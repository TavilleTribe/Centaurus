package com.tavillecode.centaurus.listeners;

import com.tavillecode.centaurus.Centaurus;
import org.bukkit.*;
import org.bukkit.entity.Player;
import org.bukkit.event.Event;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockFormEvent;
import org.bukkit.event.inventory.CraftItemEvent;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author Interface39
 * @version 1.0
 * @description: TODO
 * @date 2025/5/25 19:01
 */
public class BlockFormListener implements Listener {
    @EventHandler
    public void onInventoryClick(InventoryClickEvent e) {
        if (e.getCurrentItem() != null && e.getCurrentItem().getType().equals(Material.WOODEN_PICKAXE)) {
            e.getCurrentItem().setDurability((short) - (3 - e.getCurrentItem().getType().getMaxDurability()));
        }
    }

    @EventHandler
    public void onBlockForm(BlockFormEvent e) {
        if (e.getNewState().getType().equals(Material.COBBLESTONE)) {
            Location loc = e.getNewState().getLocation();
            if (map.get(loc)==null) {
                map.put(loc,1);
            }
            else if (map.get(loc)==10){
                map.put(loc,0);
                e.getNewState().setType(Material.IRON_ORE);
                List<Player> players = (List<Player>) loc.getNearbyPlayers(3);
                players.forEach(p-> {
                    p.playSound(p.getLocation(), Sound.BLOCK_ANVIL_LAND,1.0F,0.5F);
                    try {
                        Centaurus.getInstance().getGlowingBlocksAPI().setGlowing(e.getNewState().getBlock(),p, ChatColor.YELLOW);
                    } catch (ReflectiveOperationException ex) {
                        throw new RuntimeException(ex);
                    }
                });
                new BukkitRunnable() {
                    @Override
                    public void run() {
                        for (int i = 0;i < players.size();i++) {
                            try {
                                Centaurus.getInstance().getGlowingBlocksAPI().unsetGlowing(e.getNewState().getBlock(),players.get(i));
                            } catch (ReflectiveOperationException ex) {
                                throw new RuntimeException(ex);
                            }
                        }

                    }
                }.runTaskLater(Centaurus.getInstance(),15L);

            }
            else {
                map.put(loc,map.get(loc)+1);
            }
        }
    }

    public static Map<Location,Integer> map = new HashMap<>();
}
