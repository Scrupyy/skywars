package de.scrupy.skywars.game.map;

import de.scrupy.skywars.util.LocationSerializer;
import org.bukkit.Location;

import java.util.ArrayList;
import java.util.List;

public class TestGameMap implements Map {
    @Override
    public String getName() {
        return "Scrupys Map";
    }

    @Override
    public String getBuilder() {
        return "Scrupy";
    }

    @Override
    public List<Location> getSpawnLocations() {
        List<Location> locations = new ArrayList<>();
        String[] testLocations = new String[] {"world;802.5204084746287;7.0;-1008.5356958674134;89.79482;-0.6742685",
                "world;757.5853390005976;17.0;-998.9891304152691;-144.21999;11.433676"};
        for (String locationString : testLocations) {
            locations.add(LocationSerializer.deserialize(locationString));
        }
        return locations;
    }
}
