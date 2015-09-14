package me.imsean.heybot;

import me.imsean.heybot.commands.BaseCommand;
import me.imsean.heybot.listeners.ChatListener;

import org.bukkit.Bukkit;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.event.Listener;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.java.JavaPlugin;

public class HeyBot extends JavaPlugin {
	
	public static Plugin plugin;
	
	@Override
	public void onEnable() {
		plugin = this;

		final FileConfiguration config = this.getConfig();
		config.options().copyDefaults(true);
		saveConfig();
		
		getCommand("heybot").setExecutor(new BaseCommand());
		
		registerEvents(this, 
				new ChatListener()
				);
	}
	
	@Override
	public void onDisable() {
		plugin = null;
	}
	
	public static Plugin getInstance() {
		return plugin;
	}
	
    public static void registerEvents(Plugin plugin, Listener... listeners) {
        Listener[] arrayOfListener;
        int j = (arrayOfListener = listeners).length;
        for (int i = 0; i < j; i++) {
            Listener listener = arrayOfListener[i];
            Bukkit.getServer().getPluginManager().registerEvents(listener, plugin);
        }
    }

}
