package com.express.servermanagertool.ui;

import com.express.servermanagertool.model.Credential;
import com.express.servermanagertool.util.ConfigManager;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.input.KeyCode;
import javafx.scene.input.MouseButton;
import javafx.scene.layout.*;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class MainApp extends Application {
    private Credential currentCredential;
    private Stage primaryStage;
    private boolean fullScreen = false;
    private double xOffset = 0, yOffset = 0;
    private SshTerminal terminal;
    private boolean isModifyPanelShowing = false;

    @Override
    public void start(Stage stage) {
        this.primaryStage = stage;
        stage.initStyle(StageStyle.UNDECORATED);
        stage.setTitle("");
        stage.setWidth(900);
        stage.setHeight(600);

        BorderPane initialRoot = new BorderPane();
        Scene scene = new Scene(initialRoot, 900, 600);
        scene.setOnKeyPressed(e -> {
            if (e.getCode() == KeyCode.F11) toggleFullScreen();
        });
        stage.setScene(scene);

        currentCredential = ConfigManager.load();
        if (currentCredential == null) {
            showModifyPanel();
        } else {
            showTerminalAndConnect();
        }

        stage.show();
    }

    private HBox createCustomTitleBar() {
        // ... 保持不变（与原代码相同）
        HBox titleBar = new HBox();
        titleBar.setStyle("-fx-background-color: #2d2d2d; -fx-padding: 8 12; -fx-alignment: center-left;");
        titleBar.setPrefHeight(40);

        Label titleLabel = new Label("服务器管理工具");
        titleLabel.setStyle("-fx-text-fill: #e0e0e0; -fx-font-size: 14; -fx-font-weight: bold;");

        Label infoLabel = new Label();
        infoLabel.setStyle("-fx-text-fill: #aaaaaa; -fx-font-size: 12; -fx-padding: 0 10 0 10;");
        if (currentCredential != null) {
            infoLabel.setText(currentCredential.getIp() + " | " + currentCredential.getUsername());
        }

        Button modifyBtn = new Button("✏️ 修改");
        modifyBtn.setStyle("-fx-background-color: transparent; -fx-text-fill: #4ade80; -fx-cursor: hand;");
        modifyBtn.setOnAction(e -> showModifyPanel());

        Region spacer = new Region();
        HBox.setHgrow(spacer, Priority.ALWAYS);

        Button minBtn = new Button("─");
        minBtn.setStyle("-fx-background-color: transparent; -fx-text-fill: white; -fx-font-weight: bold;");
        minBtn.setOnAction(e -> primaryStage.setIconified(true));

        Button fullBtn = new Button("□");
        fullBtn.setStyle("-fx-background-color: transparent; -fx-text-fill: white; -fx-font-weight: bold;");
        fullBtn.setOnAction(e -> toggleFullScreen());

        Button closeBtn = new Button("✕");
        closeBtn.setStyle("-fx-background-color: transparent; -fx-text-fill: white; -fx-font-weight: bold;");
        closeBtn.setOnAction(e -> {
            if (terminal != null) terminal.disconnect(false);
            Platform.exit();
        });

        titleBar.getChildren().addAll(titleLabel, infoLabel, modifyBtn, spacer, minBtn, fullBtn, closeBtn);

        titleBar.setOnMousePressed(event -> {
            if (event.getButton() == MouseButton.PRIMARY) {
                xOffset = event.getSceneX();
                yOffset = event.getSceneY();
            }
        });
        titleBar.setOnMouseDragged(event -> {
            if (!fullScreen) {
                primaryStage.setX(event.getScreenX() - xOffset);
                primaryStage.setY(event.getScreenY() - yOffset);
            }
        });
        return titleBar;
    }

    private void showTerminalAndConnect() {
        terminal = new SshTerminal();
        terminal.connect(currentCredential, this::onDisconnected);

        BorderPane root = new BorderPane();
        root.setTop(createCustomTitleBar());
        root.setCenter(terminal);

        primaryStage.getScene().setRoot(root);
    }

    private void onDisconnected() {
        Platform.runLater(() -> {
            if (isModifyPanelShowing) return;
            showAlert(Alert.AlertType.WARNING, "SSH 连接已断开，请修改信息后重新连接。");
            showModifyPanel();
        });
    }

    // 美化的登录/修改面板  (Windows 11 风格深色毛玻璃)
    private void showModifyPanel() {
        if (isModifyPanelShowing) return;
        isModifyPanelShowing = true;

        if (terminal != null) {
            terminal.disconnect(false);
            terminal = null;
        }

        // 创建一个容器，用于放置整个登录界面
        BorderPane root = new BorderPane();
        root.setStyle("-fx-background-color: transparent;"); // 根背景透明，让底层窗口背景可见（若有）

        // 主体内容：居中一个卡片
        VBox card = new VBox(20);
        card.setMaxWidth(400);
        card.setMaxHeight(350);
        card.setStyle(
                "-fx-background-color: rgba(30, 32, 40, 0.95); " +
                        "-fx-background-radius: 16; " +
                        "-fx-effect: dropshadow(gaussian, rgba(0,0,0,0.5), 20, 0.2, 0, 8); " +
                        "-fx-padding: 30 28 30 28; " +
                        "-fx-border-color: rgba(255,255,255,0.15); " +
                        "-fx-border-radius: 16;"
        );

        // 标题
        Label titleLabel = new Label("🔐 服务器认证");
        titleLabel.setStyle("-fx-text-fill: white; -fx-font-size: 20; -fx-font-weight: bold;");

        // 副标题
        Label subtitle = new Label("请输入 SSH 连接信息");
        subtitle.setStyle("-fx-text-fill: #aaa; -fx-font-size: 12;");

        // 输入框组
        VBox inputGroup = new VBox(12);
        inputGroup.setStyle("-fx-padding: 10 0 0 0;");

        // IP 输入框
        TextField ipField = new TextField();
        ipField.setPromptText("服务器 IP 地址");
        ipField.setStyle(
                "-fx-background-color: #1e1f2c; " +
                        "-fx-border-color: #3c3f4a; " +
                        "-fx-border-radius: 8; " +
                        "-fx-background-radius: 8; " +
                        "-fx-text-fill: #e0e0e0; " +
                        "-fx-prompt-text-fill: #6a6f7a; " +
                        "-fx-padding: 10 12;"
        );
        if (currentCredential != null) ipField.setText(currentCredential.getIp());

        // 用户名输入框
        TextField userField = new TextField();
        userField.setPromptText("用户名");
        userField.setStyle(ipField.getStyle());
        if (currentCredential != null) userField.setText(currentCredential.getUsername());

        // 密码输入框
        PasswordField passField = new PasswordField();
        passField.setPromptText("密码");
        passField.setStyle(ipField.getStyle());
        if (currentCredential != null) passField.setText(currentCredential.getPassword());

        inputGroup.getChildren().addAll(ipField, userField, passField);

        // 按钮区域
        HBox buttonBox = new HBox(15);
        buttonBox.setStyle("-fx-alignment: center; -fx-padding: 10 0 0 0;");

        Button saveBtn = new Button("连接服务器");
        saveBtn.setStyle(
                "-fx-background-color: #0a6cff; " +
                        "-fx-text-fill: white; " +
                        "-fx-font-weight: bold; " +
                        "-fx-background-radius: 20; " +
                        "-fx-padding: 8 24; " +
                        "-fx-cursor: hand;"
        );
        saveBtn.setOnMouseEntered(e -> saveBtn.setStyle(saveBtn.getStyle() + "-fx-background-color: #1a7cff;"));
        saveBtn.setOnMouseExited(e -> saveBtn.setStyle("-fx-background-color: #0a6cff; -fx-text-fill: white; -fx-font-weight: bold; -fx-background-radius: 20; -fx-padding: 8 24; -fx-cursor: hand;"));

        Button cancelBtn = new Button("取消");
        cancelBtn.setStyle(
                "-fx-background-color: transparent; " +
                        "-fx-text-fill: #ccc; " +
                        "-fx-border-color: #5a5f6e; " +
                        "-fx-border-radius: 20; " +
                        "-fx-padding: 8 24; " +
                        "-fx-cursor: hand;"
        );
        cancelBtn.setOnMouseEntered(e -> cancelBtn.setStyle(cancelBtn.getStyle() + "-fx-background-color: #2a2e3a;"));
        cancelBtn.setOnMouseExited(e -> cancelBtn.setStyle("-fx-background-color: transparent; -fx-text-fill: #ccc; -fx-border-color: #5a5f6e; -fx-border-radius: 20; -fx-padding: 8 24; -fx-cursor: hand;"));

        buttonBox.getChildren().addAll(saveBtn, cancelBtn);

        // 将所有组件添加到卡片
        card.getChildren().addAll(titleLabel, subtitle, inputGroup, buttonBox);

        // 将卡片居中显示
        StackPane centerPane = new StackPane(card);
        centerPane.setStyle("-fx-background-color: #12151e;"); // 整个窗口背景深色

        root.setCenter(centerPane);

        // 简单的自定义标题栏（保留窗口控制）
        HBox simpleTitle = new HBox();
        simpleTitle.setStyle("-fx-background-color: #1e2028; -fx-padding: 6 12; -fx-alignment: center-right;");
        Button closeOnly = new Button("✕");
        closeOnly.setStyle("-fx-background-color: transparent; -fx-text-fill: white; -fx-font-weight: bold;");
        closeOnly.setOnAction(e -> Platform.exit());
        simpleTitle.getChildren().add(closeOnly);
        root.setTop(simpleTitle);

        // 保存和取消事件
        saveBtn.setOnAction(e -> {
            String ip = ipField.getText().trim();
            String user = userField.getText().trim();
            String pass = passField.getText();
            if (ip.isEmpty() || user.isEmpty()) {
                showAlert(Alert.AlertType.ERROR, "IP 和用户名不能为空");
                return;
            }
            Credential newCred = new Credential(ip, user, pass);
            try {
                ConfigManager.save(newCred);
                currentCredential = newCred;
                isModifyPanelShowing = false;
                showTerminalAndConnect();
            } catch (Exception ex) {
                showAlert(Alert.AlertType.ERROR, "保存凭证失败: " + ex.getMessage());
            }
        });

        cancelBtn.setOnAction(e -> {
            if (currentCredential != null) {
                isModifyPanelShowing = false;
                showTerminalAndConnect();
            } else {
                Platform.exit();
            }
        });

        primaryStage.getScene().setRoot(root);
    }

    private void toggleFullScreen() {
        fullScreen = !fullScreen;
        primaryStage.setFullScreen(fullScreen);
    }

    private void showAlert(Alert.AlertType type, String msg) {
        Alert alert = new Alert(type, msg);
        alert.initStyle(StageStyle.UTILITY);
        alert.showAndWait();
    }

    public static void main(String[] args) {
        launch(args);
    }
}