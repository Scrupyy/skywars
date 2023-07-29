package de.scrupy.skywars.game.lobby;

import de.scrupy.skywars.util.LocationSerializer;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;

public class LobbySpawn {
    private final Plugin plugin;
    private Location location;

    public LobbySpawn(Plugin plugin) {
        this.plugin = plugin;
        this.location = loadSpawnLocation();
    }

    public void teleportPlayer(Player player) {
        if (location != null)
            player.teleport(location);
    }

    public void teleportAllPlayers() {
        for (Player player : Bukkit.getOnlinePlayers()) {
            teleportPlayer(player);
        }
    }

    private Location loadSpawnLocation() {
        String locationString = plugin.getConfig().getString("lobbySpawnLocation");
        if (locationString != null) {
            return LocationSerializer.deserialize(locationString);
        }
        return null;
    }
}
