package com.express.servermanagertool.ui;

import com.express.servermanagertool.util.ConfigManager;
import com.express.servermanagertool.model.Credential;
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
    private SshTerminal terminal;
    private Credential currentCredential;
    private Stage primaryStage;
    private boolean fullScreen = false;

    // 窗口拖动偏移量
    private double xOffset = 0;
    private double yOffset = 0;

    @Override
    public void start(Stage stage) {
        this.primaryStage = stage;
        stage.initStyle(StageStyle.UNDECORATED);
        stage.setTitle("");          // 任务栏标题
        stage.setWidth(900);
        stage.setHeight(600);

        // 创建一个临时根节点，用于初始化 Scene
        BorderPane initialRoot = new BorderPane();
        Scene scene = new Scene(initialRoot, 900, 600);
        // 全屏快捷键（F11）
        scene.setOnKeyPressed(e -> {
            if (e.getCode() == KeyCode.F11) {
                toggleFullScreen();
            }
        });
        stage.setScene(scene);

        // 加载已保存的凭证
        currentCredential = ConfigManager.load();
        if (currentCredential == null) {
            showModifyPanel();
        } else {
            showTerminalAndConnect();
        }

        stage.show();
    }

    // 创建自定义标题栏（无边框窗口使用）
    private HBox createCustomTitleBar() {
        HBox titleBar = new HBox();
        titleBar.setStyle("-fx-background-color: #2d2d2d; -fx-padding: 8 12; -fx-alignment: center-left;");
        titleBar.setPrefHeight(40);

        // 左侧内容
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

        // 中间弹簧，将右侧按钮推至最右边
        Region spacer = new Region();
        HBox.setHgrow(spacer, Priority.ALWAYS);

        // 右侧窗口控制按钮
        Button minBtn = new Button("─");
        minBtn.setStyle("-fx-background-color: transparent; -fx-text-fill: white; -fx-font-weight: bold;");
        minBtn.setOnAction(e -> primaryStage.setIconified(true));

        Button fullBtn = new Button("□");
        fullBtn.setStyle("-fx-background-color: transparent; -fx-text-fill: white; -fx-font-weight: bold;");
        fullBtn.setOnAction(e -> toggleFullScreen());

        Button closeBtn = new Button("✕");
        closeBtn.setStyle("-fx-background-color: transparent; -fx-text-fill: white; -fx-font-weight: bold;");
        closeBtn.setOnAction(e -> {
            if (terminal != null) terminal.disconnect();
            Platform.exit();
        });

        titleBar.getChildren().addAll(titleLabel, infoLabel, modifyBtn, spacer, minBtn, fullBtn, closeBtn);

        // 窗口拖动
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
        root.setCenter(terminal);
        root.setTop(createCustomTitleBar());

        primaryStage.getScene().setRoot(root);
    }

    private void showModifyPanel() {
        // 主动断开时禁止回调弹窗，避免循环
        if (terminal != null) terminal.disconnect(false);

        VBox modifyBox = new VBox(15);
        modifyBox.setStyle("-fx-padding: 30; -fx-alignment: center; -fx-background-color: #1e1e1e;");

        TextField ipField = new TextField();
        ipField.setPromptText("服务器 IP");
        TextField userField = new TextField();
        userField.setPromptText("用户名");
        PasswordField passField = new PasswordField();
        passField.setPromptText("密码");
        if (currentCredential != null) {
            ipField.setText(currentCredential.getIp());
            userField.setText(currentCredential.getUsername());
            passField.setText(currentCredential.getPassword());
        }

        Button saveBtn = new Button("保存并连接");
        saveBtn.setOnAction(e -> {
            String ip = ipField.getText().trim();
            String user = userField.getText().trim();
            String pass = passField.getText();
            if (ip.isEmpty() || user.isEmpty()) {
                showAlert(Alert.AlertType.ERROR, "IP和用户名不能为空");
                return;
            }
            Credential newCred = new Credential(ip, user, pass);
            try {
                ConfigManager.save(newCred);
                currentCredential = newCred;
                showTerminalAndConnect();
            } catch (Exception ex) {
                showAlert(Alert.AlertType.ERROR, "保存凭证失败: " + ex.getMessage());
            }
        });
        Button cancelBtn = new Button("取消");
        cancelBtn.setOnAction(e -> {
            if (currentCredential != null) {
                showTerminalAndConnect();
            } else {
                Platform.exit();
            }
        });
        modifyBox.getChildren().addAll(
                new Label("请输入服务器连接信息："), ipField, userField, passField,
                new HBox(10, saveBtn, cancelBtn)
        );

        HBox simpleTitle = new HBox();
        simpleTitle.setStyle("-fx-background-color: #2d2d2d; -fx-padding: 8; -fx-alignment: center-right;");
        Button closeOnly = new Button("✕");
        closeOnly.setOnAction(e -> Platform.exit());
        simpleTitle.getChildren().add(closeOnly);

        BorderPane root = new BorderPane();
        root.setStyle("-fx-background-color: #1e1e1e;");
        root.setCenter(modifyBox);
        root.setTop(simpleTitle);

        primaryStage.getScene().setRoot(root);
    }

    private void onDisconnected() {
        Platform.runLater(() -> {
            showAlert(Alert.AlertType.WARNING, "SSH 连接已断开，请修改信息后重新连接。");
            showModifyPanel();
        });
    }

    private void toggleFullScreen() {
        fullScreen = !fullScreen;
        primaryStage.setFullScreen(fullScreen);
    }

    private void showAlert(Alert.AlertType type, String msg) {
        Alert alert = new Alert(type, msg);
        alert.showAndWait();
    }

    public static void main(String[] args) {
        launch(args);
    }
}