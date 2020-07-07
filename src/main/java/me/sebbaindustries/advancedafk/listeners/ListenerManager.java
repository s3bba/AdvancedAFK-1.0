package me.sebbaindustries.advancedafk.listeners;

import me.sebbaindustries.advancedafk.Core;
import org.jetbrains.annotations.NotNull;

/**
 * @author sebbaindustries
 * @version 1.0
 */
public final class ListenerManager {

    /**
     * Register listeners on call
     *
     * @param core Main class
     */
    public ListenerManager(@NotNull final Core core) {
        core.getServer().getPluginManager().registerEvents(new Join(), core);
        core.getServer().getPluginManager().registerEvents(new Quit(), core);
    }

}
