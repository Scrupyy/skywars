package de.scrupy.skywars;

import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.plugin.Plugin;

import java.util.HashMap;
import java.util.Map;

public class MessageConfig {
    private final Plugin plugin;
    private final Map<String, String> messages;
    private final String prefix;

    public MessageConfig(Plugin plugin) {
        this.plugin = plugin;
        this.messages = new HashMap<>();
        this.prefix = getMessage("prefix");
    }

    public String getMessage(String key) {
        return messages.computeIfAbsent(key, message -> loadMessage(key));
    }

    public String getMessage(String key, String... args) {
        String message = getMessage(key);
        return  String.format(message, args);
    }

    private String loadMessage(String key) {
        ConfigurationSection config = plugin.getConfig().getConfigurationSection("messages");
        String message = config.getString(key);
        if (message == null) {
            return "Message not found for key: " + key + ".";
        }
        return replacePrefixInMessage(message);
    }

    private String replacePrefixInMessage(String message) {
        return message.replaceAll("%p", prefix);
    }
}
