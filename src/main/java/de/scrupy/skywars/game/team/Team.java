package de.scrupy.skywars.game.team;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.List;

public enum Team {
    RED(ChatColor.RED, Material.RED_WOOL),
    GREEN(ChatColor.GREEN, Material.LIME_WOOL),
    BLUE(ChatColor.BLUE, Material.BLUE_WOOL),
    YELLOW(ChatColor.YELLOW, Material.YELLOW_WOOL),
    WHITE(ChatColor.WHITE, Material.WHITE_WOOL),
    ORANGE(ChatColor.GOLD, Material.ORANGE_WOOL),
    PURPLE(ChatColor.LIGHT_PURPLE, Material.PURPLE_WOOL),
    BLACK(ChatColor.BLACK, Material.BLACK_WOOL);

    public final ChatColor chatColor;
    private final Material teamSelectionItem;
    private final List<Player> players;

    Team(ChatColor chatColor, Material teamSelectionItem) {
        this.chatColor = chatColor;
        this.teamSelectionItem = teamSelectionItem;
        this.players = new ArrayList<>();
    }

    public ChatColor getChatColor() {
        return chatColor;
    }

    public Material getTeamSelectionItemMaterial() {
        return teamSelectionItem;
    }

    public String getColouredTeamName() {
        return getChatColor().toString() + this.name().charAt(0) + this.name().substring(1).toLowerCase();
    }

    public List<Player> getPlayers() {
        return players;
    }
}
