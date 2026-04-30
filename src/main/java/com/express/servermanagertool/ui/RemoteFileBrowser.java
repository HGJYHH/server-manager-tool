package com.express.servermanagertool.ui;

import com.express.servermanagertool.core.TerminalSession;
import com.express.servermanagertool.model.FileInfo;
import javafx.application.Platform;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.stage.FileChooser;

import java.io.*;
import java.util.List;
import java.util.Optional;

public class RemoteFileBrowser extends BorderPane {
    private final TerminalSession session;
    private final Runnable onExit;

    private TreeView<String> treeView;
    private TableView<FileInfo> fileTable;
    private Label pathLabel;
    private String currentPath = "/";

    public RemoteFileBrowser(TerminalSession session, Runnable onExit) {
        this.session = session;
        this.onExit = onExit;
        buildUI();
        // 启动时切换到远程家目录
        new Thread(() -> {
            try {
                String home = session.getRemoteWorkDir();
                if (home != null && !home.isEmpty()) currentPath = home;
            } catch (Exception ignored) {}
            Platform.runLater(() -> loadDirectory(currentPath));
        }).start();
    }

    @SuppressWarnings("unchecked")
    private void buildUI() {
        // ================= 全局深色背景 =================
        setStyle("-fx-background-color: #202020;");

        // ================= 顶部栏：路径 + 按钮 + 退出 =================
        HBox topBar = new HBox(6);
        topBar.setStyle("-fx-background-color: #2d2d2d; -fx-padding: 6 10; -fx-alignment: center-left;");

        // 路径标签
        pathLabel = new Label("/");
        pathLabel.setStyle(
                "-fx-background-color: #1e1e1e; -fx-text-fill: #e0e0e0; " +
                        "-fx-font-family: 'monospace'; -fx-font-size: 13; " +
                        "-fx-border-color: #555; -fx-border-radius: 3; -fx-padding: 4 10;"
        );
        HBox.setHgrow(pathLabel, Priority.ALWAYS);

        // 工具栏按钮（扁平样式）
        String btnBase = "-fx-background-color: transparent; -fx-text-fill: #ccc; " +
                "-fx-font-size: 12; -fx-padding: 6 10; -fx-border-radius: 3;";

        Button refreshBtn = styledButton("🔄 刷新", btnBase);
        Button upBtn = styledButton("⬆ 向上", btnBase);
        Button mkdirBtn = styledButton("📁⁺ 新建文件夹", btnBase);
        Button deleteBtn = styledButton("🗑 删除", btnBase);
        Button downloadBtn = styledButton("⬇ 下载", btnBase);
        Button uploadBtn = styledButton("⬆ 上传", btnBase);

        // 退出按钮
        Button exitBtn = new Button("✕ 退出");
        exitBtn.setStyle("-fx-background-color: #c0392b; -fx-text-fill: white; -fx-font-weight: bold; -fx-padding: 6 12;");

        topBar.getChildren().addAll(pathLabel, refreshBtn, upBtn, mkdirBtn,
                deleteBtn, downloadBtn, uploadBtn, exitBtn);
        setTop(topBar);

        // ================= 左侧目录树（隐藏滚动条） =================
        treeView = new TreeView<>();
        TreeItem<String> rootItem = new TreeItem<>("/");
        rootItem.getChildren().add(new TreeItem<>("")); // 占位用于展开
        treeView.setRoot(rootItem);
        treeView.setShowRoot(true);
        treeView.setStyle(
                "-fx-background-color: #252526; -fx-text-fill: #e0e0e0; " +
                        "-fx-font-size: 13; -fx-border-color: #444;" +
                        " .scroll-bar:vertical, .scroll-bar:horizontal { -fx-opacity: 0; -fx-background-color: transparent; }"
        );
        treeView.setCellFactory(tv -> new TreeCell<>() {
            @Override
            protected void updateItem(String item, boolean empty) {
                super.updateItem(item, empty);
                if (empty || item == null) {
                    setText(null);
                    setGraphic(null);
                } else {
                    String name = item.equals("/") ? "/" : item.substring(item.lastIndexOf('/') + 1);
                    setText(name);
                }
            }
        });
        // 点击树节点 → 加载目录
        treeView.getSelectionModel().selectedItemProperty().addListener((obs, oldVal, newVal) -> {
            if (newVal != null && newVal.getValue() != null) {
                loadDirectory(newVal.getValue());
            }
        });
        // 展开时懒加载子目录
        treeView.getRoot().addEventHandler(TreeItem.branchExpandedEvent(), e -> {
            TreeItem<?> source = e.getTreeItem();
            if (source.getValue() instanceof String) {
                TreeItem<String> item = (TreeItem<String>) source;
                if (!item.isLeaf() && item.getChildren().size() == 1 && "".equals(item.getChildren().get(0).getValue())) {
                    loadSubDirectories(item);
                }
            }
        });

        // ================= 右侧文件表格（隐藏滚动条） =================
        fileTable = new TableView<>();
        fileTable.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
        fileTable.setStyle("-fx-background-color: #202020; -fx-text-fill: #e0e0e0; -fx-font-size: 13;" +
                " .scroll-bar:vertical, .scroll-bar:horizontal { -fx-opacity: 0; -fx-background-color: transparent; }");

        TableColumn<FileInfo, String> nameCol = new TableColumn<>("名称");
        nameCol.setCellValueFactory(cd -> new javafx.beans.property.SimpleStringProperty(cd.getValue().getName()));
        TableColumn<FileInfo, String> sizeCol = new TableColumn<>("大小");
        sizeCol.setCellValueFactory(cd -> new javafx.beans.property.SimpleStringProperty(formatSize(cd.getValue().getSize())));
        TableColumn<FileInfo, String> timeCol = new TableColumn<>("修改时间");
        timeCol.setCellValueFactory(cd -> new javafx.beans.property.SimpleStringProperty(cd.getValue().getMtime()));
        TableColumn<FileInfo, String> permCol = new TableColumn<>("权限");
        permCol.setCellValueFactory(cd -> new javafx.beans.property.SimpleStringProperty(cd.getValue().getPermissions()));

        fileTable.getColumns().addAll(nameCol, sizeCol, timeCol, permCol);
        // 双击进入文件夹
        fileTable.setOnMouseClicked(e -> {
            if (e.getClickCount() == 2) {
                FileInfo selected = fileTable.getSelectionModel().getSelectedItem();
                if (selected != null && selected.isDirectory()) {
                    loadDirectory(selected.getFullPath());
                }
            }
        });

        // ================= 分割容器（可拖动） =================
        SplitPane splitPane = new SplitPane();
        splitPane.getItems().addAll(treeView, fileTable);
        splitPane.setDividerPositions(0.25);
        splitPane.setStyle("-fx-background-color: #202020; -fx-border-color: #444;");
        setCenter(splitPane);

        // ================= 按钮事件绑定 =================
        refreshBtn.setOnAction(e -> loadDirectory(currentPath));
        upBtn.setOnAction(e -> {
            if (!"/".equals(currentPath)) {
                currentPath = currentPath.substring(0, currentPath.lastIndexOf('/'));
                if (currentPath.isEmpty()) currentPath = "/";
                loadDirectory(currentPath);
            }
        });
        mkdirBtn.setOnAction(e -> showCreateDirectoryDialog());
        deleteBtn.setOnAction(e -> {
            FileInfo selected = fileTable.getSelectionModel().getSelectedItem();
            if (selected == null) {
                showWarning("请先选择要删除的文件或目录");
                return;
            }
            confirmAndDelete(selected);
        });
        downloadBtn.setOnAction(e -> {
            FileInfo selected = fileTable.getSelectionModel().getSelectedItem();
            if (selected == null || selected.isDirectory()) {
                showWarning("请选择一个文件进行下载");
                return;
            }
            downloadFile(selected);
        });
        uploadBtn.setOnAction(e -> uploadFile());
        exitBtn.setOnAction(e -> onExit.run());
    }

    // 辅助方法：创建带悬停样式的按钮
    private Button styledButton(String text, String baseStyle) {
        Button btn = new Button(text);
        btn.setStyle(baseStyle);
        btn.setOnMouseEntered(e -> btn.setStyle(baseStyle + "-fx-background-color: #454545; -fx-text-fill: white;"));
        btn.setOnMouseExited(e -> btn.setStyle(baseStyle));
        return btn;
    }

    // ==================== 核心逻辑 ====================
    private void loadDirectory(String path) {
        currentPath = path;
        pathLabel.setText(path);
        new Thread(() -> {
            try {
                List<FileInfo> files = session.listFiles(path);
                Platform.runLater(() -> {
                    fileTable.getItems().setAll(files);
                    selectTreeByPath(path);
                });
            } catch (Exception e) {
                Platform.runLater(() -> showError("列出目录失败: " + e.getMessage()));
            }
        }).start();
    }

    private void loadSubDirectories(TreeItem<String> parent) {
        new Thread(() -> {
            try {
                List<FileInfo> items = session.listFiles(parent.getValue());
                Platform.runLater(() -> {
                    parent.getChildren().clear();
                    for (FileInfo f : items) {
                        if (f.isDirectory()) {
                            TreeItem<String> child = new TreeItem<>(f.getFullPath());
                            child.getChildren().add(new TreeItem<>(""));
                            parent.getChildren().add(child);
                        }
                    }
                });
            } catch (Exception e) {
                Platform.runLater(() -> showError("加载子目录失败: " + e.getMessage()));
            }
        }).start();
    }

    private void selectTreeByPath(String path) {
        TreeItem<String> root = treeView.getRoot();
        if (root == null) return;
        TreeItem<String> node = findNode(root, path);
        if (node != null) {
            treeView.getSelectionModel().select(node);
            node.setExpanded(true);
        }
    }

    private TreeItem<String> findNode(TreeItem<String> parent, String targetPath) {
        if (parent.getValue().equals(targetPath)) return parent;
        for (TreeItem<String> child : parent.getChildren()) {
            TreeItem<String> found = findNode(child, targetPath);
            if (found != null) return found;
        }
        return null;
    }

    // ==================== 文件操作 ====================
    private void showCreateDirectoryDialog() {
        TextInputDialog dialog = new TextInputDialog();
        dialog.setTitle("新建文件夹");
        dialog.setHeaderText("在 " + currentPath + " 创建新目录");
        dialog.setContentText("名称:");
        Optional<String> result = dialog.showAndWait();
        result.ifPresent(name -> new Thread(() -> {
            try {
                String newPath = currentPath.equals("/") ? "/" + name : currentPath + "/" + name;
                session.createDirectory(newPath);
                Platform.runLater(() -> loadDirectory(currentPath));
            } catch (Exception ex) {
                Platform.runLater(() -> showError("创建失败: " + ex.getMessage()));
            }
        }).start());
    }

    private void confirmAndDelete(FileInfo target) {
        Alert confirm = new Alert(Alert.AlertType.CONFIRMATION);
        confirm.setTitle("确认删除");
        confirm.setHeaderText("删除 " + target.getName());
        confirm.setContentText("该操作不可恢复，确定要删除吗？");
        confirm.showAndWait().ifPresent(response -> {
            if (response == ButtonType.OK) {
                new Thread(() -> {
                    try {
                        session.delete(target.getFullPath());
                        Platform.runLater(() -> loadDirectory(currentPath));
                    } catch (Exception ex) {
                        Platform.runLater(() -> showError("删除失败: " + ex.getMessage()));
                    }
                }).start();
            }
        });
    }

    private void downloadFile(FileInfo file) {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setInitialFileName(file.getName());
        File target = fileChooser.showSaveDialog(getScene().getWindow());
        if (target != null) {
            new Thread(() -> {
                try (FileOutputStream fos = new FileOutputStream(target)) {
                    session.downloadFile(file.getFullPath(), fos);
                    Platform.runLater(() -> showInfo("下载完成"));
                } catch (Exception ex) {
                    Platform.runLater(() -> showError("下载失败: " + ex.getMessage()));
                }
            }).start();
        }
    }

    private void uploadFile() {
        FileChooser fileChooser = new FileChooser();
        File localFile = fileChooser.showOpenDialog(getScene().getWindow());
        if (localFile != null) {
            new Thread(() -> {
                try {
                    session.uploadFile(localFile, currentPath);
                    Platform.runLater(() -> loadDirectory(currentPath));
                } catch (Exception ex) {
                    Platform.runLater(() -> showError("上传失败: " + ex.getMessage()));
                }
            }).start();
        }
    }

    // ==================== 工具方法 ====================
    private String formatSize(long bytes) {
        if (bytes < 0) return "";
        if (bytes == 0) return "0 B";
        String[] units = {"B", "KB", "MB", "GB", "TB"};
        int digit = (int) (Math.log10(bytes) / Math.log10(1024));
        return String.format("%.1f %s", bytes / Math.pow(1024, digit), units[digit]);
    }

    private void showError(String msg) {
        Alert alert = new Alert(Alert.AlertType.ERROR, msg);
        alert.showAndWait();
    }

    private void showWarning(String msg) {
        Alert alert = new Alert(Alert.AlertType.WARNING, msg);
        alert.showAndWait();
    }

    private void showInfo(String msg) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION, msg);
        alert.showAndWait();
    }
}