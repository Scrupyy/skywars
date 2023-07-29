package de.scrupy.skywars.player;

import de.scrupy.skywars.SkyWars;
import de.scrupy.skywars.game.GameState;
import org.bukkit.Bukkit;
import org.bukkit.GameMode;
import org.bukkit.entity.Player;

public class PlayerAttributes {
    private final SkyWars plugin;

    public PlayerAttributes(SkyWars plugin) {
        this.plugin = plugin;
    }

    public void setAttributes(Player player) {
        if (plugin.getGameState() == GameState.LOBBY) {
            setAttributesForLobby(player);
        }
    }

    public void updatePlayerAttributes() {
        for (Player player : Bukkit.getOnlinePlayers()) {
            setAttributes(player);
        }
    }

    private void setAttributesForLobby(Player player) {
        player.setHealth(20);
        player.setFoodLevel(20);
        player.setExp(0);
        player.setLevel(0);
        player.setAllowFlight(false);
        player.setGameMode(GameMode.ADVENTURE);
        player.setGlowing(false);
        player.getInventory().clear();
    }
}
