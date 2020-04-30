package me.sebbaindustries.advancedafk.detection;

import me.sebbaindustries.advancedafk.Core;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class PlayerData {

    /**
     * @see Storage
     * HashMap stores players and locations, locations are stored under Storage class
     */
    public static HashMap<Player, Storage> playerStorageHashMap = new HashMap<>();

    public List<Player> kickList = new ArrayList<>();

    public static boolean getStatus(final Player player) {
        return playerStorageHashMap.get(player).afkTime >= Core.gCore.settings.kickTime;
    }

    public static int getAFKTime(final Player player) {
        return playerStorageHashMap.get(player).afkTime;
    }

    /**
     * Updates location in storage class
     *
     * @param p Player
     */
    public final void updateLocation(@NotNull final Player p) {
        Location location = p.getLocation();
        Storage storage = playerStorageHashMap.get(p);
        storage.updateTrail(
                location.getBlockX(),
                location.getBlockY(),
                location.getBlockZ(),
                Math.round(location.getYaw()),
                Math.round(location.getPitch())
        );
        saveStorage(p, storage);
    }

    /**
     * Creates new entry or OVERRIDES old one
     *
     * @param p Player
     * @param s Storage.class that needs to be saved
     */
    public final void saveStorage(@NotNull final Player p, @NotNull final Storage s) {
        playerStorageHashMap.put(p, s);
    }

}
