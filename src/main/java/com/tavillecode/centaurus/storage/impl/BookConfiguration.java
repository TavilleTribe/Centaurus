package com.tavillecode.centaurus.storage.impl;

import com.tavillecode.centaurus.Centaurus;
import com.tavillecode.centaurus.functions.books.Book;
import com.tavillecode.centaurus.storage.Storage;
import com.tavillecode.centaurus.utils.CustomSound;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.minimessage.MiniMessage;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Interface39
 * @version 1.0
 * @description: TODO
 * @date 2025/2/11 13:57
 */
public class BookConfiguration implements Storage {
    protected final boolean createIfNotExist, resource;
    protected final Centaurus plugin;
    protected FileConfiguration config;
    protected File file, path;
    protected String name;

    public BookConfiguration(Centaurus instance, File path, String name, boolean createIfNotExist, boolean resource) {
        this.plugin = instance;
        this.path = path;
        this.name = name + ".yml";
        this.createIfNotExist = createIfNotExist;
        this.resource = resource;
        create();
    }

    public BookConfiguration(Centaurus instance, String path, String name, boolean createIfNotExist, boolean resource) {
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
            Book.BOOKS_MAP.clear();
            YamlConfiguration yml = (YamlConfiguration) reloadConfig();
            MiniMessage mm = MiniMessage.miniMessage();
            for (String key1:yml.getKeys(false)) {
                Component title = mm.deserialize(yml.getConfigurationSection(key1).getString("title"));
                Component author = mm.deserialize(yml.getConfigurationSection(key1).getString("author"));
                CustomSound customSound = new CustomSound(yml.getConfigurationSection(key1).getString("custom_sound"));
                List<Component> pages = new ArrayList<>();
                for (String key2:yml.getConfigurationSection(key1).getConfigurationSection("pages").getKeys(false)) {
                    Component text = Component.text("");
                    List<String> content = yml.getConfigurationSection(key1).getConfigurationSection("pages").getStringList(key2);
                    text = text.append(mm.deserialize(content.get(0)));
                    for (int i = 1;i < content.size();i++) {
                        text = text.appendNewline().append(mm.deserialize(content.get(i)));
                    }
                    pages.add(text);
                }
                Book book = new Book(title,author,pages,customSound);
                Book.BOOKS_MAP.put(key1,book);
            }
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
