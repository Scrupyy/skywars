package de.scrupy.skywars.game.map;

import org.bukkit.Location;

import java.util.List;

public interface Map {
    String getName();
    String getBuilder();
    List<Location> getSpawnLocations();
}
