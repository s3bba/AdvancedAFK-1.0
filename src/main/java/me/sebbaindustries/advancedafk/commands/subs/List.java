package me.sebbaindustries.advancedafk.commands.subs;

import me.sebbaindustries.advancedafk.Core;
import me.sebbaindustries.advancedafk.utils.Color;
import me.sebbaindustries.advancedafk.utils.Message;
import org.apache.commons.lang.StringUtils;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

/**
 * @author sebbaindustries
 * @version 1.0
 */
public class List {

    /**
     * List constructor, activates on list flag
     *
     * @param sender  command Sender (player, console)
     * @param command command
     * @param label   aliases
     * @param args    command arguments
     */
    public List(final @NotNull CommandSender sender, final @NotNull Command command, final @NotNull String label, final String[] args) {

        // check for permissions
        if (!sender.hasPermission("aafk.flag.list") || !sender.hasPermission("aafk.*") || !sender.hasPermission("aafk.flag.*")) {
            sender.sendMessage(Core.gCore.message.getMessage(Message.M.noPermission));
            return;
        }


        String message = Core.gCore.message.getMessage(Message.M.listLS_Flag);
        if (Core.gCore.playerData.kickList.size() == 0) {
            sender.sendMessage(Core.gCore.message.getMessage(Message.M.listNoPlayers));
            return;
        }
        String temp = "";
        for (Player p : Core.gCore.playerData.kickList) {
            temp = temp + "&f" + p.getName() + "&8, ";
        }
        // apache thanks for string utils <3
        sender.sendMessage(Color.chat(message.replace("%list%", StringUtils.chop(temp))));
    }

}
