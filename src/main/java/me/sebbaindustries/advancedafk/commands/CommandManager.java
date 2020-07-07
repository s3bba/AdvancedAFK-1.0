package me.sebbaindustries.advancedafk.commands;

import me.sebbaindustries.advancedafk.Core;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;
import org.jetbrains.annotations.NotNull;

import java.util.List;
import java.util.Objects;

public final class CommandManager implements CommandExecutor, TabCompleter {

    private final CommandParser parser;

    /**
     * registers commands and creates new parser instance
     *
     * @param core Main class
     */
    public CommandManager(@NotNull final Core core) {
        Objects.requireNonNull(core.getCommand("aafk")).setExecutor(this);
        Objects.requireNonNull(Bukkit.getPluginCommand("aafk")).setTabCompleter(this);
        parser = new CommandParser();
    }

    @Override
    public boolean onCommand(final @NotNull CommandSender sender, final @NotNull Command command, final @NotNull String label, final String[] args) {
        Objects.requireNonNull(parser.parse(sender, command, label, args)).execute();
        return true;
    }

    @Override
    public List<String> onTabComplete(final @NotNull CommandSender sender, final @NotNull Command cmd, final @NotNull String label, final String[] args) {
        return null;
    }

}
