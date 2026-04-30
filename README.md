# 🖥️ 服务器管理工具 (Server Manager Tool)

一个基于 **JavaFX** 和 **JSch** 开发的轻量级图形化服务器管理工具，提供 **SSH 终端模拟**与 **SFTP 远程文件管理**功能，帮助开发者便捷地管理 Linux 服务器。

## ✨ 主要特性

- 🔐 **凭证管理**：首次使用时输入服务器 IP、用户名、密码，后续自动保存（本地加密存储，位于 `~/.server_manager.conf`）。
- 🖥️ **SSH 终端**：支持完整的 Shell 交互，历史命令记录（上下键切换），拖拽上传文件。
- 📂 **远程文件浏览器**：
  - 以树形目录+表格形式浏览远程文件/文件夹
  - 支持新建目录、删除、重命名（删除前确认）
  - 上传/下载文件（图形化选择本地文件）
  - 双击目录进入，支持路径导航及向上返回
- 🧩 **一体化 UI**：无边框窗口，自定义标题栏（包含服务器信息显示、修改凭证按钮、窗口控制按钮），支持 F11 全屏。
- 📁 **拖拽上传**：将本地文件直接拖入终端窗口即可上传到当前工作目录。
- 🔌 **轻量快捷**：无需配置 Web 服务，纯桌面 Java 应用，依赖少，启动快。

## 🛠️ 技术栈

| 名称          | 版本      | 用途                     |
| ------------- | --------- | ------------------------ |
| Java          | 17        | 基础运行环境             |
| JavaFX        | 21        | 桌面 UI 框架             |
| JSch          | 0.1.55    | SSH / SFTP 协议实现      |
| Maven         | 3.9.14    | 项目构建与依赖管理       |
| SLF4J Simple  | 2.0.9     | 简单日志输出             |

## 📦 如何构建与运行

### 前置条件
- JDK 17 或更高版本
- Maven 3.6+

### 构建可执行 JAR（包含所有依赖）

```bash
mvn clean compile assembly:single
```

构建成功后，在 `target/` 目录下生成 `server-manager-tool.jar`。

### 运行

```bash
java -jar target/server-manager-tool.jar
```

> 如果直接双击运行（Windows）可能因 JavaFX 模块路径问题失败，建议通过命令行启动。

## 🚀 使用说明

1. **首次启动**：弹出凭证配置窗口，填写服务器 IP、SSH 用户名及密码，点击“保存并连接”。
2. **SSH 终端**：连接成功后进入终端界面，可以执行任意 Shell 命令。
    - 输入 `view` 并回车，切换到远程文件浏览器模式。
    - 在文件浏览器中点击“✕ 退出”回到终端。
3. **文件上传**：
    - 在终端模式下，直接将文件拖入窗口，自动上传到远程当前工作目录。
    - 在文件浏览器模式下，点击“⬆ 上传”按钮选择本地文件上传。
4. **文件下载**：在文件浏览器中选中文件，点击“⬇ 下载”保存到本地。
5. **修改连接信息**：点击标题栏的“✏️ 修改”按钮，重新编辑凭证（会自动断开当前连接）。

## 📁 项目目录结构

```
.
├── .gitignore
├── pom.xml
├── README.md
├── src
│   ├── main
│   │   ├── java/com/express/servermanagertool
│   │   │   ├── Launcher.java              # 启动器
│   │   │   ├── core/TerminalSession.java  # SSH/SFTP 核心操作
│   │   │   ├── model/                     # 数据模型（Credential, FileInfo）
│   │   │   ├── ui/                        # 界面类（MainApp, SshTerminal, RemoteFileBrowser）
│   │   │   └── util/ConfigManager.java    # 凭证持久化
│   │   └── resources/                     # 资源文件（暂未使用）
│   └── test/                              # 单元测试
```

## 💡 注意与已知限制

- **凭证存储**：密码以明文形式保存在用户目录下的 `.server_manager.conf` 文件中（Java Properties 格式），请确保操作系统文件权限安全。
- **首次运行**：如果系统未安装 JavaFX 环境，请确保 JDK 包含 JavaFX 或通过 Maven 依赖（已包含）。
- **中文支持**：终端输出支持 UTF-8，但部分远程服务器可能需配置 `LANG` 环境变量。
- **SFTP 兼容性**：基于 JSch 实现，适用于绝大多数 OpenSSH 服务器。

## 📄 开源协议

本项目采用 **MIT License** 开源，可自由使用、修改、分发。

## 🤝 作者

由 [HGJYHH](https://github.com/HGJYHH) 开发维护。

---

**Enjoy managing your servers with ease!**  
如有问题或建议，欢迎提交 Issue 或 Pull Request。
```

保存后，你可以用 `git add README.md` 把它一起提交到 GitHub，这样仓库主页就会显示一个漂亮的项目介绍了。