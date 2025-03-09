package com.tavillecode.centaurus.functions.mining;

import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;

import java.util.LinkedList;
import java.util.List;

/**
 * @author Interface39
 * @version 1.0
 * @description: TODO
 * @date 2025/3/8 1:05
 */
public class DFSMiningProcessor {
    private final Location startLoc;
    private final ItemStack item;

    private List<Location> visited;

    public DFSMiningProcessor(Location startLoc, ItemStack item) {

        this.startLoc = startLoc;
        this.item = item;
        this.visited = new LinkedList<>();
        this.mine(this.startLoc);
    }

    private void mine(Location currentLoc) {
        for (int i = 0;i < 6;i++) {
            Location newLoc = currentLoc.clone().add(AROUND[i][0],AROUND[i][1],AROUND[i][2]);
            if (isOreBlock(newLoc) && !visited.contains(newLoc)) {
                visited.add(newLoc);
                newLoc.getBlock().breakNaturally();
                mine(newLoc);
            }
        }
        return;
    }

    public static boolean isOreBlock(Location loc) {
        return ORE_BLOCKS.contains(loc.getBlock().getType());
    }

    private static final int[][] AROUND;

    private static final List<Material> ORE_BLOCKS;

    static {
        AROUND = new int[][]{{1,0,0},{0,1,0},{0,0,1},{-1,0,0},{0,-1,0},{0,0,-1}};

        ORE_BLOCKS = new LinkedList<>();
        ORE_BLOCKS.add(Material.COAL_ORE);
        ORE_BLOCKS.add(Material.IRON_ORE);
        ORE_BLOCKS.add(Material.GOLD_ORE);
        ORE_BLOCKS.add(Material.DIAMOND_ORE);
        ORE_BLOCKS.add(Material.EMERALD_ORE);
        ORE_BLOCKS.add(Material.DEEPSLATE_COAL_ORE);
        ORE_BLOCKS.add(Material.DEEPSLATE_IRON_ORE);
        ORE_BLOCKS.add(Material.DEEPSLATE_GOLD_ORE);
        ORE_BLOCKS.add(Material.DEEPSLATE_DIAMOND_ORE);
        ORE_BLOCKS.add(Material.DEEPSLATE_EMERALD_ORE);
    }
}
