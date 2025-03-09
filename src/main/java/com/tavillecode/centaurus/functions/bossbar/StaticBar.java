package com.tavillecode.centaurus.functions.bossbar;


import net.kyori.adventure.bossbar.BossBar;
import net.kyori.adventure.text.minimessage.MiniMessage;

/**
 * @author Interface39
 * @version 1.0
 * @description: TODO
 * @date 2025/2/8 23:25
 */
public class StaticBar {
    private final String text;
    private final BossBar barObject;

    public StaticBar(String text, BossBar.Color color, BossBar.Overlay overlay) {
        this.text = text;
        this.barObject = BossBar.bossBar(MiniMessage.miniMessage().deserialize(text),1, color, overlay);
    }

    public String getText() {
        return this.text;
    }

    public BossBar getBarObject() {
        return this.barObject;
    }


}
