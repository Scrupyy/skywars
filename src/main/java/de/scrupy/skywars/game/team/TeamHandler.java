package de.scrupy.skywars.game.team;

import de.scrupy.skywars.GameSettings;
import de.scrupy.skywars.MessageConfig;
import de.scrupy.skywars.game.scoreboard.PlayerScoreboard;
import org.bukkit.entity.Player;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class TeamHandler {
    private final MessageConfig messageConfig;
    private final GameSettings gameSettings;
    private final Map<Player, Team> playerTeams;

    public TeamHandler(MessageConfig messageConfig, GameSettings gameSettings) {
        this.messageConfig = messageConfig;
        this.gameSettings = gameSettings;
        this.playerTeams = new HashMap<>();
    }

    public void addPlayerToTeam(Player player, Team team) {
        if (team.getPlayers().contains(player)) {
            player.sendMessage(messageConfig.getMessage("playerAlreadyJoinedThisTeam"));
            return;
        }
        if (team.getPlayers().size() >= gameSettings.getTeamSize()) {
            player.sendMessage(messageConfig.getMessage("teamIsFull"));
            return;
        }
        removePlayerFromTeam(player);
        team.getPlayers().add(player);
        playerTeams.put(player, team);
        PlayerScoreboard.updateTeam(player, team);
        player.sendMessage(messageConfig.getMessage("playerJoinedNewTeam", team.getColouredTeamName()));
    }

    public void removePlayerFromTeam(Player player) {
        if (playerTeams.get(player) != null) {
            Team team = playerTeams.get(player);
            team.getPlayers().remove(player);
        }
    }

    public Team getTeam(Player player) {
        return playerTeams.get(player);
    }

    public Team[] getAvailableTeams() {
        Team[] teams = Team.values();
        int teamAmount = gameSettings.getTeamAmount();
        if (teamAmount >= teams.length) {
            return teams;
        }
        return Arrays.copyOf(teams, teamAmount);
    }
}
