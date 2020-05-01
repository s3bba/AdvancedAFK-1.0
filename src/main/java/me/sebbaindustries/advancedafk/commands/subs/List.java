package me.sebbaindustries.advancedafk.commands.subs;

import me.sebbaindustries.advancedafk.Core;
import me.sebbaindustries.advancedafk.detection.PlayerData;
import me.sebbaindustries.advancedafk.detection.Storage;
import me.sebbaindustries.advancedafk.utils.Color;
import me.sebbaindustries.advancedafk.utils.Message;
import org.apache.commons.lang.StringUtils;
import org.bukkit.Bukkit;
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


        if (useAllFlag(args)) {
            String message = Core.gCore.message.getMessage(Message.M.listLSALL_flag);
            String activePlayers = "";
            String timedPlayers = "";
            String afkPlayers = "";

            for (Player p : Bukkit.getOnlinePlayers()) {
                int afkTime = PlayerData.playerStorageHashMap.get(p).afkTime;
                if (afkTime >= Core.gCore.settings.kickTime) {
                    afkPlayers = afkPlayers + "&c" + p.getName() + "&f, ";
                    continue;
                }
                if (afkTime > 0) {
                    timedPlayers = timedPlayers + "&e" + p.getName() + "&f, ";
                    continue;
                }
                activePlayers = activePlayers + "&a" + p.getName() + "&f, ";

            }
            activePlayers = StringUtils.chop(activePlayers);
            timedPlayers = StringUtils.chop(timedPlayers);
            afkPlayers = StringUtils.chop(afkPlayers);

            sender.sendMessage(Color.chat(message
                    .replace("%active_players%", activePlayers)
                    .replace("%timed_players%", timedPlayers)
                    .replace("%afk_players%", afkPlayers)));
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

    /**
     * Check if force flag is present in arguments
     *
     * @param args command arguments
     * @return true if flag is present
     */
    private boolean useAllFlag(final String[] args) {
        boolean allFlag = false;
        for (String arg : args) {
            if (arg.toLowerCase().contains("-all")) {
                allFlag = true;
                break;
            }
        }
        return allFlag;
    }

}
