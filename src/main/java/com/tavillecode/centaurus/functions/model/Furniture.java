package com.tavillecode.centaurus.functions.model;

import com.tavillecode.centaurus.Centaurus;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.block.Block;
import org.bukkit.block.BlockFace;
import org.bukkit.entity.ArmorStand;
import org.bukkit.entity.ItemFrame;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.hanging.HangingBreakEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.EquipmentSlot;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.persistence.PersistentDataType;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.util.Vector;
import org.spigotmc.event.entity.EntityDismountEvent;

import java.util.Objects;
import java.util.UUID;

/**
 * @author Interface39
 * @version 1.0
 * @description: TODO
 * @date 2025/5/27 21:44
 */
public class Furniture {
    public static void registerListener() {
        Listener listener = new Listener() {
            @EventHandler
            public void onPlayerRightClickBlock(PlayerInteractEvent e) {
                if (Objects.equals(e.getHand(), EquipmentSlot.HAND) && e.getClickedBlock() != null) {
                    if (e.getAction().equals(Action.RIGHT_CLICK_BLOCK)) {
                        if (e.getPlayer().isSneaking()) {
                            Block clicked = e.getClickedBlock();
                            Location clickedLoc = clicked.getLocation().clone().add(new Vector(0,1,0));
                            if (clickedLoc.getBlock().getType().equals(Material.AIR)) {
                                ItemFrame displayer = (ItemFrame) clicked.getWorld().spawn(clickedLoc, ItemFrame.class);
                                displayer.setFacingDirection(BlockFace.UP);
                                displayer.setVisible(false);
                                e.getPlayer().sendMessage(displayer.getLocation().toString());

                                ItemStack sofa = new ItemStack(Material.STICK);
                                ItemMeta sofaMeta = sofa.getItemMeta();
                                sofaMeta.setCustomModelData(1001);
                                sofa.setItemMeta(sofaMeta);
                                displayer.setItem(sofa);

                                NamespacedKey key = new NamespacedKey(Centaurus.getInstance(), "furniture");
                                displayer.getPersistentDataContainer().set(key, PersistentDataType.BOOLEAN, true);

                                clickedLoc.getBlock().setType(Material.BARRIER);
                            }
                        }
                        else {
                            if (e.getClickedBlock().getType().equals(Material.BARRIER)) {
                                Location loc = e.getClickedBlock().getLocation().add(new Vector(0.5, -1.15, 0.5));
                                ArmorStand seat = (ArmorStand) loc.getWorld().spawn(loc.clone(), ArmorStand.class);
                                seat.setInvulnerable(true);
                                seat.setGravity(false);
                                seat.setVisible(false);
                                seat.setCanMove(false);

                                NamespacedKey sit = new NamespacedKey(Centaurus.getInstance(), "seat");
                                seat.getPersistentDataContainer().set(sit, PersistentDataType.BOOLEAN, true);

                                seat.addPassenger(e.getPlayer());
                            }
                        }
                    }
                    else if (e.getAction().equals(Action.LEFT_CLICK_BLOCK)) {

                    }
                }
            }

            @EventHandler
            public void onUniqueItemFrameDamage(HangingBreakEvent e) {
                if (e.getEntity() instanceof ItemFrame itemFrame) {
                    if (e.getCause().equals(HangingBreakEvent.RemoveCause.OBSTRUCTION) || e.getCause().equals(HangingBreakEvent.RemoveCause.PHYSICS)) {
                        NamespacedKey key = new NamespacedKey(Centaurus.getInstance(), "furniture");
                        if (itemFrame.getPersistentDataContainer().has(key) && Objects.equals(itemFrame.getPersistentDataContainer().get(key, PersistentDataType.BOOLEAN), true)) {
                            e.setCancelled(true);
                        }
                    }
                }
            }

            @EventHandler
            public void onExitSeat(EntityDismountEvent e) {
                if (e.getDismounted() instanceof ArmorStand seat) {
                    NamespacedKey key = new NamespacedKey(Centaurus.getInstance(), "seat");
                    if (seat.getPersistentDataContainer().has(key) && Objects.equals(seat.getPersistentDataContainer().get(key, PersistentDataType.BOOLEAN), true)) {
                        new BukkitRunnable() {
                            @Override
                            public void run() {
                                e.getDismounted().remove();
                            }
                        }.runTaskLater(Centaurus.getInstance(),10L);

                    }
                }
            }
        };
        Bukkit.getPluginManager().registerEvents(listener,Centaurus.getInstance());
    }


}
