package com.tavillecode.centaurus.listeners;

import com.tavillecode.centaurus.Centaurus;
import org.bukkit.ChatColor;
import org.bukkit.entity.Item;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerDropItemEvent;
import org.bukkit.inventory.ItemStack;

/**
 * @author Interface39
 * @version 1.0
 * @description: TODO
 * @date 2025/3/2 15:19
 */
public class PlayerDropItemListener implements Listener {
    private final Centaurus plugin;

    public PlayerDropItemListener(Centaurus plugin) {
        this.plugin = plugin;
    }

    @EventHandler
    public void onPlayerDropItem(PlayerDropItemEvent e) throws ReflectiveOperationException {
        Item entity = e.getItemDrop();
        ItemStack itemStack = entity.getItemStack();
        if (itemStack.getItemMeta()!=null && itemStack.hasItemMeta() && itemStack.getItemMeta().hasDisplayName()) {
            entity.setCustomNameVisible(true);
            entity.customName(itemStack.displayName());
            plugin.getGlowingEntitiesAPI().setGlowing(entity,e.getPlayer(), ChatColor.GOLD);
        }
    }
}
