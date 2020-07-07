package me.sebbaindustries.advancedafk.detection;

import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public final class PlayerData {

    /**
     * @see Storage
     * Map stores players and locations, locations are stored under Storage class
     */
    public final static Map<Player, Storage> playerStorageHashMap = new HashMap<>();

    public final List<Player> kickList = new ArrayList<>();

    /**
     * Updates location in storage class
     *
     * @param p Player
     */
    final void updateLocation(@NotNull final Player p) {
        final Location location = p.getLocation();
        final Storage storage = playerStorageHashMap.get(p);
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
    private void saveStorage(@NotNull final Player p, @NotNull final Storage s) {
        playerStorageHashMap.put(p, s);
    }

}
