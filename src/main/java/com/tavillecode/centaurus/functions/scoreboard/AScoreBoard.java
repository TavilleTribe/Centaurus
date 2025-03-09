package com.tavillecode.centaurus.functions.scoreboard;

import fr.mrmicky.fastboard.adventure.FastBoard;
import net.kyori.adventure.text.Component;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author Interface39
 * @version 1.0
 * @description: TODO
 * @date 2025/2/22 21:00
 */
public class AScoreBoard {
    private final Player player;

    private FastBoard boardObj;

    public AScoreBoard(Player player) {
        this.player = player;
        this.init();
    }

    private void init() {
        this.boardObj = new FastBoard(this.player);
        boards.put(this.player,this);
        this.update();
    }

    public void update() {
        this.boardObj.updateLines(title);
        this.boardObj.updateLines(components);
    }

    public void delete() {
        this.boardObj.delete();
        boards.remove(this.player);
    }

    public static List<Component> components;

    public static Component title;

    private static Map<Player, AScoreBoard> boards;

    public static AScoreBoard getByPlayer(Player player) {
        return boards.get(player);
    }

    static {
        components = new ArrayList<>();
        boards = new ConcurrentHashMap<>();
    }
}
