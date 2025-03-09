package com.tavillecode.centaurus.utils;

import org.bukkit.entity.Player;

/**
 * @author Interface39
 * @version 1.0
 * @description: TODO
 * @date 2025/2/11 23:46
 */
public class CustomSound {
    private final String sound;

    public CustomSound(String sound) {
        this.sound = sound;
    }

    public void play(Player player) {
        String[] split = sound.split("-");
        player.playSound(player.getLocation(),split[0],Float.parseFloat(split[1]),Float.parseFloat(split[2]));
    }
}
