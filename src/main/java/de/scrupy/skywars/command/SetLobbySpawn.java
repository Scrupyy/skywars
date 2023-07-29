package de.scrupy.skywars.command;

import de.scrupy.skywars.MessageConfig;
import de.scrupy.skywars.util.LocationSerializer;
import org.bukkit.Location;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;
import org.bukkit.plugin.Plugin;

public class SetLobbySpawn implements CommandExecutor, Listener {
    private final Plugin plugin;
    private final MessageConfig messageConfig;

    public SetLobbySpawn(Plugin plugin, MessageConfig messageConfig) {
        this.plugin = plugin;
        this.messageConfig = messageConfig;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (sender instanceof Player player) {
            Location playerLocation = player.getLocation();
            if (player.hasPermission("lobby.setspawn")) {
                String locationString = LocationSerializer.serialize(playerLocation);
                plugin.getConfig().set("lobbySpawnLocation", locationString);
                plugin.saveConfig();
                player.sendMessage(messageConfig.getMessage("lobbySpawnLocationUpdated"));
            }
        } else {
            sender.sendMessage(messageConfig.getMessage("wrongCommandSender"));
        }
        return true;
    }
}
