package com.tavillecode.centaurus.functions.books;

import com.tavillecode.centaurus.utils.CustomSound;
import net.kyori.adventure.text.Component;
import org.bukkit.entity.Player;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author Interface39
 * @version 1.0
 * @description: TODO
 * @date 2025/2/10 23:35
 */
public class Book {
    private final Component title;
    private final Component author;
    private final List<Component> pages;

    private final CustomSound customSound;

    public Book(Component title, Component author, List<Component> pages, CustomSound customSound) {
        this.title = title;
        this.author = author;
        this.pages = pages;
        this.customSound = customSound;
    }

    public void openBook(Player player) {
        net.kyori.adventure.inventory.Book book = net.kyori.adventure.inventory.Book.book(this.title,this.author,this.pages);
        player.openBook(book);
        this.customSound.play(player);
    }

    public static Map<String,Book> BOOKS_MAP;

    static {
        BOOKS_MAP = new HashMap<>();
    }
}
