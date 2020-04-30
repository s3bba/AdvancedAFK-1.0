package me.sebbaindustries.advancedafk.commands.subs;

import me.sebbaindustries.advancedafk.Core;
import me.sebbaindustries.advancedafk.utils.Color;
import me.sebbaindustries.advancedafk.utils.Message;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.jetbrains.annotations.NotNull;

/**
 * @author sebbaindustries
 * @version 1.0
 */
public class Help {

    /**
     * Help constructor, activates on help flag
     *
     * @param sender  command Sender (player, console)
     * @param command command
     * @param label   aliases
     * @param args    command arguments
     */
    public Help(final @NotNull CommandSender sender, final @NotNull Command command, final @NotNull String label, final String[] args) {

        // check for permissions
        if (!sender.hasPermission("aafk.flag.help") || !sender.hasPermission("aafk.*") || !sender.hasPermission("aafk.flag.*")) {
            sender.sendMessage(Core.gCore.message.getMessage(Message.M.noPermission));
            return;
        }

        // send help message ONE day this will be in xml fil but right now I dont give a single fuck
        sender.sendMessage(Color.chat("&8[&dAAFK&8] &7Help Message"));
        sender.sendMessage(Color.chat(""));
        sender.sendMessage(Color.chat(" &8» &f/aafk --help &a[&7--h&a] &e(&7aafk.flag.help&e)"));
        sender.sendMessage(Color.chat(" &8» &7Shows all commands for AdvancedAFK"));
        sender.sendMessage(Color.chat(" &8» &f/aafk -lookup -p &9<&7player&9> &a[&7-l&a] &e(&7aafk.flag.lookup&e)"));
        sender.sendMessage(Color.chat(" &8» &7Shows specified players AFK status and time"));
        sender.sendMessage(Color.chat(" &8» &f/aafk -debug -p &9<&7player&9> &a[&7-d&a] &e(&7aafk.flag.debug&e)"));
        sender.sendMessage(Color.chat(" &8» &7Shows all AFK data of specified player"));
        sender.sendMessage(Color.chat(" &8» &f/aafk -cleanup &c?&7-force&c? &a[&7-c&8, &7-f&a] &e(&7aafk.flag.cleanup&e)"));
        sender.sendMessage(Color.chat(" &8» &7Removes all AFK players from the server"));
        sender.sendMessage(Color.chat(" &8» &7Disregards bypass permissions &8(&7-force&8)"));
        sender.sendMessage(Color.chat(" &8» &f/aafk -list &c?&f-all&c? &a[&7-ls&a] &e(&7aafk.list&e)"));
        sender.sendMessage(Color.chat(" &8» &7Lists all AFK players"));
        sender.sendMessage(Color.chat(" &8» &7Shows all players - AFK and Active &8(&7-all&8)"));
        sender.sendMessage(Color.chat(" &8» &f/aafk -reload &a[&7-r&a] &e(&7aafk.flag.reload&e)"));
        sender.sendMessage(Color.chat(" &8» &7Reloads the plugins files"));
        sender.sendMessage(Color.chat(""));
        sender.sendMessage(Color.chat(" &fAlias&8: &a[]&7, &fPermission&8: &e()&7, &fAditional Flag&8: &c??&7, &fTarget&8: &9<>"));
        sender.sendMessage(Color.chat(""));

    }

}
