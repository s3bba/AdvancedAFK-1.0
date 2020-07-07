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

import java.util.Arrays;

/**
 * @author sebbaindustries
 * @version 1.0
 */
public final class Debug {

    /**
     * Debug constructor, activates on debug flag
     *
     * @param sender  command Sender (player, console)
     * @param command command
     * @param label   aliases
     * @param args    command arguments
     */
    public Debug(final @NotNull CommandSender sender, final @NotNull Command command, final @NotNull String label, final String[] args) {

        // check for permissions
        if (!sender.hasPermission("aafk.flag.debug") || !sender.hasPermission("aafk.*") || !sender.hasPermission("aafk.flag.*")) {
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

        // send all data to sender
        sender.sendMessage(Color.chat("&8[&dAAFK&8] &7Debugging Player &8: &f" + target.getName()));
        sender.sendMessage(Color.chat(""));
        sender.sendMessage(Color.chat("&fStatus: "));
        sender.sendMessage(Color.chat(" &8» &7AFK time&8: &f" + storage.afkTime));
        sender.sendMessage(Color.chat(" &8» &7Trail length&8: &f" + storage.trailLength));
        sender.sendMessage(Color.chat(""));
        sender.sendMessage(Color.chat(" &8» &7X coordinate&8(&fmin&8, &fmax&8, &favg&8) &f" + storage.minX + "&8, &f" + storage.maxX + "&8, &f" + storage.avgDistanceTrailX));
        sender.sendMessage(Color.chat(" &8» &7Y coordinate&8(&fmin&8, &fmax&8, &favg&8) &f" + storage.minY + "&8, &f" + storage.maxY + "&8, &f" + storage.avgDistanceTrailY));
        sender.sendMessage(Color.chat(" &8» &7Z coordinate&8(&fmin&8, &fmax&8, &favg&8) &f" + storage.minZ + "&8, &f" + storage.maxZ + "&8, &f" + storage.avgDistanceTrailZ));
        sender.sendMessage(Color.chat(" &8» &7Yaw coordinate&8(&fmin&8, &fmax&8, &favg&8) &f" + storage.minYaw + "&8, &f" + storage.maxYaw + "&8, &f" + storage.avgMoveTrailYaw));
        sender.sendMessage(Color.chat(" &8» &7Pitch coordinate&8(&fmin&8, &fmax&8, &favg&8) &f" + storage.minPitch + "&8, &f" + storage.maxPitch + "&8, &f" + storage.avgMoveTrailPitch));
        sender.sendMessage(Color.chat(" &8» &7Trail X&8: &f" + Arrays.toString(storage.getTrailX())));
        sender.sendMessage(Color.chat(" &8» &7Trail Y&8: &f" + Arrays.toString(storage.getTrailY())));
        sender.sendMessage(Color.chat(" &8» &7Trail Z&8: &f" + Arrays.toString(storage.getTrailZ())));
        sender.sendMessage(Color.chat(" &8» &7Trail Yaw&8: &f" + Arrays.toString(storage.getTrailYaw())));
        sender.sendMessage(Color.chat(" &8» &7Trail Pitch&8: &f" + Arrays.toString(storage.getTrailPitch())));
        sender.sendMessage(Color.chat(""));
    }

}
