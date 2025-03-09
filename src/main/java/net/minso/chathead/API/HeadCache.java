//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package net.minso.chathead.API;

import java.util.Map;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;
import net.md_5.bungee.api.chat.BaseComponent;
import org.bukkit.Bukkit;
import org.bukkit.OfflinePlayer;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scheduler.BukkitTask;

public class HeadCache {
    private final JavaPlugin plugin;
    private static final long CACHE_EXPIRATION = 300000L;
    private final Map<String, CachedHead> cache = new ConcurrentHashMap();
    private final Map<String, Boolean> pendingRequests = new ConcurrentHashMap();
    private BukkitTask cacheCleanupTask;

    public HeadCache(JavaPlugin plugin) {
        this.plugin = plugin;
        this.startCacheCleanupTask();
    }

    public BaseComponent[] getCachedHead(UUID uuid, boolean overlay, SkinSource skinSource) {
        return this.getCachedHead(Bukkit.getOfflinePlayer(uuid), overlay, skinSource);
    }

    public BaseComponent[] getCachedHead(OfflinePlayer player, boolean overlay, SkinSource skinSource) {
        UUID uuid = player.getUniqueId();
        String cacheKey = this.getCacheKey(uuid, overlay);
        CachedHead cachedHead = (CachedHead)this.cache.get(cacheKey);
        if (cachedHead != null && !this.isExpired(cachedHead)) {
            return cachedHead.getHead();
        } else {
            BaseComponent[] lastHead = cachedHead != null ? cachedHead.getHead() : new BaseComponent[0];
            if (this.pendingRequests.putIfAbsent(cacheKey, true) == null) {
                Bukkit.getScheduler().runTaskAsynchronously(this.plugin, () -> {
                    BaseComponent[] head = skinSource.getHead(player, overlay);
                    if (head != null && head.length > 0 && this.plugin.isEnabled()) {
                        this.cache.put(cacheKey, new CachedHead(head, overlay, System.currentTimeMillis()));
                    }

                    this.pendingRequests.remove(cacheKey);
                });
            }

            return lastHead;
        }
    }

    private boolean isExpired(CachedHead cachedHead) {
        return System.currentTimeMillis() - cachedHead.getTimestamp() > 300000L;
    }

    private void startCacheCleanupTask() {
        if (this.cacheCleanupTask != null) {
            this.cacheCleanupTask.cancel();
        }

        this.cacheCleanupTask = Bukkit.getScheduler().runTaskTimerAsynchronously(this.plugin, () -> {
            this.cache.entrySet().removeIf((entry) -> {
                return this.isExpired((CachedHead)entry.getValue());
            });
        }, 15000L, 15000L);
    }

    private String getCacheKey(UUID uuid, boolean overlay) {
        String var10000 = uuid.toString();
        return var10000 + ":" + overlay;
    }

    private static class CachedHead {
        private final BaseComponent[] head;
        private final boolean overlay;
        private final long timestamp;

        CachedHead(BaseComponent[] head, boolean overlay, long timestamp) {
            this.head = head;
            this.overlay = overlay;
            this.timestamp = timestamp;
        }

        public BaseComponent[] getHead() {
            return this.head;
        }

        public boolean hasOverlay() {
            return this.overlay;
        }

        public long getTimestamp() {
            return this.timestamp;
        }
    }
}
