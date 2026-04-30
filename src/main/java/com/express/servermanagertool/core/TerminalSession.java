package com.express.servermanagertool.core;

import com.express.servermanagertool.model.Credential;
import com.express.servermanagertool.model.FileInfo;
import com.jcraft.jsch.*;
import java.io.*;
import java.text.SimpleDateFormat;
import java.util.*;

public class TerminalSession {
    private Session session;
    private ChannelShell shellChannel;
    private InputStream in;
    private OutputStream out;
    private JSch jsch = new JSch();

    public void connect(Credential cred) throws JSchException, IOException {
        session = jsch.getSession(cred.getUsername(), cred.getIp(), 22);
        session.setPassword(cred.getPassword());
        Properties config = new Properties();
        config.put("StrictHostKeyChecking", "no");
        session.setConfig(config);
        session.connect(10000);

        shellChannel = (ChannelShell) session.openChannel("shell");
        in = shellChannel.getInputStream();
        out = shellChannel.getOutputStream();
        shellChannel.connect();
    }

    // ==================== SFTP 文件操作 ====================

    /** 获取远程当前工作目录 */
    public String getRemoteWorkDir() throws JSchException {
        ChannelSftp sftp = null;
        try {
            sftp = (ChannelSftp) session.openChannel("sftp");
            sftp.connect();
            return sftp.pwd();
        } catch (SftpException e) {
            throw new RuntimeException(e);
        } finally {
            if (sftp != null) sftp.disconnect();
        }
    }

    /** 列出指定目录下的文件/文件夹 */
    public List<FileInfo> listFiles(String remotePath) throws JSchException, SftpException {
        ChannelSftp sftp = null;
        try {
            sftp = (ChannelSftp) session.openChannel("sftp");
            sftp.connect();
            @SuppressWarnings("unchecked")
            Vector<ChannelSftp.LsEntry> entries = sftp.ls(remotePath);
            List<FileInfo> files = new ArrayList<>();
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm");
            for (ChannelSftp.LsEntry entry : entries) {
                String name = entry.getFilename();
                if (".".equals(name) || "..".equals(name)) continue;
                boolean isDir = entry.getAttrs().isDir();
                long size = entry.getAttrs().getSize();
                String mtime = sdf.format(new Date(entry.getAttrs().getMTime() * 1000L));
                String perms = entry.getAttrs().getPermissionsString();
                files.add(new FileInfo(name, combinePath(remotePath, name), isDir, size, mtime, perms));
            }
            return files;
        } finally {
            if (sftp != null) sftp.disconnect();
        }
    }

    /** 创建目录 */
    public void createDirectory(String remotePath) throws JSchException, SftpException {
        ChannelSftp sftp = null;
        try {
            sftp = (ChannelSftp) session.openChannel("sftp");
            sftp.connect();
            sftp.mkdir(remotePath);
        } finally {
            if (sftp != null) sftp.disconnect();
        }
    }

    /** 删除文件或空目录 */
    public void delete(String remotePath) throws JSchException, SftpException {
        ChannelSftp sftp = null;
        try {
            sftp = (ChannelSftp) session.openChannel("sftp");
            sftp.connect();
            // 先判断是目录还是文件
            try {
                SftpATTRS attrs = sftp.stat(remotePath);
                if (attrs.isDir()) {
                    sftp.rmdir(remotePath);
                } else {
                    sftp.rm(remotePath);
                }
            } catch (SftpException e) {
                throw e;
            }
        } finally {
            if (sftp != null) sftp.disconnect();
        }
    }

    /** 上传文件（已存在） */
    public void uploadFile(File localFile, String remoteDir) throws Exception {
        ChannelSftp sftp = null;
        try {
            sftp = (ChannelSftp) session.openChannel("sftp");
            sftp.connect();
            sftp.cd(remoteDir);
            try (FileInputStream fis = new FileInputStream(localFile)) {
                sftp.put(fis, localFile.getName());
            }
        } finally {
            if (sftp != null) sftp.disconnect();
        }
    }

    /** 下载文件到本地输出流 */
    public void downloadFile(String remotePath, OutputStream localOutput) throws JSchException, SftpException, IOException {
        ChannelSftp sftp = null;
        try {
            sftp = (ChannelSftp) session.openChannel("sftp");
            sftp.connect();
            sftp.get(remotePath, localOutput);
        } finally {
            if (sftp != null) sftp.disconnect();
        }
    }

    // 路径拼接工具
    private String combinePath(String dir, String name) {
        if (dir.equals("/")) return "/" + name;
        return dir + "/" + name;
    }

    // 原有终端相关方法
    public InputStream getInputStream() { return in; }
    public void sendCommand(String cmd) throws Exception {
        out.write((cmd + "\n").getBytes());
        out.flush();
    }

    public boolean isConnected() {
        return shellChannel != null && shellChannel.isConnected();
    }

    public void disconnect() {
        if (shellChannel != null) shellChannel.disconnect();
        if (session != null) session.disconnect();
    }
}