package com.tavillecode.centaurus.functions.treechop;

import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;

import java.util.LinkedList;
import java.util.List;

/**
 * @author Interface39
 * @version 1.0
 * @description: TODO
 * @date 2024/12/5 19:30
 */
public class DFSTreeChopProcessor {

    private final Location startLoc;
    private final ItemStack item;

    private List<Location> visited;

    public DFSTreeChopProcessor(Location startLoc, ItemStack item) {

        this.startLoc = startLoc;
        this.item = item;
        this.visited = new LinkedList<>();
        this.chop(this.startLoc);
    }

    private void chop(Location currentLoc) {
        for (int i = 0;i < 6;i++) {
            Location newLoc = currentLoc.clone().add(AROUND[i][0],AROUND[i][1],AROUND[i][2]);
            if (isTreeBlock(newLoc) && !visited.contains(newLoc)) {
                visited.add(newLoc);
                newLoc.getBlock().breakNaturally();
                chop(newLoc);
            }
        }
        return;
    }

    public static boolean isTreeBlock(Location loc) {
        return TREE_BLOCKS.contains(loc.getBlock().getType());
    }

    private static final int[][] AROUND;

    private static final List<Material> TREE_BLOCKS;

    static {
        AROUND = new int[][]{{1,0,0},{0,1,0},{0,0,1},{-1,0,0},{0,-1,0},{0,0,-1}};

        TREE_BLOCKS = new LinkedList<>();
        TREE_BLOCKS.add(Material.OAK_LOG);
        TREE_BLOCKS.add(Material.OAK_LEAVES);
    }
}
