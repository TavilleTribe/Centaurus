package net.minso.chathead.API.impl;

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.URL;
import javax.imageio.ImageIO;
import net.md_5.bungee.api.chat.BaseComponent;
import net.minso.chathead.API.SkinSource;
import net.minso.chathead.API.SkinSourceEnum;
import org.bukkit.OfflinePlayer;

public class CrafatarSource extends SkinSource {
    public CrafatarSource(boolean useUUIDWhenRetrieve) {
        super(SkinSourceEnum.MCHEADS, false, useUUIDWhenRetrieve);
    }

    public CrafatarSource() {
        super(SkinSourceEnum.MCHEADS, false);
    }

    public BaseComponent[] getHead(OfflinePlayer player, boolean overlay) {
        if (!this.hasUsernameSupport() && !this.useUUIDWhenRetrieve()) {
            throw new UnsupportedOperationException("CrafatarSource does not support username to retrieve player heads");
        } else {
            String[] colors = new String[64];

            try {
                String url = "https://crafatar.com/avatars/" + String.valueOf(player.getUniqueId()) + "?size=8";
                if (overlay) {
                    url = url + "&overlay";
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
            } catch (IOException var11) {
                IOException e = var11;
                e.printStackTrace();
            }

            return this.toBaseComponent(colors);
        }
    }
}
