package me.imsean.heybot;

import me.imsean.heybot.commands.BaseCommand;
import me.imsean.heybot.listeners.ChatListener;
import org.bukkit.ChatColor;
import org.bukkit.plugin.java.JavaPlugin;

public class HeyBot extends JavaPlugin {

	@Override
	public void onEnable() {
        saveDefaultConfig();

		getCommand("heybot").setExecutor(new BaseCommand(this));
        getServer().getPluginManager().registerEvents(new ChatListener(this), this);
	}

    public String colorize(String message) {
        return message == null ? message : ChatColor.translateAlternateColorCodes('&', message);
    }
}
