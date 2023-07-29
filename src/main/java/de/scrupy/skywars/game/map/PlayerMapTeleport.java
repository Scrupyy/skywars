package de.scrupy.skywars.game.map;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

public class PlayerMapTeleport {
    private final Map map;

    public PlayerMapTeleport(Map map) {
        this.map = map;
    }

    public void teleportAllToRandomLocation() {
        List<Location> spawnLocations = new ArrayList<>(map.getSpawnLocations());
        for (Player player : Bukkit.getOnlinePlayers()) {
            Location randomLocation = getRandomLocation(spawnLocations);
            if (randomLocation != null) {
                player.teleport(randomLocation);
                spawnLocations.remove(randomLocation);
            }
        }
    }

    private Location getRandomLocation(List<Location> locations) {
        if (locations.isEmpty())
            return null;
        int randomIndex = ThreadLocalRandom.current().nextInt(locations.size());
        return locations.get(randomIndex);
    }
}
