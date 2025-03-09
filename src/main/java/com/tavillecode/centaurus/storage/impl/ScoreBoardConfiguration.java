package com.tavillecode.centaurus.storage.impl;

import com.tavillecode.centaurus.Centaurus;
import com.tavillecode.centaurus.functions.scoreboard.AScoreBoard;
import com.tavillecode.centaurus.storage.Storage;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.minimessage.MiniMessage;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;

import java.io.File;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author Interface39
 * @version 1.0
 * @description: TODO
 * @date 2025/2/22 20:58
 */
public class ScoreBoardConfiguration implements Storage {
    protected final boolean createIfNotExist, resource;
    protected final Centaurus plugin;
    protected FileConfiguration config;
    protected File file, path;
    protected String name;

    public ScoreBoardConfiguration(Centaurus instance, File path, String name, boolean createIfNotExist, boolean resource) {
        this.plugin = instance;
        this.path = path;
        this.name = name + ".yml";
        this.createIfNotExist = createIfNotExist;
        this.resource = resource;
        create();
    }

    public ScoreBoardConfiguration(Centaurus instance, String path, String name, boolean createIfNotExist, boolean resource) {
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
            MiniMessage mm = MiniMessage.miniMessage();
            YamlConfiguration yml = (YamlConfiguration) reloadConfig();
            List<String> content = yml.getStringList("content");
            Component title = mm.deserialize(yml.getString("title"));
            AScoreBoard.title = title;
            content.forEach(s -> {
                AScoreBoard.components.add(mm.deserialize(s));
            });
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
