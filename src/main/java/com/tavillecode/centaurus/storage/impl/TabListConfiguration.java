package com.tavillecode.centaurus.storage.impl;

import com.tavillecode.centaurus.Centaurus;
import com.tavillecode.centaurus.functions.tablist.TabList;
import com.tavillecode.centaurus.storage.Storage;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;

import java.io.File;

/**
 * @author Interface39
 * @version 1.0
 * @description: TODO
 * @date 2025/2/12 14:55
 */
public class TabListConfiguration implements Storage {
    protected final boolean createIfNotExist, resource;
    protected final Centaurus plugin;
    protected FileConfiguration config;
    protected File file, path;
    protected String name;

    public TabListConfiguration(Centaurus instance, File path, String name, boolean createIfNotExist, boolean resource) {
        this.plugin = instance;
        this.path = path;
        this.name = name + ".yml";
        this.createIfNotExist = createIfNotExist;
        this.resource = resource;
        create();
    }

    public TabListConfiguration(Centaurus instance, String path, String name, boolean createIfNotExist, boolean resource) {
        this(instance, new File(path), name, createIfNotExist, resource);
    }

    public FileConfiguration getConfig() {
        return config;
    }

    @Override
    public void save() {
        try {
            config.save(file);
        } catch (Exception exc) {
            exc.printStackTrace();
        }
    }

    public File reloadFile() {
        file = new File(path, name);
        return file;
    }

    public FileConfiguration reloadConfig() {
        config = YamlConfiguration.loadConfiguration(file);
        return config;
    }

    @SuppressWarnings("all")
    @Override
    public void reload() {
        try {
            YamlConfiguration yml = (YamlConfiguration) reloadConfig();
            String head = yml.getString("head");
            String tail = yml.getString("tail");
            //dynamic.replaceAll(string -> string.replaceAll("&","\u00a7"));
            TabList.HEAD = head;
            TabList.TAIL = tail;
        } catch (Exception ex) {
            System.out.print("无法重载!");
        }
    }

    @SuppressWarnings("all")
    @Override
    public void create() {
        if (file == null) {
            reloadFile();
        }
        if (!createIfNotExist || file.exists()) {
            return;
        }
        file.getParentFile().mkdirs();
        if (resource) {
            plugin.saveResource(name, false);
        } else {
            try {
                file.createNewFile();
            } catch (Exception exc) {
                exc.printStackTrace();
            }
        }
        if (config == null) {
            reloadConfig();
        }
    }
}
