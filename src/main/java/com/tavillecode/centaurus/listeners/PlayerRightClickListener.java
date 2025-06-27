package com.tavillecode.centaurus.listeners;

import com.tavillecode.centaurus.functions.cum.Cumming;
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
//        if (e.getPlayer().isOp()) {
//            Component component = Component.text("你好,我是你的朋友").font(Key.key("fonts/comic/line_1"));
//            Component component1 = Component.text("\uF808\uF808\uF808\uF808\uF808\uF808\uF808\uF808\uF803我是来帮助你的").font(Key.key("fonts/comic/line_2"));
//            component = component.append(component1);
//
//            e.getPlayer().sendActionBar(component);
//        }
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
        else if (e.getAction().equals(Action.RIGHT_CLICK_BLOCK)) {

        }
//        else if (e.getAction().equals(Action.RIGHT_CLICK_BLOCK)) {
//            if (Objects.requireNonNull(e.getClickedBlock()).getType().equals(Material.JUKEBOX)) {
//                if (inBox != null) {
//                    e.getPlayer().getWorld().dropItem(e.getClickedBlock().getLocation().clone().add(0,0.5,0),inBox);
//                    e.getPlayer().stopSound("fun.dick");
//                    inBox = null;
//                }
//                ItemStack item = e.getItem();
//                if (item != null && item.hasItemMeta() && item.getItemMeta().hasDisplayName() && item.getItemMeta().hasLore()) {
//                    if (item.getItemMeta().getLore().get(0).contains("dick")) {
//                        e.getPlayer().playSound(e.getPlayer().getLocation(), "fun.dick", 1.0F, 1.0F);
//                        e.getPlayer().sendActionBar(MiniMessage.miniMessage().deserialize("<#F0F01C>正在播放：Interface39 - dick"));
//                        inBox = item;
//                        e.getPlayer().getInventory().setItemInMainHand(null);
//                    }
//                }
//            }
//        }
//        String avaterText = ChatHeadAPI.getInstance().getHeadAsString(e.getPlayer());
//        Centaurus.boards.get(e.getPlayer().getUniqueId()).updateLines(
//                Component.text(""),
//                Component.text(avaterText),
//                Component.text("")
//        );
    }

    private static ItemStack inBox = null;
}
