package com.tavillecode.centaurus.functions.treechop;

import com.tavillecode.centaurus.functions.Processor;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * @author Interface39
 * @version 1.0
 * @description: TODO
 * @date 2024/11/30 17:15
 */
public class BFSTreeChopProcessor implements Processor {

    private final Location startLoc;
    private final ItemStack item;

    private Queue<Location> queue;
    private List<Location> inQueue;

    public BFSTreeChopProcessor(Location startLoc, ItemStack item) {
        this.startLoc = startLoc;
        this.item = item;
        this.queue = new LinkedList<>();
        this.inQueue = new ArrayList<>();
        this.chop();
    }

    private boolean isInQueue(Location loc) {
        return this.inQueue.contains(loc);
    }

    private void chop() {
        this.startLoc.getBlock().breakNaturally();

        this.queue.offer(this.startLoc);
        this.inQueue.add(this.startLoc);

        while (!this.queue.isEmpty()) {
            Location currentLoc = this.queue.poll();
            for (int i = 0; i < 6; i++) {
                if (!this.isInQueue(currentLoc.clone().add(AROUND[i][0], AROUND[i][1], AROUND[i][2])) && isTreeBlock(currentLoc.clone().add(AROUND[i][0], AROUND[i][1], AROUND[i][2]))) {
                    Location newLoc = currentLoc.clone().add(AROUND[i][0], AROUND[i][1], AROUND[i][2]);
                    newLoc.getBlock().breakNaturally();
                    this.queue.offer(newLoc);
                    this.inQueue.add(newLoc);
                }
            }
        }

        if (this.item != null) {
            this.item.setDurability((short) (this.item.getDurability() + (short) 5));
        }
    }

    public static boolean isTreeBlock(Location loc) {
        return TREE_BLOCKS.contains(loc.getBlock().getType());
    }

    private final static int[][] AROUND;

    private final static List<Material> TREE_BLOCKS;

    static {
        AROUND = new int[][]{{1,0,0},{-1,0,0},{0,1,0},{0,-1,0},{0,0,1},{0,0,-1}};

        TREE_BLOCKS = new LinkedList<>();
        //树叶
//        TREE_BLOCKS.add(Material.ACACIA_LEAVES);
//        TREE_BLOCKS.add(Material.AZALEA_LEAVES);
//        TREE_BLOCKS.add(Material.BIRCH_LEAVES);
//        TREE_BLOCKS.add(Material.CHERRY_LEAVES);
//        TREE_BLOCKS.add(Material.JUNGLE_LEAVES);
//        TREE_BLOCKS.add(Material.DARK_OAK_LEAVES);
//        TREE_BLOCKS.add(Material.MANGROVE_LEAVES);
        TREE_BLOCKS.add(Material.OAK_LEAVES);
//        TREE_BLOCKS.add(Material.FLOWERING_AZALEA_LEAVES);
//        TREE_BLOCKS.add(Material.SPRUCE_LEAVES);
        //木头
        TREE_BLOCKS.add(Material.OAK_LOG);
    }
}
