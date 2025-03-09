package com.tavillecode.centaurus.listeners;

import com.tavillecode.centaurus.functions.cum.Cumming;
import com.tavillecode.itemStorage.utils.ItemGetter;
import net.minso.chathead.API.ChatHeadAPI;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.EquipmentSlot;
import org.bukkit.inventory.ItemStack;

import java.util.Objects;

/**
 * @description: TODO 
 * @author Interface39
 * @date 2025/2/9 18:12
 * @version 1.0
 */
public class PlayerRightClickListener implements Listener {
    @EventHandler
    public void onRightClick(PlayerInteractEvent e) {
        if(Objects.equals(e.getHand(), EquipmentSlot.HAND) && e.getAction().equals(Action.RIGHT_CLICK_AIR)) {
            ItemStack item = e.getItem();
            if (item != null && item.hasItemMeta() && item.getItemMeta().hasDisplayName()) {
                if (item.getItemMeta().getDisplayName().contains("迪")) {
                    Cumming.modifyCumming(e.getPlayer());
                }
            }
        }
//        String avaterText = ChatHeadAPI.getInstance().getHeadAsString(e.getPlayer());
//        Centaurus.boards.get(e.getPlayer().getUniqueId()).updateLines(
//                Component.text(""),
//                Component.text(avaterText),
//                Component.text("")
//        );
    }
}
