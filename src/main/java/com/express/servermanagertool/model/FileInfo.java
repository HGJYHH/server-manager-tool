package com.express.servermanagertool.model;

public class FileInfo {
    private String name;
    private String fullPath;
    private boolean isDirectory;
    private long size;
    private String mtime;      // 格式化的修改时间
    private String permissions;

    public FileInfo(String name, String fullPath, boolean isDirectory, long size, String mtime, String permissions) {
        this.name = name;
        this.fullPath = fullPath;
        this.isDirectory = isDirectory;
        this.size = size;
        this.mtime = mtime;
        this.permissions = permissions;
    }

    public String getName() { return name; }
    public String getFullPath() { return fullPath; }
    public boolean isDirectory() { return isDirectory; }
    public long getSize() { return size; }
    public String getMtime() { return mtime; }
    public String getPermissions() { return permissions; }
}