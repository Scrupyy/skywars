package de.scrupy.skywars.game.scoreboard;

import de.scrupy.skywars.game.kit.KitHandler;
import de.scrupy.skywars.game.map.Map;
import de.scrupy.skywars.game.team.TeamHandler;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

public class ScoreboardListener implements Listener {
    private final TeamHandler teamHandler;
    private final KitHandler kitHandler;
    private final Map map;

    public ScoreboardListener(TeamHandler teamHandler, KitHandler kitHandler, Map map) {
        this.teamHandler = teamHandler;
        this.kitHandler = kitHandler;
        this.map = map;
    }

    @EventHandler
    public void onJoin(PlayerJoinEvent joinEvent) {
        Player player = joinEvent.getPlayer();
        PlayerScoreboard playerScoreboard = new PlayerScoreboard(player, teamHandler, kitHandler, map);
        playerScoreboard.setScoreboard();
    }
}
