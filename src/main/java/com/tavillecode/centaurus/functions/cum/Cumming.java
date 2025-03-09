package com.tavillecode.centaurus.functions.cum;

import com.tavillecode.centaurus.Centaurus;
import org.bukkit.Bukkit;
import org.bukkit.entity.LlamaSpit;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;

/**
 * @author Interface39
 * @version 1.0
 * @description: TODO
 * @date 2025/3/2 16:20
 */
public class Cumming {
    public static void modifyCumming(Player player) {
        final int[] i = {0};
        new BukkitRunnable() {
            @Override
            public void run() {
                if (i[0]++ < 3) {
                    player.playSound(player.getLocation(),"fun.cumming",1.0f,0.5f);
                    player.launchProjectile(LlamaSpit.class);
                }
                else {
                    this.cancel();
                }
            }
        }.runTaskTimer(Centaurus.getInstance(),0L,10L);
    }
}
