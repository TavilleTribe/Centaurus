//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package net.minso.chathead.API;

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.URL;
import java.util.Arrays;
import javax.imageio.ImageIO;
import net.md_5.bungee.api.ChatColor;
import net.md_5.bungee.api.chat.BaseComponent;
import net.md_5.bungee.api.chat.ComponentBuilder;
import net.md_5.bungee.api.chat.TextComponent;
import org.bukkit.OfflinePlayer;

public abstract class SkinSource {
    private final SkinSourceEnum skinSource;
    private final boolean hasUsernameSupport;
    private final boolean useUUIDWhenRetrieve;

    public SkinSource(SkinSourceEnum skinSource, boolean hasUsernameSupport, boolean useUUIDWhenRetrieve) {
        this.skinSource = skinSource;
        this.hasUsernameSupport = hasUsernameSupport;
        this.useUUIDWhenRetrieve = useUUIDWhenRetrieve;
    }

    public SkinSource(SkinSourceEnum skinSource, boolean hasUsernameSupport) {
        this.skinSource = skinSource;
        this.hasUsernameSupport = hasUsernameSupport;
        this.useUUIDWhenRetrieve = true;
    }

    public abstract BaseComponent[] getHead(OfflinePlayer var1, boolean var2);

    public BaseComponent[] toBaseComponent(String[] hexColors) {
        if (hexColors != null && hexColors.length >= 64) {
            TextComponent[][] components = new TextComponent[8][8];

            for(int i = 0; i < 64; ++i) {
                int row = i / 8;
                int col = i % 8;
                char unicodeChar = (char)('\uf000' + i % 8 + 1);
                TextComponent component = new TextComponent();
                if (i != 7 && i != 15 && i != 23 && i != 31 && i != 39 && i != 47 && i != 55) {
                    if (i == 63) {
                        component.setText(Character.toString(unicodeChar));
                    } else {
                        component.setText("" + unicodeChar + Character.toString('\uf102'));
                    }
                } else {
                    component.setText("" + unicodeChar + Character.toString('\uf101'));
                }

                component.setColor(ChatColor.of(hexColors[i]));
                components[row][col] = component;
            }

            TextComponent defaultFont = new TextComponent();
            defaultFont.setText("");
            defaultFont.setFont("minecraft:default");
            BaseComponent[] baseComponents = (new ComponentBuilder()).append((BaseComponent[])Arrays.stream(components).flatMap(Arrays::stream).toArray((x$0) -> {
                return new TextComponent[x$0];
            })).append(defaultFont).create();
            return baseComponents;
        } else {
            throw new IllegalArgumentException("Hex colors must have at least 64 elements.");
        }
    }

    public String[] getPixelColorsFromSkin(String playerSkinUrl, boolean overlay) {
        String[] colors = new String[64];

        try {
            BufferedImage skinImage = ImageIO.read(new URL(playerSkinUrl));
            if (skinImage.getHeight() < 64) {
                overlay = false;
            }

            int faceStartX = 8;
            int faceStartY = 8;
            int faceWidth = 8;
            int faceHeight = 8;
            int overlayStartX = 40;
            int overlayStartY = 8;
            BufferedImage faceImage = skinImage.getSubimage(faceStartX, faceStartY, faceWidth, faceHeight);
            BufferedImage overlayImage = null;
            if (overlay) {
                overlayImage = skinImage.getSubimage(overlayStartX, overlayStartY, faceWidth, faceHeight);
            }

            int index = 0;

            for(int x = 0; x < faceHeight; ++x) {
                for(int y = 0; y < faceWidth; ++y) {
                    int rgbFace = faceImage.getRGB(x, y);
                    if (overlay && overlayImage != null) {
                        int rgbOverlay = overlayImage.getRGB(x, y);
                        if (rgbOverlay >> 24 != 0) {
                            colors[index++] = String.format("#%06X", rgbOverlay & 16777215);
                            continue;
                        }
                    }

                    colors[index++] = String.format("#%06X", rgbFace & 16777215);
                }
            }
        } catch (IOException var18) {
            IOException e = var18;
            e.printStackTrace();
        }

        return colors;
    }

    public SkinSourceEnum getSkinSource() {
        return this.skinSource;
    }

    public boolean hasUsernameSupport() {
        return this.hasUsernameSupport;
    }

    public boolean useUUIDWhenRetrieve() {
        return this.useUUIDWhenRetrieve;
    }
}
