package me.sebbaindustries.advancedafk.placeholderapi;

import me.clip.placeholderapi.expansion.PlaceholderExpansion;
import me.sebbaindustries.advancedafk.detection.PlayerData;
import org.bukkit.Bukkit;
import org.bukkit.OfflinePlayer;
import org.bukkit.entity.Player;

public class Placeholders extends PlaceholderExpansion {

    @Override
    public boolean canRegister() {
        return true;
    }

    @Override
    public String getAuthor() {
        return "SebbaIndustries, Frcsty";
    }

    @Override
    public String getIdentifier() {
        return "advancedafk";
    }

    @Override
    public String getVersion() {
        return "1.0";
    }

    @Override
    public String onRequest(OfflinePlayer player, String identifier) {
        if (identifier.length() == 0) return null;

        final Player plr = (Player) player;
        final String[] parts = identifier.split("_");
        final Player target = parts.length == 2 ? Bukkit.getPlayerExact(parts[1]) : null;

        // Returns the status for the player
        if (identifier.equalsIgnoreCase("status")) {
            return formatStatus(plr);
        }

        // Returns the AFK timer for the player
        if (identifier.equalsIgnoreCase("timer")) {
            return String.valueOf(PlayerData.getAFKTime(plr));
        }

        if (target == null) {
            return null;
        }

        // Returns the status for the argument player
        if (identifier.startsWith("status_")) {
            return formatStatus(target);
        }

        // Returns the AFK timer for the argument player
        if (identifier.startsWith("timer_")) {
            return String.valueOf(PlayerData.getAFKTime(target));
        }

        return null;
    }

    private String formatStatus(final Player player) {
        return PlayerData.getStatus(player) ? "&cAFK" : "&aPlaying";
    }
}


