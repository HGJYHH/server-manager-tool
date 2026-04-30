package com.express.servermanagertool.util;

import com.express.servermanagertool.model.Credential;

import java.io.*;
import java.util.Properties;

public class ConfigManager {
    private static final String CONFIG_FILE = System.getProperty("user.home") +
            File.separator + ".server_manager.conf";

    public static void save(Credential cred) throws IOException {
        Properties props = new Properties();
        props.setProperty("ip", cred.getIp());
        props.setProperty("username", cred.getUsername());
        props.setProperty("password", cred.getPassword());
        try (FileOutputStream fos = new FileOutputStream(CONFIG_FILE)) {
            props.store(fos, "Server Manager Credentials");
        }
    }

    public static Credential load() {
        File file = new File(CONFIG_FILE);
        if (!file.exists()) return null;
        Properties props = new Properties();
        try (FileInputStream fis = new FileInputStream(file)) {
            props.load(fis);
            String ip = props.getProperty("ip", "");
            String username = props.getProperty("username", "");
            String password = props.getProperty("password", "");
            if (ip.isEmpty() || username.isEmpty()) return null;
            return new Credential(ip, username, password);
        } catch (IOException ignored) {
            return null;
        }
    }
}