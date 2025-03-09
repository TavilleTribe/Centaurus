package com.tavillecode.centaurus.listeners;

import com.tavillecode.centaurus.functions.mining.DFSMiningProcessor;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;

/**
 * @author Interface39
 * @version 1.0
 * @description: TODO
 * @date 2024/12/1 15:37
 */
public class PlayerBreakBlockListener implements Listener {
    @EventHandler
    public void onPlayerBreakBlock(BlockBreakEvent e) {
        if (DFSMiningProcessor.isOreBlock(e.getBlock().getLocation())) {
            new DFSMiningProcessor(e.getBlock().getLocation(),e.getPlayer().getInventory().getItemInMainHand());
        }
//        if (BFSTreeChopProcessor.isTreeBlock(e.getBlock().getLocation())) {
//            new BFSTreeChopProcessor(e.getBlock().getLocation(),e.getPlayer().getInventory().getItemInMainHand());
//        }
//        if (DFSTreeChopProcessor.isTreeBlock(e.getBlock().getLocation())) {
//            new DFSTreeChopProcessor(e.getBlock().getLocation(),e.getPlayer().getInventory().getItemInMainHand());
//        }
    }
}
