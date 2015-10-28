package me.imsean.heybot.commands;

import me.imsean.heybot.HeyBot;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

public class BaseCommand implements CommandExecutor {

    private HeyBot plugin;

    public BaseCommand(HeyBot plugin) {
        this.plugin = plugin;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String command, String[] args) {
        if (args.length == 0) {
            sender.sendMessage(plugin.colorize("&6HeyBot by killazombiecow (http://imsean.me)"));
        } else {
            if (args[0].equalsIgnoreCase("help")) {
                if (sender.hasPermission("heybot.admin")) {
                    sender.sendMessage(plugin.colorize("&7-----&8[&6HeyBot Commands&8]&7-----"));
                    sender.sendMessage(plugin.colorize("&8/heybot reload &7- Reload heybot's configuration"));
                    sender.sendMessage(plugin.colorize("&8/heybot blacklist (add/remove) <username> &7- Add a player to blacklist"));
                } else {
                    sender.sendMessage(plugin.colorize("&cInsufficient permissions."));
                }
            } else if (args[0].equalsIgnoreCase("reload")) {
                if (sender.hasPermission("heybot.admin")) {
                    plugin.reloadConfig();
                    plugin.saveConfig();
                    sender.sendMessage(plugin.colorize("&6HeyBot configuration reloaded!"));
                } else {
                    sender.sendMessage(plugin.colorize("&cInsufficient permissions."));
                }
            } else if (args[0].equalsIgnoreCase("blacklist")) {
                sender.sendMessage(plugin.colorize("&6This feature isn't available yet!"));
                /* if (args[1].equalsIgnoreCase("add")) {
                    Player target = Bukkit.getPlayer(args[1]);
                    if (target == null) {
                        sender.sendMessage(plugin.colorize("&cPlease specify a player"));
                        return false;
                    }
                } */
            }
        }
        return false;
    }
}
