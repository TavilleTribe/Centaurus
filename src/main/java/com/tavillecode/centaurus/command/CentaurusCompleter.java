package com.tavillecode.centaurus.command;

import com.tavillecode.centaurus.functions.books.Book;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Interface39
 * @version 1.0
 * @description: TODO
 * @date 2025/2/12 14:45
 */
public class CentaurusCompleter implements TabCompleter {
    @Override
    public List<String> onTabComplete(@NotNull CommandSender commandSender, @NotNull Command command, @NotNull String s, String[] args) {
        ArrayList<String> list = new ArrayList<>();
        if (args.length == 1) {
            list.add("font");
            list.add("help");
            list.add("reload");
            list.add("armorstand");
            list.add("book");
            list.add("chars");
            list.add("tpa");
        }
        else if (args.length == 2) {
            if (args[0].equalsIgnoreCase("armorstand"))
            {
                list.add("true");
                list.add("false");
            }
            if (args[0].equalsIgnoreCase("book")) {
                list.addAll(Book.BOOKS_MAP.keySet());
            }
            if (args[0].equalsIgnoreCase("tpa")) {
                Bukkit.getOnlinePlayers().forEach(player -> list.add(player.getName()));
            }
        }
        return list;
    }
}
