package de.scrupy.skywars.listener;

import de.scrupy.skywars.MessageConfig;
import de.scrupy.skywars.game.lobby.LobbySpawn;
import de.scrupy.skywars.player.PlayerAttributes;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

public class PlayerJoinListener implements Listener {
    private final MessageConfig messageConfig;
    private final PlayerAttributes playerAttributes;
    private final LobbySpawn lobbySpawn;

    public PlayerJoinListener(MessageConfig messageConfig, PlayerAttributes playerAttributes, LobbySpawn lobbySpawn) {
        this.messageConfig = messageConfig;
        this.playerAttributes = playerAttributes;
        this.lobbySpawn = lobbySpawn;
    }

    @EventHandler
    public void onJoin(PlayerJoinEvent joinEvent) {
        Player player = joinEvent.getPlayer();
        String playerName = player.getName();
        int currentPlayerAmount = Bukkit.getOnlinePlayers().size();
        int maxPlayerAmount = Bukkit.getMaxPlayers();

        String playerJoinMessage = messageConfig.getMessage("playerJoinMessage",
                playerName, String.valueOf(currentPlayerAmount), String.valueOf(maxPlayerAmount));
        joinEvent.setJoinMessage(playerJoinMessage);

        playerAttributes.setAttributes(player);
        lobbySpawn.teleportPlayer(player);
    }
}
