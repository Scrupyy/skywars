package de.scrupy.skywars.game.scoreboard;

import de.scrupy.skywars.game.kit.Kit;
import de.scrupy.skywars.game.kit.KitHandler;
import de.scrupy.skywars.game.map.Map;
import de.scrupy.skywars.game.team.TeamHandler;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.scoreboard.*;

import java.util.Objects;

public class PlayerScoreboard {
    private final static String VALUE_PREFIX = "§f>> §7";
    private final static String SCOREBOARD_TITLE = " §b§lScrupy´s SkyWars ";
    private final static String KIT_TITLE = "§e§lKit§7:";
    private final static String TEAM_TITLE = "§e§lTeam§7:";
    private final static String Map_TITLE = "§e§lMap§7:";
    private final Scoreboard scoreboard;
    private final Player player;

    public PlayerScoreboard(Player player, TeamHandler teamHandler, KitHandler kitHandler, Map map) {
        this.player = player;
        this.scoreboard = Bukkit.getScoreboardManager().getNewScoreboard();
        Objective objective = scoreboard.registerNewObjective("SkyWars", "dummy", SCOREBOARD_TITLE);
        objective.setDisplaySlot(DisplaySlot.SIDEBAR);

        Team selectedKit = scoreboard.registerNewTeam("selectedKit");
        selectedKit.addEntry(ChatColor.BLACK + "");
        selectedKit.setPrefix(VALUE_PREFIX + kitHandler.getSelectedKit(player).getDisplayName());

        Team selectedTeam = scoreboard.registerNewTeam("selectedTeam");
        selectedTeam.addEntry(ChatColor.RED + "");
        if (teamHandler.getTeam(player) == null) {
            selectedTeam.setPrefix(VALUE_PREFIX + "§7-");
        } else {
            selectedTeam.setPrefix(VALUE_PREFIX + teamHandler.getTeam(player).getColouredTeamName());
        }

        objective.getScore(" ").setScore(10);
        objective.getScore(Map_TITLE).setScore(9);
        objective.getScore(VALUE_PREFIX + map.getName()).setScore(8);
        objective.getScore("  ").setScore(7);
        objective.getScore(TEAM_TITLE).setScore(6);
        objective.getScore(ChatColor.RED + "").setScore(5);
        objective.getScore("   ").setScore(4);
        objective.getScore(KIT_TITLE).setScore(3);
        objective.getScore(ChatColor.BLACK + "").setScore(2);
        objective.getScore("    ").setScore(1);
    }

    public static void updateKit(Player player, Kit selectedKit) {
        Objects.requireNonNull(player.getScoreboard().getTeam("selectedKit")).setPrefix(VALUE_PREFIX + selectedKit.getDisplayName());
    }

    public static void updateTeam(Player player, de.scrupy.skywars.game.team.Team selectedTeam) {
        Objects.requireNonNull(player.getScoreboard().getTeam("selectedTeam")).setPrefix(VALUE_PREFIX + selectedTeam.getColouredTeamName());
    }

    public void setScoreboard() {
        player.setScoreboard(scoreboard);
    }
}
