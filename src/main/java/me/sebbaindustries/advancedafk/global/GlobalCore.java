package me.sebbaindustries.advancedafk.global;

import me.sebbaindustries.advancedafk.Core;
import me.sebbaindustries.advancedafk.commands.CommandManager;
import me.sebbaindustries.advancedafk.debug.Debug;
import me.sebbaindustries.advancedafk.detection.PlayerData;
import me.sebbaindustries.advancedafk.detection.ThreadManager;
import me.sebbaindustries.advancedafk.lang.Lang;
import me.sebbaindustries.advancedafk.listeners.ListenerManager;
import me.sebbaindustries.advancedafk.utils.FileManager;
import me.sebbaindustries.advancedafk.utils.Message;
import me.sebbaindustries.advancedafk.utils.Settings;
import org.jetbrains.annotations.NotNull;

/**
 * @author sebbaindustries
 * @version 1.0
 */
public final class GlobalCore {

    /*
    Global instances of classes
     */
    public final Settings settings;
    public final Debug debug;
    public final Lang lang;
    public final FileManager fileManager;
    public final Message message;
    public final PlayerData playerData;

    /**
     * @param core Core.class
     * @see Core
     * GlobalCore constructor with Core class inheritance
     */
    public GlobalCore(@NotNull Core core) {
       /*
       Initialize instances when constructor is called
        */
        debug = new Debug();
        lang = new Lang();
        fileManager = new FileManager(core);
        message = new Message();
        new CommandManager(core);
        new ListenerManager(core);
        playerData = new PlayerData();
        new ThreadManager();
        settings = new Settings();
    }

}
