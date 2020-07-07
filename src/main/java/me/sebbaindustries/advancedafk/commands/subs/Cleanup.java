package me.sebbaindustries.advancedafk.commands.subs;

import me.sebbaindustries.advancedafk.Core;
import me.sebbaindustries.advancedafk.utils.Message;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

import java.util.List;

/**
 * @author sebbaindustries
 * @version 1.0
 */
public final class Cleanup {

    private CommandSender sender;
    private int kickCounter;

    /**
     * Cleanup constructor, activates on cleanup flag
     *
     * @param sender  command Sender (player, console)
     * @param command command
     * @param label   aliases
     * @param args    command arguments
     */
    public Cleanup(final @NotNull CommandSender sender, final @NotNull Command command, final @NotNull String label, final String[] args) {

        // check for permissions
        if (!sender.hasPermission("aafk.flag.cleanup") || !sender.hasPermission("aafk.*") || !sender.hasPermission("aafk.flag.*")) {
            sender.sendMessage(Core.gCore.message.getMessage(Message.M.noPermission));
            return;
        }

        // check for -f (force) flag
        final boolean force = useForce(args);
        // check if sender has permission to use force flag
        if (force) {
            if (!sender.hasPermission("aafk.flag.cleanup.force") || !sender.hasPermission("aafk.*")) {
                sender.sendMessage(Core.gCore.message.getMessage(Message.M.noPermission));
                return;
            }
        }

        this.sender = sender;
        this.kickCounter = 0;

        // start cleanup
        sender.sendMessage(Core.gCore.message.getMessage(Message.M.cleanupStart));

        final List<Player> copiedKickList = Core.gCore.playerData.kickList;
        copiedKickList.forEach(player ->
        {
            if (force) {
                kickPlayer(player);
            } else if (!player.hasPermission("aafk.*") || !player.hasPermission("aafk.kick.bypass.cleanup")) {
                kickPlayer(player);
            }
        });
        // end cleanup
        sender.sendMessage(Core.gCore.message.getMessage(Message.M.cleanupEnd).replace("%kicked_players%", String.valueOf(kickCounter)));
    }

    /**
     * kick player and removes him from the list
     *
     * @param p Player
     * @see me.sebbaindustries.advancedafk.detection.PlayerData
     */
    private void kickPlayer(final Player p) {
        if (p == sender) {
            return;
        }
        p.kickPlayer(Core.gCore.message.getMessage(Message.M.cleanupKick));
        Core.gCore.playerData.kickList.remove(p);
        sender.sendMessage(Core.gCore.message.getMessage(Message.M.cleanupKickedPlayer).replace("%player%", p.getName()));
        kickCounter++;
    }

    /**
     * Check if force flag is present in arguments
     *
     * @param args command arguments
     * @return true if flag is present
     */
    private boolean useForce(final String[] args) {
        boolean force = false;
        for (final String arg : args) {
            if (arg.toLowerCase().contains("-force") || arg.contains("-f")) {
                force = true;
                break;
            }
        }
        return force;
    }

}
