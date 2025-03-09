package net.minso.chathead.API.impl;

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.URL;
import javax.imageio.ImageIO;
import net.md_5.bungee.api.chat.BaseComponent;
import net.minso.chathead.API.SkinSource;
import net.minso.chathead.API.SkinSourceEnum;
import org.bukkit.OfflinePlayer;

public class McHeadsSource extends SkinSource {
    public McHeadsSource(boolean useUUIDWhenRetrieve) {
        super(SkinSourceEnum.MCHEADS, true, useUUIDWhenRetrieve);
    }

    public McHeadsSource() {
        super(SkinSourceEnum.MCHEADS, true);
    }

    public BaseComponent[] getHead(OfflinePlayer player, boolean overlay) {
        String nameOrUUID = this.useUUIDWhenRetrieve() ? player.getUniqueId().toString() : player.getName();
        String[] colors = new String[64];

        try {
            String url = "https://mc-heads.net/avatar/" + nameOrUUID + "/8";
            if (!overlay) {
                url = url + "/nohelm";
            }

            BufferedImage skinImage = ImageIO.read(new URL(url));
            int faceWidth = 8;
            int faceHeight = 8;
            int index = 0;

            for(int x = 0; x < faceHeight; ++x) {
                for(int y = 0; y < faceWidth; ++y) {
                    colors[index++] = String.format("#%06X", skinImage.getRGB(x, y) & 16777215);
                }
            }
        } catch (IOException var12) {
            IOException e = var12;
            e.printStackTrace();
        }

        return this.toBaseComponent(colors);
    }
}
