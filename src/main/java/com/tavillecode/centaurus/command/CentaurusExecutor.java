package com.tavillecode.centaurus.command;

import com.tavillecode.centaurus.Centaurus;
import com.tavillecode.centaurus.functions.books.Book;
import com.tavillecode.centaurus.utils.MessageSection;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

/**
 * @author Interface39
 * @version 1.0
 * @description: TODO
 * @date 2025/2/12 14:36
 */
public class CentaurusExecutor implements CommandExecutor {
    private final Centaurus plugin;

    public CentaurusExecutor(Centaurus plugin) {
        this.plugin = plugin;
    }

    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, String s, String[] args) {
        if (sender instanceof Player player) {
            if (!player.isOp()) {
                return true;
            }
            if (args.length == 0) {
                player.performCommand("centaurus help");
            } else if (args.length == 1) {
                String parameter = args[0];
                if (parameter.equalsIgnoreCase("help")) {
                    MessageSection.HelpMessages(player);
                }
                if (parameter.equalsIgnoreCase("reload")) {
                    plugin.getBookYaml().reload();
                    plugin.getBarYaml().reload();
                    plugin.getTabListYaml().reload();
                    plugin.getBoardYaml().reload();
                    player.sendMessage("§a重载成功!");
                    MessageSection.EnableMessages();
                }
            } else if (args.length == 2) {
                String parameter = args[0];
                if (parameter.equalsIgnoreCase("book")) {
                    try {
                        Book.BOOKS_MAP.get(args[1]).openBook(player);
                    } catch (Exception ignored) {}
                }
            }
        }
        return true;
    }
}
