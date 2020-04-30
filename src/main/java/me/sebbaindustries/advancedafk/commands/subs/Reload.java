package me.sebbaindustries.advancedafk.commands.subs;

import me.sebbaindustries.advancedafk.Core;
import me.sebbaindustries.advancedafk.utils.Message;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.jetbrains.annotations.NotNull;

/**
 * @author sebbaindustries
 * @version 1.0
 */
public class Reload {

    /**
     * Reload constructor, activates on reload flag
     *
     * @param sender  command Sender (player, console)
     * @param command command
     * @param label   aliases
     * @param args    command arguments
     */
    public Reload(final @NotNull CommandSender sender, final @NotNull Command command, final @NotNull String label, final String[] args) {

        // check for permissions
        if (!sender.hasPermission("aafk.flag.reload") || !sender.hasPermission("aafk.*") || !sender.hasPermission("aafk.flag.*")) {
            sender.sendMessage(Core.gCore.message.getMessage(Message.M.noPermission));
            return;
        }

        // check current time
        long time = System.currentTimeMillis();

        sender.sendMessage(Core.gCore.message.getMessage(Message.M.reloadStart));

        // try to reload, print any errors to console and send sender message that reload failed
        try {
            Core.gCore.fileManager.reloadConfiguration();
            Core.gCore.debug.useDebug = Core.gCore.fileManager.getConfiguration().getBoolean("console.debug");
            Core.gCore.lang.LANG = Core.gCore.fileManager.getConfiguration().getString("language");
            Core.gCore.settings.reloadSettings();
            Core.gCore.message.reloadMessages();
        } catch (Exception e) {
            e.printStackTrace();
            sender.sendMessage(Core.gCore.message.getMessage(Message.M.reloadFailed));
        }
        // calculate reload time
        Long reloadTime = System.currentTimeMillis() - time;
        // prepare message
        String message = Core.gCore.message.getMessage(Message.M.reloadEnd).replace("%time%", String.valueOf(reloadTime));
        sender.sendMessage(message);
    }

}
