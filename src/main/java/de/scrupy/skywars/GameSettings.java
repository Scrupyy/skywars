package de.scrupy.skywars;

import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.plugin.Plugin;

public class GameSettings {
    private final Plugin plugin;
    private final int MAX_PLAYER_AMOUNT;
    private final int PLAYER_AMOUNT_TO_START;
    private final int TEAM_SIZE;
    private final int TEAM_AMOUNT;
    private final int LOBBY_TIMER_DURATION;
    private final int TEAM_SELECTOR_ITEM_SLOT;
    private final int KIT_SELECTOR_ITEM_SLOT;

    private static final String GAME_SETTINGS_KEY = "gameSettings";

    public GameSettings(Plugin plugin) {
        this.plugin = plugin;
        this.PLAYER_AMOUNT_TO_START = getInteger("playerAmountToStart");
        this.TEAM_SIZE = getInteger("teamSize");
        this.TEAM_AMOUNT = getInteger("teamAmount");
        this.MAX_PLAYER_AMOUNT = TEAM_AMOUNT * TEAM_SIZE;
        this.LOBBY_TIMER_DURATION = getInteger("lobbyTimerDuration");
        this.TEAM_SELECTOR_ITEM_SLOT = getInteger("teamSelectorItemSlot");
        this.KIT_SELECTOR_ITEM_SLOT = getInteger("kitSelectorItemSlot");
    }

    public int getMaxPlayerAmount() {
        return MAX_PLAYER_AMOUNT;
    }

    public int getPlayerAmountToStart() {
        return PLAYER_AMOUNT_TO_START;
    }

    public int getTeamSize() {
        return TEAM_SIZE;
    }

    public int getLobbyTimeDuration() {
        return LOBBY_TIMER_DURATION;
    }

    public int getTeamAmount() {
        return TEAM_AMOUNT;
    }

    public int getTeamSelectorItemSlot() {
        return TEAM_SELECTOR_ITEM_SLOT;
    }

    public int getKitSelectorItemSlot() {
        return KIT_SELECTOR_ITEM_SLOT;
    }

    private int getInteger(String key) {
        ConfigurationSection configurationSection = getConfigurationSection();
        return configurationSection.getInt(key);
    }

    private ConfigurationSection getConfigurationSection() {
        ConfigurationSection configurationSection = plugin.getConfig().getConfigurationSection(GAME_SETTINGS_KEY);
        if (configurationSection == null) {
            throw new IllegalStateException("Config section " + GAME_SETTINGS_KEY + " not found");
        }
        return configurationSection;
    }
}
