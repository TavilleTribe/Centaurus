package com.tavillecode.centaurus.listeners;

import com.tavillecode.centaurus.Centaurus;
import com.tavillecode.centaurus.functions.scoreboard.AScoreBoard;
import fr.mrmicky.fastboard.adventure.FastBoard;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.minimessage.MiniMessage;
import net.kyori.adventure.text.minimessage.tag.resolver.Placeholder;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.scheduler.BukkitRunnable;

/**
 * @author Interface39
 * @version 1.0
 * @description: TODO
 * @date 2024/12/27 19:41
 */
public class PlayerQuitListener implements Listener {
    private final Centaurus plugin;

    public PlayerQuitListener(Centaurus plugin) {
        this.plugin = plugin;
    }

    @EventHandler
    public void onPlayerQuit(PlayerQuitEvent e) {
        //this.plugin.getGlobalBar().getBarObject().removePlayer(e.getPlayer());
        //MiniMessage mm = MiniMessage.miniMessage();
        e.getPlayer().hideBossBar(plugin.getGlobalBar().getBarObject());
        AScoreBoard.getByPlayer(e.getPlayer()).delete();
    //        FastBoard board = AScoreBoard.boards.remove(e.getPlayer().getUniqueId());
//        if (board != null) {
//            board.delete();
//        }
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
//        }.runTaskLaterAsynchronously(Centaurus.getInstance(),5L);
    }
}
