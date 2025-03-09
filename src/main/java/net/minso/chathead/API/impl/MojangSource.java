package net.minso.chathead.API.impl;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Base64;
import net.md_5.bungee.api.chat.BaseComponent;
import net.minso.chathead.API.SkinSource;
import net.minso.chathead.API.SkinSourceEnum;
import org.bukkit.OfflinePlayer;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class MojangSource extends SkinSource {
    public MojangSource(boolean useUUIDWhenRetrieve) {
        super(SkinSourceEnum.MOJANG, true, useUUIDWhenRetrieve);
    }

    public MojangSource() {
        super(SkinSourceEnum.MOJANG, true);
    }

    public BaseComponent[] getHead(OfflinePlayer player, boolean overlay) {
        return this.useUUIDWhenRetrieve() ? this.toBaseComponent(this.getPixelColorsFromSkin(this.getPlayerSkinFromMojang(player.getUniqueId().toString()), overlay)) : this.toBaseComponent(this.getPixelColorsFromSkin(this.getPlayerSkinFromMojang(this.getUUIDFromName(player)), overlay));
    }

    public String getUUIDFromName(OfflinePlayer offlinePlayer) {
        try {
            URL url = new URL("https://api.mojang.com/users/profiles/minecraft/" + offlinePlayer.getName());
            HttpURLConnection connection = (HttpURLConnection)url.openConnection();
            connection.setRequestMethod("GET");
            BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));

            String var9;
            try {
                StringBuilder response = new StringBuilder();

                while(true) {
                    String line;
                    if ((line = reader.readLine()) == null) {
                        reader.close();
                        String jsonResponse = response.toString();
                        JSONObject jsonObject = new JSONObject(jsonResponse);
                        var9 = jsonObject.getString("id");
                        break;
                    }

                    response.append(line);
                }
            } catch (Throwable var11) {
                try {
                    reader.close();
                } catch (Throwable var10) {
                    var11.addSuppressed(var10);
                }

                throw var11;
            }

            reader.close();
            return var9;
        } catch (JSONException | IOException var12) {
            Exception e = var12;
            ((Exception)e).printStackTrace();
            return "";
        }
    }

    private String getPlayerSkinFromMojang(String uuid) {
        try {
            URL url = new URL("https://sessionserver.mojang.com/session/minecraft/profile/" + uuid);
            HttpURLConnection connection = (HttpURLConnection)url.openConnection();
            connection.setRequestMethod("GET");
            BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));

            String var16;
            label62: {
                try {
                    StringBuilder response = new StringBuilder();

                    String line;
                    while((line = reader.readLine()) != null) {
                        response.append(line);
                    }

                    reader.close();
                    String jsonResponse = response.toString();
                    JSONObject jsonObject = new JSONObject(jsonResponse);
                    JSONArray propertiesArray = jsonObject.getJSONArray("properties");

                    for(int i = 0; i < propertiesArray.length(); ++i) {
                        JSONObject property = propertiesArray.getJSONObject(i);
                        if (property.getString("name").equals("textures")) {
                            String value = property.getString("value");
                            byte[] decodedBytes = Base64.getDecoder().decode(value);
                            String decodedValue = new String(decodedBytes);
                            JSONObject textureJson = new JSONObject(decodedValue);
                            var16 = textureJson.getJSONObject("textures").getJSONObject("SKIN").getString("url");
                            break label62;
                        }
                    }
                } catch (Throwable var18) {
                    try {
                        reader.close();
                    } catch (Throwable var17) {
                        var18.addSuppressed(var17);
                    }

                    throw var18;
                }

                reader.close();
                return "Unable to retrieve player skin URL.";
            }

            reader.close();
            return var16;
        } catch (JSONException | IOException var19) {
            Exception e = var19;
            ((Exception)e).printStackTrace();
            return "Unable to retrieve player skin URL.";
        }
    }
}
