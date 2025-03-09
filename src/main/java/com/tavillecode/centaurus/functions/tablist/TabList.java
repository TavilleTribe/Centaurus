package com.tavillecode.centaurus.functions.tablist;

import net.kyori.adventure.text.minimessage.MiniMessage;
import net.kyori.adventure.text.minimessage.tag.resolver.Placeholder;
import org.bukkit.entity.Player;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @author Interface39
 * @version 1.0
 * @description: TODO
 * @date 2025/2/12 14:49
 */
public class TabList {
    private final String head;
    private final String tail;

    public TabList(String head, String tail) {
        this.head = head;
        this.tail = tail;
    }

    public void setTabList(Player player) {
        Date date = new Date();
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy.MM.dd");
        player.sendPlayerListHeaderAndFooter(MiniMessage.miniMessage().deserialize(this.head),MiniMessage.miniMessage().deserialize(this.tail, Placeholder.unparsed("time",formatter.format(date))));
    }

    public static String HEAD;

    public static String TAIL;
}
