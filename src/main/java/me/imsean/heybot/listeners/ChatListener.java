package me.imsean.heybot.listeners;

import me.imsean.heybot.HeyBot;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Random;

public class ChatListener implements Listener {

	private HeyBot plugin;
	private String beginsWith;
	private String prefix;

    public ChatListener(HeyBot plugin) {
        this.plugin = plugin;
        this.beginsWith = plugin.colorize(plugin.getConfig().getString("begins-with"));
        this.prefix = plugin.colorize(plugin.getConfig().getString("bot-prefix"));
    }

	@EventHandler
	public void onPlayerChat(AsyncPlayerChatEvent e) {
		if(prefix == "none") {
			prefix = "";
		}
		final Player p = e.getPlayer();
		String message = e.getMessage();
		if(!message.startsWith(beginsWith)) return;
		message = message.replace(beginsWith, "").trim();

		if(message.isEmpty()) return;

		final List<String> responses = plugin.getConfig().getStringList("messages." + message + ".responses");
		if(responses.isEmpty()) return;
		plugin.getServer().getScheduler().scheduleSyncDelayedTask(plugin, new Runnable() {
			public void run() {
				p.sendMessage(ChatColor.translateAlternateColorCodes('&', prefix) + formatResponse(getRandomResponse(responses), p));
			}
		}, 1L);
	}

	public String getRandomResponse(@SuppressWarnings("rawtypes") List list) {
		Random random = new Random();
		int idx = random.nextInt(list.size());
		String response = list.get(idx).toString();
		return response;
	}

	public String formatResponse(String str, Player p) {
		str = str.replace("%player name%", p.getName())
				 .replace("%player display name%", p.getDisplayName())
			     .replace("%date%", new SimpleDateFormat("yyyy/MM/dd").format(new Date()));
		str = ChatColor.translateAlternateColorCodes('&', str);
		return str;
	}

}
