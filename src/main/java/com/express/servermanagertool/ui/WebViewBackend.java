package com.express.servermanagertool.ui;

import com.express.servermanagertool.core.TerminalSession;
import com.express.servermanagertool.model.FileInfo;
import javafx.application.Platform;
import javafx.scene.web.WebView;
import netscape.javascript.JSObject;

import java.io.*;
import java.util.Base64;
import java.util.List;

public class WebViewBackend {
    private final TerminalSession session;
    private final WebView webView;
    private JSObject jsWindow;
    // 在 WebViewBackend 类中添加
    private Runnable onExitCallback;

    public void setOnExitCallback(Runnable callback) {
        this.onExitCallback = callback;
    }

    public void exitToTerminal() {
        if (onExitCallback != null) {
            Platform.runLater(onExitCallback);
        }
    }
    public WebViewBackend(TerminalSession session, WebView webView) {
        this.session = session;
        this.webView = webView;
    }

    public void setJsWindow(JSObject jsWindow) {
        this.jsWindow = jsWindow;
    }

    // ========== 终端 ==========
    public void sendCommand(String cmd) {
        try {
            session.sendCommand(cmd);
        } catch (Exception e) {
            callJs("onTerminalError", e.getMessage());
        }
    }

    public void startTerminalReader() {
        new Thread(() -> {
            try (BufferedReader reader = new BufferedReader(new InputStreamReader(session.getInputStream()))) {
                char[] buf = new char[8192];
                int len;
                while (session.isConnected() && (len = reader.read(buf)) != -1) {
                    String chunk = new String(buf, 0, len);
                    String escaped = escapeJsString(chunk);
                    Platform.runLater(() -> callJs("onTerminalOutput", escaped));
                }
            } catch (Exception e) {
                Platform.runLater(() -> callJs("onTerminalError", "连接中断: " + e.getMessage()));
            }
        }).start();
    }

    // ========== 文件管理器 ==========
    public String listDirectory(String path) {
        try {
            List<FileInfo> files = session.listFiles(path);
            StringBuilder sb = new StringBuilder("[");
            for (FileInfo f : files) {
                sb.append("{")
                        .append("\"name\":\"").append(escapeJson(f.getName())).append("\",")
                        .append("\"fullPath\":\"").append(escapeJson(f.getFullPath())).append("\",")
                        .append("\"isDirectory\":").append(f.isDirectory()).append(",")
                        .append("\"size\":").append(f.getSize()).append(",")
                        .append("\"mtime\":\"").append(escapeJson(f.getMtime())).append("\",")
                        .append("\"permissions\":\"").append(escapeJson(f.getPermissions())).append("\"")
                        .append("},");
            }
            if (sb.length() > 1) sb.setLength(sb.length() - 1);
            sb.append("]");
            return sb.toString();
        } catch (Exception e) {
            return "{\"error\":\"" + escapeJson(e.getMessage()) + "\"}";
        }
    }

    public void createDirectory(String path) {
        try {
            session.createDirectory(path);
            callJs("onFileOperationComplete", "mkdir", path);
        } catch (Exception e) {
            callJs("onError", "创建目录失败: " + e.getMessage());
        }
    }

    public void deleteFile(String fullPath) {
        try {
            session.delete(fullPath);
            callJs("onFileOperationComplete", "delete", fullPath);
        } catch (Exception e) {
            callJs("onError", "删除失败: " + e.getMessage());
        }
    }

    public void uploadFile(String fileName, String base64Data, String remoteDir) {
        new Thread(() -> {
            try {
                byte[] bytes = Base64.getDecoder().decode(base64Data);
                File temp = File.createTempFile("upload", ".tmp");
                try (FileOutputStream fos = new FileOutputStream(temp)) {
                    fos.write(bytes);
                }
                session.uploadFile(temp, remoteDir);
                temp.delete();
                Platform.runLater(() -> callJs("onUploadComplete", fileName));
            } catch (Exception e) {
                Platform.runLater(() -> callJs("onError", "上传失败: " + e.getMessage()));
            }
        }).start();
    }

    public void downloadFile(String remotePath, String fileName) {
        new Thread(() -> {
            try {
                ByteArrayOutputStream baos = new ByteArrayOutputStream();
                session.downloadFile(remotePath, baos);
                String b64 = Base64.getEncoder().encodeToString(baos.toByteArray());
                Platform.runLater(() -> callJs("onDownloadReady", remotePath, fileName, b64));
            } catch (Exception e) {
                Platform.runLater(() -> callJs("onError", "下载失败: " + e.getMessage()));
            }
        }).start();
    }

    // ========== 辅助 ==========
    private void callJs(String functionName, Object... args) {
        Platform.runLater(() -> {
            if (jsWindow != null) {
                StringBuilder sb = new StringBuilder(functionName);
                sb.append("(");
                for (int i = 0; i < args.length; i++) {
                    if (i > 0) sb.append(",");
                    Object arg = args[i];
                    if (arg instanceof String) {
                        sb.append("'").append(escapeJsString((String) arg)).append("'");
                    } else {
                        sb.append(arg);
                    }
                }
                sb.append(")");
                jsWindow.eval(sb.toString());
            }
        });
    }

    private String escapeJson(String s) {
        if (s == null) return "";
        return s.replace("\\", "\\\\").replace("\"", "\\\"").replace("\n", "\\n");
    }

    private String escapeJsString(String s) {
        if (s == null) return "";
        return s.replace("\\", "\\\\").replace("'", "\\'").replace("\n", "\\n").replace("\r", "\\r");
    }
}