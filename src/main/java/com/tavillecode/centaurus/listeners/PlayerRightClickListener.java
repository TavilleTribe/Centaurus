package com.tavillecode.centaurus.listeners;

import com.tavillecode.centaurus.functions.cum.Cumming;
import com.tavillecode.itemStorage.utils.ItemGetter;
import net.minso.chathead.API.ChatHeadAPI;
import org.bukkit.Material;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.EquipmentSlot;
import org.bukkit.inventory.ItemStack;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

import java.util.Objects;
import java.util.Random;

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
                    e.setCancelled(true);
                    if (e.getPlayer().hasCooldown(item.getType())) {
                        return;
                    }
                    Cumming.modifyCumming(e.getPlayer());
                    e.getPlayer().setCooldown(item.getType(),60);
                    Random random = new Random();
                    if (random.nextInt(3) == 1) {
                        e.getPlayer().addPotionEffect(new PotionEffect(PotionEffectType.BLINDNESS,100,1,false));
                        e.getPlayer().addPotionEffect(new PotionEffect(PotionEffectType.SLOW,100,3,false));
                    }
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
