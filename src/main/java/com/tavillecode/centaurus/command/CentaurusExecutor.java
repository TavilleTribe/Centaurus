package com.tavillecode.centaurus.command;

import com.tavillecode.centaurus.Centaurus;
import com.tavillecode.centaurus.functions.books.Book;
import com.tavillecode.centaurus.functions.world.WorldProtector;
import com.tavillecode.centaurus.utils.MessageSection;
import com.tavillecode.centaurus.utils.StringUtils;
import net.kyori.adventure.key.Key;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.minimessage.MiniMessage;
import net.kyori.adventure.title.Title;
import net.kyori.adventure.title.TitlePart;
import org.bukkit.Bukkit;
import org.bukkit.Sound;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;
import org.checkerframework.checker.units.qual.A;
import org.jetbrains.annotations.NotNull;

import java.time.Duration;
import java.util.Objects;
import java.util.Random;

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
                if (parameter.equalsIgnoreCase("chars")) {
                    MiniMessage mm = MiniMessage.miniMessage();
                    player.sendMessage(mm.deserialize("<!i><green>前进 +<white>: "));
                    player.sendMessage(mm.deserialize("<click:copy_to_clipboard:\uF821><hover:show_text:'点击复制'>前进1格</hover></click>"));
                    player.sendMessage(mm.deserialize("<click:copy_to_clipboard:\uF822><hover:show_text:'点击复制'>前进2格</hover></click>"));
                    player.sendMessage(mm.deserialize("<click:copy_to_clipboard:\uF823><hover:show_text:'点击复制'>前进3格</hover></click>"));
                    player.sendMessage(mm.deserialize("<click:copy_to_clipboard:\uF824><hover:show_text:'点击复制'>前进4格</hover></click>"));
                    player.sendMessage(mm.deserialize("<click:copy_to_clipboard:\uF825><hover:show_text:'点击复制'>前进5格</hover></click>"));
                    player.sendMessage(mm.deserialize("<click:copy_to_clipboard:\uF826><hover:show_text:'点击复制'>前进6格</hover></click>"));
                    player.sendMessage(mm.deserialize("<click:copy_to_clipboard:\uF827><hover:show_text:'点击复制'>前进7格</hover></click>"));
                    player.sendMessage(mm.deserialize("<click:copy_to_clipboard:\uF828><hover:show_text:'点击复制'>前进8格</hover></click>"));
                    player.sendMessage(mm.deserialize("<click:copy_to_clipboard:\uF829><hover:show_text:'点击复制'>前进16格</hover></click>"));
                    player.sendMessage(mm.deserialize("<click:copy_to_clipboard:\uF82A><hover:show_text:'点击复制'>前进32格</hover></click>"));
                    player.sendMessage(mm.deserialize("<click:copy_to_clipboard:\uF82B><hover:show_text:'点击复制'>前进64格</hover></click>"));
                    player.sendMessage(mm.deserialize("<click:copy_to_clipboard:\uF82C><hover:show_text:'点击复制'>前进128格</hover></click>"));
                    player.sendMessage(mm.deserialize("<!i><red>退格 -<white>: "));
                    player.sendMessage(mm.deserialize("<click:copy_to_clipboard:\uF801><hover:show_text:'点击复制'>退格1格</hover></click>"));
                    player.sendMessage(mm.deserialize("<click:copy_to_clipboard:\uF802><hover:show_text:'点击复制'>退格2格</hover></click>"));
                    player.sendMessage(mm.deserialize("<click:copy_to_clipboard:\uF803><hover:show_text:'点击复制'>退格3格</hover></click>"));
                    player.sendMessage(mm.deserialize("<click:copy_to_clipboard:\uF804><hover:show_text:'点击复制'>退格4格</hover></click>"));
                    player.sendMessage(mm.deserialize("<click:copy_to_clipboard:\uF805><hover:show_text:'点击复制'>退格5格</hover></click>"));
                    player.sendMessage(mm.deserialize("<click:copy_to_clipboard:\uF806><hover:show_text:'点击复制'>退格6格</hover></click>"));
                    player.sendMessage(mm.deserialize("<click:copy_to_clipboard:\uF807><hover:show_text:'点击复制'>退格7格</hover></click>"));
                    player.sendMessage(mm.deserialize("<click:copy_to_clipboard:\uF808><hover:show_text:'点击复制'>退格8格</hover></click>"));
                    player.sendMessage(mm.deserialize("<click:copy_to_clipboard:\uF809><hover:show_text:'点击复制'>退格16格</hover></click>"));
                    player.sendMessage(mm.deserialize("<click:copy_to_clipboard:\uF80A><hover:show_text:'点击复制'>退格32格</hover></click>"));
                    player.sendMessage(mm.deserialize("<click:copy_to_clipboard:\uF80B><hover:show_text:'点击复制'>退格64格</hover></click>"));
                    player.sendMessage(mm.deserialize("<click:copy_to_clipboard:\uF80C><hover:show_text:'点击复制'>退格128格</hover></click>"));
                }
            } else if (args.length == 2) {
                String parameter = args[0];
                if (parameter.equalsIgnoreCase("book")) {
                    try {
                        Book.BOOKS_MAP.get(args[1]).openBook(player);
                    } catch (Exception ignored) {}
                }
                if (parameter.equalsIgnoreCase("armorstand")) {
                    WorldProtector.damageSwitch = Boolean.parseBoolean(args[1]);
                }
                if (parameter.equalsIgnoreCase("tpa")) {
                    if (Objects.requireNonNull(Bukkit.getPlayer(args[1])).isOnline()) {
                        final int[] i = {0};
                        new BukkitRunnable() {
                            @Override
                            public void run() {
                                if (i[0] > 16) {
                                    new BukkitRunnable() {
                                        @Override
                                        public void run() {
                                            player.teleport(Objects.requireNonNull(Bukkit.getPlayer(args[1])).getLocation());
                                        }
                                    }.runTask(Centaurus.getInstance());
                                    cancel();;
                                }
                                else {
                                    player.sendTitlePart(TitlePart.TITLE, Component.text(tags[(i[0]++)%8]));
                                    player.sendTitlePart(TitlePart.SUBTITLE,Component.text("ᇸ"));
                                    player.sendTitlePart(TitlePart.TIMES, Title.Times.times(Duration.ofMillis(10),Duration.ofMillis(500),Duration.ofMillis(300)));
                                }

                            }
                        }.runTaskTimerAsynchronously(Centaurus.getInstance(),0L,7L);
                    }
                }
            } else if (args.length == 4) {
                String parameter = args[0];
                if (parameter.equalsIgnoreCase("font")) {
                    String line1 = args[1];
                    int line_1_len = line1.length();
                    String space = args[2];
                    if (space.equalsIgnoreCase("auto")) {
                        space = StringUtils.getSpaceStringGreedily(line1);
                    }
                    String line2 = args[3];
                    int line_2_len = line2.length();
                    final int[] index = {0};
                    final int[] flag = {0};
                    MiniMessage mm = MiniMessage.miniMessage();
                    final Component[] component = {Component.text("")};
                    Random random = new Random();

                    String finalSpace = space;

                    player.playSound(player.getLocation(),Sound.ENTITY_ITEM_PICKUP,1.0F,0.5F);
                    new BukkitRunnable() {
                        @Override
                        public void run() {
                            if (flag[0] == 0) {
                                if (index[0] == line_1_len) {
                                    flag[0] = 1;
                                    index[0] = 0;
                                    component[0] = component[0].append(Component.text(finalSpace));
                                }
                                else {
                                    component[0] = component[0].append(mm.deserialize(String.valueOf(line1.charAt(index[0]++))).font(Key.key("fonts/comic/line_1")));
                                    player.sendActionBar(component[0]);
                                    player.playSound(player.getLocation(), Sound.ENTITY_ITEM_PICKUP,1.0F,random.nextFloat(1.3f)+0.6f);
                                }
                            }
                            else if (flag[0] == 1){
                                if (index[0] == line_2_len) {
                                    cancel();
                                }
                                else {
                                    component[0] = component[0].append(mm.deserialize(String.valueOf(line2.charAt(index[0]++))).font(Key.key("fonts/comic/line_2")));
                                    player.sendActionBar(component[0]);
                                    player.playSound(player.getLocation(), Sound.ENTITY_ITEM_PICKUP,1.0F,random.nextFloat(1.3f)+0.6f);
                                }
                            }
//                            Component component = mm.deserialize(line1).font(Key.key("fonts/comic/line_1"));
//                            Component component1 = mm.deserialize(line2).font(Key.key("fonts/comic/line_2"));
//                            component = component.append(component1);

                        }
                    }.runTaskTimerAsynchronously(Centaurus.getInstance(),0L,1L);

                }
            }
        }
        return true;
    }

    private static String[] tags = new String[8];

    static {
        for (int i = 0;i < 8;i++) {
            tags[i] = StringUtils.unicodeToString('\\' + "ub21" + (i+2));
        }
    }
}
