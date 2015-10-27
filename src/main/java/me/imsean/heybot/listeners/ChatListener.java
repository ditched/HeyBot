package me.imsean.heybot.listeners;

import me.imsean.heybot.HeyBot;
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
    private Random random = new Random();

    public ChatListener(HeyBot plugin) {
        this.plugin = plugin;
        this.beginsWith = plugin.colorize(plugin.getConfig().getString("begins-with"));
        this.prefix = plugin.colorize(plugin.getConfig().getString("bot-prefix"));

        if (prefix.equalsIgnoreCase("none")) {
            prefix = "";
        }
    }

	@EventHandler
	public void onPlayerChat(AsyncPlayerChatEvent e) {
        Player p = e.getPlayer();
		String message = e.getMessage();

        // Only want messages to the bot should be considered
		if(!message.startsWith(beginsWith)) {
            return;
        }
		message = message.replace(beginsWith, "").trim();

        if (!message.isEmpty()) {
            List<String> responses = plugin.getConfig().getStringList("messages." + message + ".responses");

            if (responses == null || responses.isEmpty()) {
                return;
            }

            String randomResponse = this.getRandomResponse(responses);
            p.sendMessage(this.formatResponse(randomResponse, p));
        }
	}

	public String getRandomResponse(List<String> list) {
        return list.get(random.nextInt(list.size()));
	}

	public String formatResponse(String str, Player p) {
		str = str.replace("%player name%", p.getName())
				 .replace("%player display name%", p.getDisplayName())
			     .replace("%date%", new SimpleDateFormat("yyyy/MM/dd").format(new Date()));
		str = plugin.colorize(str);
		return str;
	}
}
