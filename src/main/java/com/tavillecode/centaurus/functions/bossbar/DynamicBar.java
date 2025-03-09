package com.tavillecode.centaurus.functions.bossbar;

import com.tavillecode.centaurus.Centaurus;
import org.bukkit.Bukkit;
import org.bukkit.boss.BarColor;
import org.bukkit.boss.BarStyle;
import org.bukkit.boss.BossBar;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.ArrayList;

public class DynamicBar {
    private final Centaurus plugin;
    private final ArrayList<String> dynamic;
    private final String text;
    private final String Symbol;
    private final BossBar barObject;
    private int current;

    public DynamicBar(Centaurus plugin, ArrayList<String> dynamic, String origin, String symbol, BarColor barColor, BarStyle barStyle) {
        this.plugin = plugin;
        this.dynamic = dynamic;
        this.text = origin;
        this.Symbol = symbol;
        this.barObject = Bukkit.createBossBar(this.text,barColor,barStyle);
        this.current = 0;
        playDynamicText();
    }

    private synchronized void playDynamicText() {
        (new BukkitRunnable() {
            @Override
            public void run() {
                barObject.setTitle(text.replace(Symbol, dynamic.get(current)));
                if (current + 1 >= dynamic.size()) {
                    current = 0;
                }
                else {
                    current += 1;
                }
            }
        }).runTaskTimerAsynchronously(this.plugin,1L,4L);
    }

    public BossBar getBarObject() {
        return barObject;
    }

    public static String TEXT;

    public static String SYMBOL;

    public static ArrayList<String> DYNAMIC;

    public static BarColor BAR_COLOR;

    public static BarStyle BAR_STYLE;

}
