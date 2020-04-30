package me.sebbaindustries.advancedafk.utils;

import org.bukkit.ChatColor;

/**
 * @author sebbaindustries
 * @version 1.0
 */
public class Color {

    public static String chat(String s) {
        return ChatColor.translateAlternateColorCodes('&', s);
    }
}
