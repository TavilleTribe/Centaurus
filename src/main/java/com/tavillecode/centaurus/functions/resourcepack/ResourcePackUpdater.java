package com.tavillecode.centaurus.functions.resourcepack;

import org.bukkit.entity.Player;

import javax.net.ssl.HttpsURLConnection;
import java.io.InputStream;
import java.math.BigInteger;
import java.net.URL;
import java.security.MessageDigest;
import java.util.Random;

public final class ResourcePackUpdater {

    private final String url;
    private String hash = "";

    public ResourcePackUpdater(String url) {
        this.url = url;
    }

    public void sendPlayer(Player player) {
        player.setResourcePack(url, hash);
    }

    public void updateHash() {
        try {
            HttpsURLConnection con = (HttpsURLConnection) new URL(url).openConnection();
            con.setRequestMethod("GET");
            con.setRequestProperty("User-Agent", String.valueOf(new Random().nextInt(100)));
            con.setConnectTimeout(5000);
            con.setReadTimeout(5000);
            con.setDoOutput(true);
            try (InputStream in = con.getInputStream()) {
                byte[] buffer = new byte[1024];
                MessageDigest digest = MessageDigest.getInstance("SHA-1");
                int len;
                while ((len = in.read(buffer, 0, 1024)) != -1) {
                    digest.update(buffer, 0, len);
                }
                hash = new BigInteger(1, digest.digest()).toString(16);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}