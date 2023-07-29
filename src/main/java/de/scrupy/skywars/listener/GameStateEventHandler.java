package de.scrupy.skywars.listener;

import de.scrupy.skywars.SkyWars;
import de.scrupy.skywars.game.GameState;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.entity.FoodLevelChangeEvent;
import org.bukkit.event.player.PlayerDropItemEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.weather.WeatherChangeEvent;

public class GameStateEventHandler implements Listener {
    private final SkyWars plugin;

    public GameStateEventHandler(SkyWars plugin) {
        this.plugin = plugin;
    }

    @EventHandler
    public void onFoodLevelChange(FoodLevelChangeEvent foodLevelChange) {
        if (plugin.getGameState() == GameState.LOBBY)
            foodLevelChange.setCancelled(true);
    }

    @EventHandler
    public void onEntityDamage(EntityDamageEvent entityDamage) {
        if (plugin.getGameState() == GameState.LOBBY)
            entityDamage.setCancelled(true);
    }

    @EventHandler
    public void onBlockBreak(BlockBreakEvent blockBreak) {
        if (plugin.getGameState() == GameState.LOBBY)
            blockBreak.setCancelled(true);
    }

    @EventHandler
    public void onBlockPlace(BlockPlaceEvent blockPlace) {
        if (plugin.getGameState() == GameState.LOBBY)
            blockPlace.setCancelled(true);
    }

    @EventHandler
    public void onWeatherChange(WeatherChangeEvent weatherChange) {
        if (plugin.getGameState() == GameState.LOBBY)
            weatherChange.setCancelled(true);
    }

    @EventHandler
    public void onInteract(PlayerInteractEvent interact) {
        if (plugin.getGameState() == GameState.LOBBY)
            interact.setCancelled(true);
    }

    @EventHandler
    public void onPlayerDropItem(PlayerDropItemEvent playerDropItem) {
        if (plugin.getGameState() == GameState.LOBBY)
            playerDropItem.setCancelled(true);
    }
}
