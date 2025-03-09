//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package net.minso.chathead.API;

import java.util.UUID;
import net.md_5.bungee.api.chat.BaseComponent;
import net.md_5.bungee.api.chat.TextComponent;
import net.minso.chathead.API.impl.CrafatarSource;
import net.minso.chathead.API.impl.McHeadsSource;
import net.minso.chathead.API.impl.MinotarSource;
import net.minso.chathead.API.impl.MojangSource;
import org.bukkit.Bukkit;
import org.bukkit.OfflinePlayer;
import org.bukkit.plugin.java.JavaPlugin;

public class ChatHeadAPI {
    public static SkinSource defaultSource;
    private static ChatHeadAPI instance;
    private final JavaPlugin plugin;
    private final HeadCache headCache;

    public ChatHeadAPI(JavaPlugin plugin) {
        this.plugin = plugin;
        this.headCache = new HeadCache(plugin);
    }

    public static ChatHeadAPI getInstance() {
        if (instance == null) {
            throw new IllegalArgumentException("ChatHeadAPI has not been initialized.");
        } else {
            return instance;
        }
    }

    public static void initialize(JavaPlugin plugin) {
        if (instance != null) {
            throw new IllegalStateException("PlayerHeadAPI has already been initialized.");
        } else {
            String skinSourceConfig = plugin.getConfig().getString("skin-source", "MOJANG");
            Object var10000;
            switch (skinSourceConfig.toUpperCase()) {
                case "CRAFATAR" -> var10000 = new CrafatarSource();
                case "MINOTAR" -> var10000 = new MinotarSource();
                case "MCHEADS" -> var10000 = new McHeadsSource();
                default -> var10000 = new MojangSource();
            }

            defaultSource = (SkinSource)var10000;
            instance = new ChatHeadAPI(plugin);
        }
    }

    public BaseComponent[] getHead(UUID uuid) {
        return this.headCache.getCachedHead(uuid, true, defaultSource);
    }

    public BaseComponent[] getHead(UUID uuid, boolean overlay) {
        return this.headCache.getCachedHead(uuid, overlay, defaultSource);
    }

    public BaseComponent[] getHead(UUID uuid, boolean overlay, SkinSource skinSource) {
        return this.headCache.getCachedHead(uuid, overlay, skinSource);
    }

    public BaseComponent[] getHead(OfflinePlayer player) {
        return this.headCache.getCachedHead(player, true, defaultSource);
    }

    public BaseComponent[] getHead(OfflinePlayer player, boolean overlay) {
        return this.headCache.getCachedHead(player, overlay, defaultSource);
    }

    public BaseComponent[] getHead(OfflinePlayer player, boolean overlay, SkinSource skinSource) {
        return this.headCache.getCachedHead(player, overlay, skinSource);
    }

    public String getHeadAsString(UUID uuid, boolean overlay, SkinSource skinSource) {
        return this.getHeadAsString(Bukkit.getOfflinePlayer(uuid), true, defaultSource);
    }

    public String getHeadAsString(OfflinePlayer player, boolean overlay, SkinSource skinSource) {
        return TextComponent.toLegacyText(this.getHead(player, overlay, skinSource));
    }

    public String getHeadAsString(OfflinePlayer player) {
        return this.getHeadAsString(player, true, defaultSource);
    }
}
