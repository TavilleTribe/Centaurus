package net.minso.chathead.API.impl;

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.URL;
import javax.imageio.ImageIO;
import net.md_5.bungee.api.chat.BaseComponent;
import net.minso.chathead.API.SkinSource;
import net.minso.chathead.API.SkinSourceEnum;
import org.bukkit.OfflinePlayer;

public class MinotarSource extends SkinSource {
    public MinotarSource(boolean useUUIDWhenRetrieve) {
        super(SkinSourceEnum.MCHEADS, true, useUUIDWhenRetrieve);
    }

    public MinotarSource() {
        super(SkinSourceEnum.MCHEADS, true);
    }

    public BaseComponent[] getHead(OfflinePlayer player, boolean overlay) {
        String[] colors = new String[64];

        try {
            String baseUrl = "https://minotar.net/";
            String endpoint = overlay ? "helm" : "avatar";
            String uuidOrUsername = this.useUUIDWhenRetrieve() ? player.getUniqueId().toString().replace("-", "").trim() : player.getName();
            String imageUrl = baseUrl + endpoint + "/" + uuidOrUsername + "/8.png";
            BufferedImage skinImage = ImageIO.read(new URL(imageUrl));
            int faceWidth = 8;
            int faceHeight = 8;
            int index = 0;

            for(int x = 0; x < faceHeight; ++x) {
                for(int y = 0; y < faceWidth; ++y) {
                    colors[index++] = String.format("#%06X", skinImage.getRGB(x, y) & 16777215);
                }
            }
        } catch (IOException var14) {
            IOException e = var14;
            e.printStackTrace();
        }

        return this.toBaseComponent(colors);
    }
}
