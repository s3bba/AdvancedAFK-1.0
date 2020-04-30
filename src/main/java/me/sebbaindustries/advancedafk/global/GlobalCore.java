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
public class GlobalCore {

    /*
    Global instances of classes
     */
    public final Settings settings;
    public final Debug debug;
    public final Lang lang;
    public final FileManager fileManager;
    public final Message message;
    public final CommandManager commandManager;
    public final ListenerManager listenerManager;
    public final PlayerData playerData;
    public final ThreadManager threadManager;

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
        commandManager = new CommandManager(core);
        listenerManager = new ListenerManager(core);
        playerData = new PlayerData();
        threadManager = new ThreadManager();
        settings = new Settings();
    }

}
