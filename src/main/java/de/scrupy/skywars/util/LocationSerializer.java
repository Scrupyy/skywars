package de.scrupy.skywars.util;

import org.bukkit.Bukkit;
import org.bukkit.Location;

public class LocationSerializer {
    public static String serialize(Location location) {
        if (location == null) {
            return null;
        }
        String worldName = location.getWorld().getName();
        double x = location.getX();
        double y = location.getY();
        double z = location.getZ();
        float yaw = location.getYaw();
        float pitch = location.getPitch();
        return worldName + ";" + x + ";" + y + ";" + z + ";" + yaw + ";" + pitch;
    }

    public static Location deserialize(String locationString) {
        if (locationString == null) {
            return null;
        }
        String[] parts = locationString.split(";");
        String worldName = parts[0];
        double x = Double.parseDouble(parts[1]);
        double y =  Double.parseDouble(parts[2]);
        double z =  Double.parseDouble(parts[3]);
        float yaw = Float.parseFloat(parts[4]);
        float pitch = Float.parseFloat(parts[5]);

        return new Location(Bukkit.getWorld(worldName), x, y, z, yaw, pitch);
    }
}
