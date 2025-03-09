package com.tavillecode.centaurus.utils;

import org.bukkit.entity.Player;

/**
 * @author Interface39
 * @version 1.0
 * @description: TODO
 * @date 2025/2/12 14:38
 */
public class MessageSection {
    private final static String heading = "[Centaurus]";
    public static void EnableMessages() {
        System.out.print("\n" + heading + " 作为Library插件,提供各种核心显示功能和算法的API");
        System.out.print("\n" + heading + " 作者: 凉呈哟");
        System.out.print("\n" + heading + " 工作室: TavilleTribe\n");
    }
    public static void HelpMessages(Player p) {
        p.sendMessage("§8§m                                                                            ");
        p.sendMessage(" ");
        p.sendMessage("   §f“闲着没事”");
        p.sendMessage("      §f“随便搓搓”");
        p.sendMessage(" ");
        p.sendMessage("§7- §fAuthor: [§nTavilleTribe.凉呈哟§r]");
        p.sendMessage("§7- §fVersion: [§n1.0§r]");
        p.sendMessage(" ");
        p.sendMessage("§7/§fcentaurus reload §7##重新读取所有配置");
        p.sendMessage(" ");
        p.sendMessage("§8§m                                                                            ");
    }
}
