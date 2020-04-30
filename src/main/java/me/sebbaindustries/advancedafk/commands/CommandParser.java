package me.sebbaindustries.advancedafk.commands;

import me.sebbaindustries.advancedafk.commands.subs.*;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.jetbrains.annotations.NotNull;

public class CommandParser {

    private static CommandSender sender;
    private static Command command;
    private static String label;
    private static String[] args;

    /**
     * parses arguments and tries to extract flags
     *
     * @param sender  Command sender
     * @param command Command
     * @param label   Aliases
     * @param args    Arguments for parsing
     * @return Flag, if flags are not presented help flag is returned
     */
    public final Flags parse(final @NotNull CommandSender sender, final @NotNull Command command, final @NotNull String label, final String[] args) {

        CommandParser.sender = sender;
        CommandParser.command = command;
        CommandParser.label = label;
        CommandParser.args = args;

        Flags defaultFlag = Flags.HELP;
        // loop thru all arguments and check for flags
        for (String arg : args) {
            arg = arg.toLowerCase();
            for (Flags flag : Flags.values()) {
                if (arg.contains(flag.flag)) return flag;
                if (arg.contains(flag.alias)) defaultFlag = flag;
            }
        }

        return defaultFlag;
    }

    public enum Flags {
        HELP("--help", "--h") {
            @Override
            public void execute() {
                new Help(sender, command, label, args);
            }
        },
        LOOKUP("-lookup", "-l") {
            @Override
            public void execute() {
                new Lookup(sender, command, label, args);
            }
        },
        DEBUG("-debug", "-d") {
            @Override
            public void execute() {
                new Debug(sender, command, label, args);
            }
        },
        CLEANUP("-cleanup", "-c") {
            @Override
            public void execute() {
                new Cleanup(sender, command, label, args);
            }
        },
        LIST("-list", "-ls") {
            @Override
            public void execute() {
                new List(sender, command, label, args);
            }
        },
        RELOAD("-reload", "-r") {
            @Override
            public void execute() {
                new Reload(sender, command, label, args);
            }
        },
        ;

        public final String flag;
        public final String alias;

        public abstract void execute();

        Flags(String flag, String alias) {
            this.flag = flag;
            this.alias = alias;
        }
    }

}
