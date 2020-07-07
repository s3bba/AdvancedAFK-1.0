package me.sebbaindustries.advancedafk.commands.subs;

import me.sebbaindustries.advancedafk.Core;
import me.sebbaindustries.advancedafk.detection.PlayerData;
import me.sebbaindustries.advancedafk.detection.Storage;
import me.sebbaindustries.advancedafk.utils.Color;
import me.sebbaindustries.advancedafk.utils.Message;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

/**
 * @author sebbaindustries
 * @version 1.0
 */
public final class Lookup {

    /**
     * Lookup constructor, activates on lookup flag
     *
     * @param sender  command Sender (player, console)
     * @param command command
     * @param label   aliases
     * @param args    command arguments
     */
    public Lookup(final @NotNull CommandSender sender, final @NotNull Command command, final @NotNull String label, final String[] args) {

        // check for permissions
        if (!sender.hasPermission("aafk.flag.lookup") || !sender.hasPermission("aafk.*") || !sender.hasPermission("aafk.flag.*")) {
            sender.sendMessage(Core.gCore.message.getMessage(Message.M.noPermission));
            return;
        }

        // check for player flag (-p)
        Integer playerArg = null;
        for (int i = 0; i < args.length; i++) {
            if (args[i].toLowerCase().contains("-p") || args[i].toLowerCase().contains("-player")) {
                playerArg = i + 1;
                break;
            }
        }

        // try to extract player form arguments
        Player target;
        try {
            target = Bukkit.getPlayerExact(args[playerArg]);
        } catch (final NullPointerException ignored) {
            sender.sendMessage(Core.gCore.message.getMessage(Message.M.targetNotSpecified));
            return;
        } catch (final ArrayIndexOutOfBoundsException ignored) {
            sender.sendMessage(Core.gCore.message.getMessage(Message.M.missingArguments));
            return;
        }

        try {
            target.getName();
        } catch (final NullPointerException ignored) {
            sender.sendMessage(Core.gCore.message.getMessage(Message.M.targetNotFound));
            return;
        }

        final Storage storage = PlayerData.playerStorageHashMap.get(target);
        String status = "&aPlaying";
        if (storage.afkTime > 0) status = "&cAFK";
        sender.sendMessage(Color.chat(Core.gCore.message.getMessage(Message.M.lookup)
                .replace("%player%", target.getName())
                .replace("%status%", status)
                .replace("%time%", String.valueOf(storage.afkTime))));
    }

}
