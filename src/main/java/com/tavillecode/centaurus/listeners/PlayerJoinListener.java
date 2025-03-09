package com.tavillecode.centaurus.listeners;

import com.tavillecode.centaurus.Centaurus;
import com.tavillecode.centaurus.functions.scoreboard.AScoreBoard;
import net.kyori.adventure.text.minimessage.MiniMessage;
import net.minso.chathead.API.ChatHeadAPI;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.scheduler.BukkitRunnable;

/**
 * @author Interface39
 * @version 1.0
 * @description: TODO
 * @date 2024/12/27 19:40
 */
public class PlayerJoinListener implements Listener {
    private final Centaurus plugin;

    public PlayerJoinListener(Centaurus plugin) {
        this.plugin = plugin;
    }

    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent e) {
        //this.plugin.getGlobalBar().getBarObject().addPlayer(e.getPlayer());
        //plugin.getUpdater().sendPlayer(e.getPlayer());
        MiniMessage mm = MiniMessage.miniMessage();
        e.getPlayer().showBossBar(plugin.getGlobalBar().getBarObject());
        plugin.getGlobalTabList().setTabList(e.getPlayer());
        new AScoreBoard(e.getPlayer());
//        board.updateTitle(MiniMessage.miniMessage().deserialize("\ue029"));
//        new BukkitRunnable() {
//            @Override
//            public void run() {
//                for (Player player:Bukkit.getOnlinePlayers()) {
//                    AScoreBoard.boards.get(player.getUniqueId()).updateLines(
//                            Component.text(""),
//                            mm.deserialize("<!italic>\ue032 <gray>ʀᴀɴᴋꜱ: <white><rank>",Placeholder.unparsed("rank","\ue034")),
//                            mm.deserialize("<!italic>\ue033 <gray>ᴢᴏɴᴇꜱ: <bold><#00FF7F><zone>",Placeholder.unparsed("zone","SAFE")),
//                            mm.deserialize("<!italic>\ue035 <gray>ᴋɪʟʟꜱ: <white><kill>",Placeholder.unparsed("kill","0")),
//                            Component.text(""),
//                            mm.deserialize("<!italic>\ue030 <gray>ᴄᴏɪɴꜱ: <white><eco>.0¥", Placeholder.unparsed("eco","0")),
//                            mm.deserialize("<!italic>\ue031 <gray>ᴄʀʏꜱᴛ: <white><cryst>", Placeholder.unparsed("cryst","0")),
//                            Component.text(""),
//                            mm.deserialize("<!italic>\ue036 <gray>ᴏɴʟɪɴᴇ: <white><on>/100",Placeholder.unparsed("on",String.valueOf(Bukkit.getOnlinePlayers().size()))),
//                            Component.text(""),
//                            mm.deserialize("<yellow>play.gpnumc.com")
//                    );
//                }
//            }
//        }.runTaskLaterAsynchronously(plugin,5L);
    }
}
