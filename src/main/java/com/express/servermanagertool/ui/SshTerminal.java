package com.express.servermanagertool.ui;

import com.express.servermanagertool.model.Credential;
import com.express.servermanagertool.core.TerminalSession;
import javafx.application.Platform;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.scene.input.Dragboard;
import javafx.scene.input.KeyCode;
import javafx.scene.input.TransferMode;
import javafx.scene.layout.*;
import javafx.stage.Stage;

import java.io.BufferedReader;
import java.io.File;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class SshTerminal extends BorderPane {
    private final TextArea outputArea;
    private final TextField inputField;
    private TerminalSession session;
    private final List<String> history = new ArrayList<>();
    private int historyIndex = 0;
    private volatile boolean isConnected = false;
    private Runnable onDisconnect;

    private BorderPane terminalPanel;
    private RemoteFileBrowser fileBrowser;
    private boolean isFileBrowserActive = false;

    public SshTerminal() {
        outputArea = new TextArea();
        outputArea.setEditable(false);
        outputArea.setWrapText(true);
        outputArea.setStyle("-fx-font-family: 'Courier New', monospace; -fx-font-size: 13px; " +
                "-fx-control-inner-background: #0c0c10; -fx-text-fill: #cbd5e6; " +
                "-fx-padding: 10; -fx-background-color: #0c0c10;");
        // 隐藏滚动条（需延迟执行，等待皮肤加载）
        Platform.runLater(() -> {
            Node scrollPane = outputArea.lookup(".scroll-pane");
            if (scrollPane instanceof ScrollPane) {
                ScrollPane sp = (ScrollPane) scrollPane;
                sp.setHbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
                sp.setVbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
            }
        });


        inputField = new TextField();
        inputField.setStyle("-fx-font-family: monospace; -fx-background-color: #0c0c10; " +
                "-fx-text-fill: #f1f5f9; -fx-padding: 5;");

        HBox inputBox = new HBox(0);
        inputBox.setStyle("-fx-background-color: #0c0c10; -fx-padding: 0 10 10 10;");
        Label prompt = new Label("$ ");
        prompt.setStyle("-fx-text-fill: #4ade80; -fx-font-weight: bold;");
        inputBox.getChildren().addAll(prompt, inputField);
        HBox.setHgrow(inputField, Priority.ALWAYS);

        terminalPanel = new BorderPane();
        terminalPanel.setCenter(outputArea);
        terminalPanel.setBottom(inputBox);
        terminalPanel.setStyle("-fx-background-color: #0c0c10;");

        setCenter(terminalPanel);
        this.setStyle("-fx-background-color: #0c0c10;");

        setupKeyEvents();
        setupDragAndDrop();
    }

    private void setupKeyEvents() {
        inputField.setOnAction(e -> sendCommand());
        inputField.setOnKeyPressed(e -> {
            if (e.getCode() == KeyCode.UP && historyIndex > 0) {
                inputField.setText(history.get(--historyIndex));
            } else if (e.getCode() == KeyCode.DOWN) {
                if (historyIndex < history.size() - 1) {
                    inputField.setText(history.get(++historyIndex));
                } else {
                    historyIndex = history.size();
                    inputField.clear();
                }
            }
        });
    }

    private void setupDragAndDrop() {
        this.setOnDragOver(event -> {
            if (event.getDragboard().hasFiles() && isConnected) {
                event.acceptTransferModes(TransferMode.COPY);
            }
            event.consume();
        });

        this.setOnDragDropped(event -> {
            Dragboard db = event.getDragboard();
            if (db.hasFiles()) {
                handleUpload(db.getFiles());
            }
            event.setDropCompleted(true);
            event.consume();
        });
    }

    private void handleUpload(List<File> files) {
        if (!isConnected || session == null) return;
        new Thread(() -> {
            try {
                String remotePath = session.getRemoteWorkDir();
                if (remotePath == null || remotePath.trim().isEmpty()) {
                    throw new Exception("无法获取远程工作目录");
                }
                for (File f : files) {
                    final String fileName = f.getName();
                    Platform.runLater(() ->
                            outputArea.appendText("\n[System] 正在上传: " + fileName + " -> " + remotePath + "\n")
                    );
                    session.uploadFile(f, remotePath);
                    Platform.runLater(() ->
                            outputArea.appendText("[System] " + fileName + " 上传成功!\n")
                    );
                }
            } catch (Exception e) {
                Platform.runLater(() ->
                        outputArea.appendText("\n[Error] 上传失败: " + e.getMessage() + "\n")
                );
            }
        }).start();
    }

    public void connect(Credential cred, Runnable onDisconnectCallback) {
        this.onDisconnect = onDisconnectCallback;
        new Thread(() -> {
            try {
                session = new TerminalSession();
                session.connect(cred);
                isConnected = true;
                Platform.runLater(() -> {
                    outputArea.clear();
                    outputArea.appendText("[System] 连接成功! （输入 view 打开可视化文件管理）\n");
                    inputField.requestFocus();
                });
                startReader();
            } catch (Exception e) {
                Platform.runLater(() -> {
                    outputArea.appendText("[System] 连接失败: " + e.getMessage() + "\n");
                    if (onDisconnect != null) onDisconnect.run();
                });
            }
        }).start();
    }

    private void startReader() {
        new Thread(() -> {
            try (BufferedReader reader = new BufferedReader(new InputStreamReader(session.getInputStream()))) {
                char[] buf = new char[1024];
                int len;
                while (isConnected && (len = reader.read(buf)) != -1) {
                    String chunk = new String(buf, 0, len);
                    Platform.runLater(() -> {
                        outputArea.appendText(chunk);
                        outputArea.positionCaret(outputArea.getLength());
                    });
                }
            } catch (Exception e) {
                if (isConnected) disconnect();
            }
        }).start();
    }

    private void sendCommand() {
        String cmd = inputField.getText().trim();
        if (cmd.isEmpty() || !isConnected) return;
        if ("view".equalsIgnoreCase(cmd)) {
            inputField.clear();
            showFileBrowser();
            return;
        }
        history.add(cmd);
        historyIndex = history.size();
        try {
            session.sendCommand(cmd);
        } catch (Exception e) {
            outputArea.appendText("[Error] " + e.getMessage() + "\n");
        }
        inputField.clear();
    }

    private void showFileBrowser() {
        if (!isConnected || session == null) return;
        if (fileBrowser == null) {
            fileBrowser = new RemoteFileBrowser(session, this::showTerminal);
        }
        isFileBrowserActive = true;
        setCenter(fileBrowser);
    }

    private void showTerminal() {
        isFileBrowserActive = false;
        setCenter(terminalPanel);
        inputField.requestFocus();
    }

    // ========== 修改点：增加 disconnect(boolean) 重载 ==========
    public void disconnect() {
        disconnect(true);
    }

    public void disconnect(boolean runCallback) {
        isConnected = false;
        if (session != null) {
            session.disconnect();
            session = null;
        }
        if (runCallback) {
            Platform.runLater(() -> {
                inputField.setDisable(true);
                if (onDisconnect != null) onDisconnect.run();
            });
        }
    }
}