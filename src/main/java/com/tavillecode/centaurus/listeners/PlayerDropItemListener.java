package com.tavillecode.centaurus.listeners;

import com.tavillecode.centaurus.Centaurus;
import org.bukkit.ChatColor;
import org.bukkit.entity.Item;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerDropItemEvent;
import org.bukkit.inventory.ItemStack;

import java.awt.*;
import java.util.HashMap;
import java.util.Map;

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
            if (itemStack.getItemMeta().hasLore()) {
                String type = itemStack.getItemMeta().getLore().get(0);
                if (type != null && hash.containsKey(type)) {
                    plugin.getGlowingEntitiesAPI().setGlowing(entity,e.getPlayer(), hash.get(type));
                }
            }
        }
    }

    private static Map<String,ChatColor> hash;

    static {
        hash = new HashMap<>();
        hash.put("§9工具",ChatColor.GRAY);
        hash.put("§9防具",ChatColor.LIGHT_PURPLE);
        hash.put("§9材料",ChatColor.DARK_GRAY);
        hash.put("§9魔法",ChatColor.GOLD);
        hash.put("§9食品",ChatColor.GREEN);
    }
}
