package me.sebbaindustries.advancedafk.utils;

import org.bukkit.ChatColor;

/**
 * @author sebbaindustries
 * @version 1.0
 */
public final class Color {

    public static String chat(final String s) {
        return ChatColor.translateAlternateColorCodes('&', s);
    }
}
