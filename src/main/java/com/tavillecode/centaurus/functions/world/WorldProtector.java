package com.tavillecode.centaurus.functions.world;

import com.tavillecode.centaurus.Centaurus;
import org.bukkit.Bukkit;
import org.bukkit.entity.ArmorStand;
import org.bukkit.event.EventHandler;
import org.bukkit.event.HandlerList;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.player.PlayerInteractAtEntityEvent;

/**
 * @author Interface39
 * @version 1.0
 * @description: TODO
 * @date 2025/3/28 20:06
 */
public class WorldProtector {
    private Listener listener;

    public void init() {
        this.listener = new Listener() {
            @EventHandler
            public void onArmorStandDamage(EntityDamageEvent e) {
                if (e.getEntity() instanceof ArmorStand) {
                    if (damageSwitch) {
                        return;
                    }
                    e.setCancelled(true);
                }
            }

            @EventHandler
            public void onPlayerInteractArmorStand(PlayerInteractAtEntityEvent e) {
                if (e.getPlayer().isOp()) {
                    return;
                }
                if (e.getRightClicked() instanceof ArmorStand) {
                    e.setCancelled(true);
                }
            }
        };
        Bukkit.getServer().getPluginManager().registerEvents(listener, Centaurus.getInstance());
    }

    public void disable() {
        HandlerList.unregisterAll(this.listener);
    }

    public static boolean damageSwitch;

    static {
        damageSwitch = false;
    }
}
