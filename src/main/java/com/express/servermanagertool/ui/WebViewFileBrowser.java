package com.express.servermanagertool.ui;

import com.express.servermanagertool.core.TerminalSession;
import javafx.application.Platform;
import javafx.concurrent.Worker;
import javafx.scene.layout.BorderPane;
import javafx.scene.web.WebView;
import netscape.javascript.JSObject;

import java.util.logging.Level;
import java.util.logging.Logger;

public class WebViewFileBrowser extends BorderPane {
    private static final Logger LOGGER = Logger.getLogger(WebViewFileBrowser.class.getName());

    private final TerminalSession session;
    private final Runnable onExit;          // 返回到终端的回调
    private WebView webView;
    private WebViewBackend backend;
    private boolean isBackendInjected = false;  // 防止重复注入

    public WebViewFileBrowser(TerminalSession session, Runnable onExit) {
        if (session == null) {
            throw new IllegalArgumentException("TerminalSession cannot be null");
        }
        this.session = session;
        this.onExit = onExit;
        initWebView();
    }

    private void initWebView() {
        webView = new WebView();
        webView.getEngine().setJavaScriptEnabled(true);

        // 1. 检查 HTML 文件是否存在
        String htmlPath = getClass().getResource("/templates/linux_desktop.html").toExternalForm();
        if (htmlPath == null) {
            LOGGER.severe("无法找到 /templates/linux_desktop.html，请检查资源文件");
            if (onExit != null) {
                Platform.runLater(onExit);
            }
            return;
        }
        webView.getEngine().load(htmlPath);

        // 2. 监听页面加载成功
        webView.getEngine().getLoadWorker().stateProperty().addListener((obs, oldState, newState) -> {
            if (newState == Worker.State.SUCCEEDED && !isBackendInjected) {
                isBackendInjected = true;
                try {
                    // 3. 创建后端桥接
                    backend = new WebViewBackend(session, webView);
                    backend.setOnExitCallback(onExit);
                    JSObject window = (JSObject) webView.getEngine().executeScript("window");
                    window.setMember("javaBackend", backend);
                    backend.setJsWindow(window);

                    // 4. 启动终端输出推送线程
                    backend.startTerminalReader();

                    // 5. 通知前端后端已就绪（捕获前端未定义函数的异常）
                    try {
                        window.call("onJavaBackendReady");
                    } catch (Exception e) {
                        LOGGER.log(Level.WARNING, "前端未定义 onJavaBackendReady 函数", e);
                    }

                    // 6. 注册退出回调（显式创建 Runnable 对象，避免 lambda 编译歧义）
                    Runnable exitCallback = () -> {
                        if (onExit != null) {
                            Platform.runLater(onExit);
                        }
                    };
                    window.setMember("exitToTerminal", exitCallback);

                } catch (Exception e) {
                    LOGGER.log(Level.SEVERE, "WebViewBackend 初始化失败", e);
                    if (onExit != null) {
                        Platform.runLater(onExit);
                    }
                }
            }
        });

        // 7. 处理加载失败（如网络错误、文件不存在等）
        webView.getEngine().getLoadWorker().exceptionProperty().addListener((obs, oldErr, newErr) -> {
            if (newErr != null) {
                LOGGER.log(Level.SEVERE, "WebView 加载失败", newErr);
                if (onExit != null) {
                    Platform.runLater(onExit);
                }
            }
        });

        setCenter(webView);
    }

    /**
     * 可选：释放资源（需要在父容器移除时主动调用）
     * 由于 setVisible 是 final 方法，不能重写，请手动调用此方法或通过外置逻辑调用。
     */
    public void dispose() {
        if (backend != null) {
            try {
                // 如果 WebViewBackend 实现了 stop 方法，可以在这里调用
                // backend.stop();
            } catch (Exception e) {
                LOGGER.log(Level.WARNING, "停止后端时出错", e);
            }
            backend = null;
        }
        if (webView != null) {
            webView.getEngine().load(null); // 释放页面资源
            webView = null;
        }
        isBackendInjected = false;
    }
}