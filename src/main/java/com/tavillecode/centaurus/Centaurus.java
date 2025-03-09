package com.tavillecode.centaurus;

import com.tavillecode.centaurus.command.CentaurusCompleter;
import com.tavillecode.centaurus.command.CentaurusExecutor;
import com.tavillecode.centaurus.functions.bossbar.DynamicBar;
import com.tavillecode.centaurus.functions.bossbar.StaticBar;
import com.tavillecode.centaurus.functions.resourcepack.ResourcePackUpdater;
import com.tavillecode.centaurus.functions.tablist.TabList;
import com.tavillecode.centaurus.listeners.*;
import com.tavillecode.centaurus.storage.impl.BarConfiguration;
import com.tavillecode.centaurus.storage.impl.BookConfiguration;
import com.tavillecode.centaurus.storage.impl.ScoreBoardConfiguration;
import com.tavillecode.centaurus.storage.impl.TabListConfiguration;
import com.tavillecode.centaurus.utils.MessageSection;
import fr.skytasul.glowingentities.GlowingBlocks;
import fr.skytasul.glowingentities.GlowingEntities;
import net.kyori.adventure.bossbar.BossBar;
import net.minso.chathead.API.ChatHeadAPI;
import net.minso.chathead.config.Config;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.Objects;

/**
 * @author Interface39
 * @version 1.0
 * @description: TODO
 * @date 2024/11/30 17:13
 */
public class Centaurus extends JavaPlugin {
    private BarConfiguration BAR_YAML;
    private BookConfiguration BOOK_YAML;
    private TabListConfiguration TABLIST_YAML;
    private ScoreBoardConfiguration BOARD_YAML;
    private Config AVATER_CONFIG;

    private GlowingEntities glowingEntitiesAPI;
    private GlowingBlocks glowingBlocksAPI;

    private ResourcePackUpdater UPDATER;

    //private DynamicBar GLOBAL_BAR;
    private StaticBar GLOBAL_BAR;
    private TabList GLOBAL_TABLIST;

    private static Centaurus instance;

    @Override
    public void onEnable() {
        instance = this;

//        UPDATER = new ResourcePackUpdater("https://resource-1301765989.cos.ap-guangzhou.myqcloud.com/pack.zip");
//        UPDATER.updateHash();

        BAR_YAML = new BarConfiguration(this,this.getDataFolder(),"dynamicbar",true,true);
        BAR_YAML.reload();

        BOOK_YAML = new BookConfiguration(this,this.getDataFolder(),"book",true,true);
        BOOK_YAML.reload();

        TABLIST_YAML = new TabListConfiguration(this,this.getDataFolder(),"tablist",true,true);
        TABLIST_YAML.reload();;

        BOARD_YAML = new ScoreBoardConfiguration(this,this.getDataFolder(),"scoreboard",true,true);
        BOARD_YAML.reload();

        glowingEntitiesAPI = new GlowingEntities(this);
        glowingBlocksAPI = new GlowingBlocks(this);

        ChatHeadAPI.initialize(this);
        this.AVATER_CONFIG = new Config(this);
        this.AVATER_CONFIG.init();

        //GLOBAL_BAR = new DynamicBar(this,DynamicBar.DYNAMIC,DynamicBar.TEXT,DynamicBar.SYMBOL,DynamicBar.BAR_COLOR,DynamicBar.BAR_STYLE);
        GLOBAL_BAR = new StaticBar(DynamicBar.TEXT, BossBar.Color.WHITE, BossBar.Overlay.PROGRESS);
        GLOBAL_TABLIST = new TabList(TabList.HEAD,TabList.TAIL);

        Objects.requireNonNull(getCommand("centaurus")).setExecutor(new CentaurusExecutor(this));
        Objects.requireNonNull(getCommand("centaurus")).setTabCompleter(new CentaurusCompleter());

        getServer().getPluginManager().registerEvents(new PlayerBreakBlockListener(),this);
        getServer().getPluginManager().registerEvents(new PlayerRightClickListener(),this);
        getServer().getPluginManager().registerEvents(new PlayerJoinListener(this),this);
        getServer().getPluginManager().registerEvents(new PlayerQuitListener(this),this);
        getServer().getPluginManager().registerEvents(new PlayerDropItemListener(this),this);

        MessageSection.EnableMessages();
    }

    @Override
    public void onDisable() {

    }

    public BarConfiguration getBarYaml() {
        return BAR_YAML;
    }

    public BookConfiguration getBookYaml() {
        return BOOK_YAML;
    }

    public TabListConfiguration getTabListYaml() {
        return TABLIST_YAML;
    }

    public ScoreBoardConfiguration getBoardYaml() {
        return BOARD_YAML;
    }

    public GlowingEntities getGlowingEntitiesAPI() {
        return glowingEntitiesAPI;
    }

    public GlowingBlocks getGlowingBlocksAPI() {
        return glowingBlocksAPI;
    }

    public Config getAvaterConfig() {
        return AVATER_CONFIG;
    }

//    public DynamicBar getGlobalBar() {
//        return GLOBAL_BAR;
//    }

    public StaticBar getGlobalBar() {
        return this.GLOBAL_BAR;
    }

    public TabList getGlobalTabList() {
        return this.GLOBAL_TABLIST;
    }

    public ResourcePackUpdater getUpdater() {
        return this.UPDATER;
    }

    public static Centaurus getInstance() {
        return instance;
    }
}
