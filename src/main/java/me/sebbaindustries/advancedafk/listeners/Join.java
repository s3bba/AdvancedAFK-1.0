package me.sebbaindustries.advancedafk.listeners;

import me.sebbaindustries.advancedafk.Core;
import me.sebbaindustries.advancedafk.detection.PlayerData;
import me.sebbaindustries.advancedafk.detection.Storage;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

/**
 * @author sebbaindustries
 * @version 1.0
 */
public final class Join implements Listener {

    /**
     * Activates when player Joins
     *
     * @param e Event
     */
    @EventHandler
    public void onPlayerJoin(final PlayerJoinEvent e) {
        Player p = e.getPlayer();
        // put player into map
        PlayerData.playerStorageHashMap.put(p, new Storage(Core.gCore.settings.trail));
    }

}
