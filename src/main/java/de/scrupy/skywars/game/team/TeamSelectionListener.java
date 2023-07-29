package de.scrupy.skywars.game.team;

import de.scrupy.skywars.MessageConfig;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

public class TeamSelectionListener implements Listener {
    private final MessageConfig messageConfig;
    private final TeamHandler teamHandler;
    private final TeamSelectorGui teamSelectorGui;

    public TeamSelectionListener(MessageConfig messageConfig, TeamHandler teamHandler, TeamSelectorGui teamSelectorGui) {
        this.messageConfig = messageConfig;
        this.teamHandler = teamHandler;
        this.teamSelectorGui = teamSelectorGui;
    }

    @EventHandler
    public void onClick(InventoryClickEvent inventoryClick) {
        Inventory topInventory = inventoryClick.getView().getTopInventory();
        if (inventoryClick.getView().getTitle().equals(messageConfig.getMessage("teamSelectorGuiName"))) {
            inventoryClick.setCancelled(true);
            if (!(inventoryClick.getWhoClicked() instanceof Player player)) {
                return;
            }
            if (inventoryClick.getClickedInventory() != null && !inventoryClick.getClickedInventory().equals(topInventory)) {
                return;
            }
            if (inventoryClick.getCurrentItem() == null && inventoryClick.getCurrentItem().getItemMeta() == null) {
                return;
            }
            ItemStack currentItem = inventoryClick.getCurrentItem();
            Material currentItemType = currentItem.getType();

            if (!currentItemType.name().endsWith("WOOL")) {
                return;
            }
            for (Team team : Team.values()) {
                if (team.getTeamSelectionItemMaterial() == currentItemType) {
                    teamHandler.addPlayerToTeam(player, team);
                    player.playSound(player, Sound.UI_BUTTON_CLICK, 3.0F, 5.0F);
                    teamSelectorGui.updateInventory();
                }
            }
        }
    }

    @EventHandler
    public void onQuit(PlayerQuitEvent quitEvent) {
        Player player = quitEvent.getPlayer();
        teamHandler.removePlayerFromTeam(player);
        teamSelectorGui.updateInventory();
    }
}
