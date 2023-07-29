package de.scrupy.skywars.game.map;

import org.bukkit.Difficulty;
import org.bukkit.GameRule;
import org.bukkit.World;

public class WorldSetup {
    private World world;

    public void setDayTime() {
        world.setTime(1000);
    }

    public void setNightTime() {
        world.setTime(13000);
    }

    public void setWeatherClear() {
        world.setStorm(false);
        world.setThundering(false);
        world.setWeatherDuration(0);
    }

    public void setDifficulty(Difficulty difficulty) {
        world.setDifficulty(difficulty);
    }

    public void setDaylightCycle(boolean value) {
        this.world.setGameRule(GameRule.DO_DAYLIGHT_CYCLE, value);
    }

    public void setWorld(World world) {
        this.world = world;
    }
}
