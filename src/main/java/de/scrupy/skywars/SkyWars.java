package de.scrupy.skywars;

import de.scrupy.skywars.command.SetLobbySpawn;
import de.scrupy.skywars.listener.GameStateEventHandler;
import de.scrupy.skywars.listener.PlayerJoinListener;
import de.scrupy.skywars.game.kit.KitGui;
import de.scrupy.skywars.game.kit.KitGuiEvent;
import de.scrupy.skywars.game.kit.KitHandler;
import de.scrupy.skywars.game.lobby.LobbyItems;
import de.scrupy.skywars.game.scoreboard.ScoreboardListener;
import de.scrupy.skywars.game.team.TeamSelectionListener;
import de.scrupy.skywars.game.*;
import de.scrupy.skywars.game.lobby.LobbySpawn;
import de.scrupy.skywars.game.map.Map;
import de.scrupy.skywars.game.map.PlayerMapTeleport;
import de.scrupy.skywars.game.map.TestGameMap;
import de.scrupy.skywars.game.map.WorldSetup;
import de.scrupy.skywars.game.team.TeamHandler;
import de.scrupy.skywars.game.team.TeamSelectorGui;
import de.scrupy.skywars.player.PlayerAttributes;
import org.bukkit.Bukkit;
import org.bukkit.Difficulty;
import org.bukkit.World;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

public class SkyWars extends JavaPlugin {
    private GameState gameState;
    private MessageConfig messageConfig;
    private PlayerAttributes playerAttributes;
    private LobbySpawn lobbySpawn;
    private Map map;
    private PlayerMapTeleport playerMapTeleport;
    private GameSettings gameSettings;
    private TeamHandler teamHandler;
    private TeamSelectorGui teamSelectorGui;
    private KitHandler kitHandler;
    private KitGui kitGui;

    @Override
    public void onEnable() {
        saveDefaultConfig();
        setupWorlds();

        this.gameState = GameState.LOBBY;

        messageConfig = new MessageConfig(this);
        playerAttributes = new PlayerAttributes(this);
        lobbySpawn = new LobbySpawn(this);
        map = new TestGameMap();
        playerMapTeleport = new PlayerMapTeleport(map);
        gameSettings = new GameSettings(this);
        teamHandler = new TeamHandler(messageConfig, gameSettings);
        teamSelectorGui = new TeamSelectorGui(messageConfig, gameSettings, teamHandler);
        kitHandler = new KitHandler(messageConfig);
        kitGui = new KitGui(messageConfig, kitHandler);

        registerListener();
        registerCommands();

        Bukkit.getConsoleSender().sendMessage(messageConfig.getMessage("pluginStarted"));
    }

    @Override
    public void onDisable() {
        Bukkit.getConsoleSender().sendMessage(messageConfig.getMessage("pluginStopped"));
    }

    public GameState getGameState() {
        return gameState;
    }

    public void setGameState(GameState gameState) {
        this.gameState = gameState;
    }

    private void registerListener() {
        PluginManager pluginManager = Bukkit.getPluginManager();
        pluginManager.registerEvents(new PlayerJoinListener(messageConfig, playerAttributes, lobbySpawn), this);
        pluginManager.registerEvents(new GameStateEventHandler(this), this);
        pluginManager.registerEvents(new TeamSelectionListener(messageConfig, teamHandler, teamSelectorGui), this);
        pluginManager.registerEvents(new LobbyItems(this, messageConfig, gameSettings, teamSelectorGui, kitGui), this);
        pluginManager.registerEvents(new KitGuiEvent(messageConfig, kitHandler), this);
        pluginManager.registerEvents(new ScoreboardListener(teamHandler, kitHandler, map), this);
    }

    private void registerCommands() {
        this.getCommand("setLobbySpawn").setExecutor(new SetLobbySpawn(this, messageConfig));
    }

    private void setupWorlds() {
        WorldSetup worldSetup = new WorldSetup();
        for (World world : Bukkit.getWorlds()) {
            worldSetup.setWorld(world);
            worldSetup.setDayTime();
            worldSetup.setWeatherClear();
            worldSetup.setDifficulty(Difficulty.PEACEFUL);
            worldSetup.setDaylightCycle(false);
        }
    }
}