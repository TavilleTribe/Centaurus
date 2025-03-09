package com.tavillecode.centaurus.storage.impl;

import com.tavillecode.centaurus.Centaurus;
import com.tavillecode.centaurus.functions.bossbar.DynamicBar;
import com.tavillecode.centaurus.storage.Storage;
import org.bukkit.boss.BarColor;
import org.bukkit.boss.BarStyle;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;

import java.io.File;
import java.util.ArrayList;

/**
 * @author Interface39
 * @version 1.0
 * @description: TODO
 * @date 2024/12/27 19:23
 */
public class BarConfiguration implements Storage {
    protected final boolean createIfNotExist, resource;
    protected final Centaurus plugin;
    protected FileConfiguration config;
    protected File file, path;
    protected String name;

    public BarConfiguration(Centaurus instance, File path, String name, boolean createIfNotExist, boolean resource) {
        this.plugin = instance;
        this.path = path;
        this.name = name + ".yml";
        this.createIfNotExist = createIfNotExist;
        this.resource = resource;
        create();
    }

    public BarConfiguration(Centaurus instance, String path, String name, boolean createIfNotExist, boolean resource) {
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
            String text = yml.getString("Text").replaceAll("“","\"").replaceAll("”","\"");//.replaceAll("&","\u00a7");
            BarColor barColor = BarColor.valueOf(yml.getString("BarColor"));
            BarStyle barStyle = BarStyle.valueOf(yml.getString("BarStyle"));
            String symbol = yml.getString("Symbol");
            ArrayList<String> dynamic = (ArrayList<String>) yml.getStringList("Dynamic");
            //dynamic.replaceAll(string -> string.replaceAll("&","\u00a7"));
            DynamicBar.TEXT = text;
            DynamicBar.BAR_COLOR = barColor;
            DynamicBar.BAR_STYLE = barStyle;
            DynamicBar.SYMBOL = symbol;
            DynamicBar.DYNAMIC = dynamic;
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

