package me.imsean.heybot.commands;

import me.imsean.heybot.HeyBot;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;

public class BaseCommand implements CommandExecutor {
	
	Plugin plugin = HeyBot.getInstance();

	@SuppressWarnings("unused")
	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String command, String[] args) {
		Player p = (Player)sender;
		if(command.equalsIgnoreCase("heybot")) {
			if(args.length == 0) {
				p.sendMessage(colorize("&6HeyBot by killazombiecow (http://imsean.me)"));
			}
			if(args.length > 0) {
				if(args[0].equalsIgnoreCase("help")) {
					if(p.hasPermission("heybot.admin")) {
						p.sendMessage(colorize("&7-----&8[&6HeyBot Commands&8]&7-----"));
						p.sendMessage(colorize("&8/heybot reload &7- Reload heybot's configuration"));
						p.sendMessage(colorize("&8/heybot blacklist (add/remove) <username> &7- Add a player to blacklist"));
					} else {
						p.sendMessage(colorize("&cInsufficient permissions."));
					}
				}
				if(args[0].equalsIgnoreCase("reload")) {
					if(p.hasPermission("heybot.admin")) {
						plugin.reloadConfig();
						plugin.saveConfig();
						p.sendMessage(colorize("&6HeyBot configuration reloaded!"));
					} else {
						p.sendMessage(colorize("&cInsufficient permissions."));
					}
				}
				if(args[0].equalsIgnoreCase("blacklist")) {
					if(true) {
						p.sendMessage(colorize("&6This feature isn't available yet!"));
						return false;
					}
					if(args[1].equalsIgnoreCase("add")) {
						Player target = Bukkit.getPlayer(args[1]);
						if(target == null) {
							p.sendMessage(colorize("&cPlease specify a player"));
							return false;
						}
					}
				}
			}
		}
		return false;
	}
	
	public static String colorize(String colorize) {
		return ChatColor.translateAlternateColorCodes('&', colorize);
	}

}
