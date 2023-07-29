package de.scrupy.skywars.game.lobby;

import de.scrupy.skywars.GameSettings;
import de.scrupy.skywars.MessageConfig;
import de.scrupy.skywars.SkyWars;
import de.scrupy.skywars.game.GameState;
import de.scrupy.skywars.game.kit.KitGui;
import de.scrupy.skywars.game.team.TeamSelectorGui;
import de.scrupy.skywars.util.ItemBuilder;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.inventory.EquipmentSlot;
import org.bukkit.inventory.ItemStack;

public class LobbyItems implements Listener {
    private final SkyWars plugin;
    private final MessageConfig messageConfig;
    private final GameSettings gameSettings;
    private final TeamSelectorGui teamSelectorGui;
    private final KitGui kitGui;

    public LobbyItems(
            SkyWars plugin,
            MessageConfig messageConfig,
            GameSettings gameSettings,
            TeamSelectorGui teamSelectorGui,
            KitGui kitGui
    ) {
        this.plugin = plugin;
        this.messageConfig = messageConfig;
        this.gameSettings = gameSettings;
        this.teamSelectorGui = teamSelectorGui;
        this.kitGui = kitGui;
    }

    @EventHandler
    public void onJoin(PlayerJoinEvent playerJoin) {
        if (plugin.getGameState() == GameState.LOBBY) {
            giveItems(playerJoin.getPlayer());
        }
    }

    @EventHandler
    public void onInteract(PlayerInteractEvent interact) {
        Player player = interact.getPlayer();
        if (plugin.getGameState() != GameState.LOBBY)
            return;
        if (interact.getHand() != EquipmentSlot.HAND) {
            return;
        }
        if (interact.getAction() == Action.RIGHT_CLICK_AIR || interact.getAction() == Action.RIGHT_CLICK_BLOCK) {
            int heldItemSlot = player.getInventory().getHeldItemSlot();
            if (heldItemSlot == gameSettings.getTeamSelectorItemSlot()) {
                teamSelectorGui.open(player);
            }
            if (heldItemSlot == gameSettings.getKitSelectorItemSlot()) {
                kitGui.open(player);
            }
        }
    }

    private void giveItems(Player player) {
        String teamSelectorItemName = messageConfig.getMessage("teamSelectorItem");
        ItemStack teamSelectorItem = new ItemBuilder(Material.RED_BED).setDisplayName(teamSelectorItemName).build();

        String kitSelectorItemName = messageConfig.getMessage("kitSelectorItem");
        ItemStack kitSelectorItem = new ItemBuilder(Material.CHEST).setDisplayName(kitSelectorItemName).build();

        player.getInventory().setItem(gameSettings.getKitSelectorItemSlot(), kitSelectorItem);
        player.getInventory().setItem(gameSettings.getTeamSelectorItemSlot(), teamSelectorItem);
        player.updateInventory();
    }
}
