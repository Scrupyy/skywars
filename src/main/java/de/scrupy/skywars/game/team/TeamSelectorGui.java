package de.scrupy.skywars.game.team;

import de.scrupy.skywars.GameSettings;
import de.scrupy.skywars.MessageConfig;
import de.scrupy.skywars.util.ItemBuilder;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class TeamSelectorGui {
    private final MessageConfig messageConfig;
    private final GameSettings gameSettings;
    private final TeamHandler teamHandler;
    private final Inventory inventory;

    private static final int INVENTORY_SIZE = 27;

    public TeamSelectorGui(MessageConfig messageConfig, GameSettings gameSettings, TeamHandler teamHandler) {
        this.messageConfig = messageConfig;
        this.gameSettings = gameSettings;
        this.teamHandler = teamHandler;
        this.inventory = Bukkit.createInventory(null, INVENTORY_SIZE, messageConfig.getMessage("teamSelectorGuiName"));
        fillInventory();
        addTeamSelectionItems();
    }

    public void open(Player player) {
        player.openInventory(this.inventory);
    }

    public void updateInventory() {
        int inventoryItemSlot = 9;
        for (Team team : teamHandler.getAvailableTeams()) {
            ItemStack itemStack = this.inventory.getItem(inventoryItemSlot);
            if (itemStack != null) {
                updateSelectionItem(itemStack, team);
            }
            inventoryItemSlot++;
        }
    }

    private void updateSelectionItem(ItemStack itemStack, Team team) {
        ItemMeta itemMeta = itemStack.getItemMeta();
        Optional.ofNullable(itemMeta).ifPresent(meta -> {
            meta.setLore(generateItemDescription(team));
            meta.setDisplayName(getSelectionItemName(team));
            itemStack.setItemMeta(meta);
        });
    }

    private void addTeamSelectionItems() {
        int inventoryItemSlot = 9;
        for (Team team : teamHandler.getAvailableTeams()) {
            this.inventory.setItem(inventoryItemSlot++, getTeamSelectionItem(team));
        }
    }

    private ItemStack getTeamSelectionItem(Team team) {
        String selectionItemName = getSelectionItemName(team);
        return new ItemBuilder(team.getTeamSelectionItemMaterial()).setDisplayName(selectionItemName).setLore(generateItemDescription(team)).build();
    }

    private String getSelectionItemName(Team team) {
        String currentPlayerAmount = String.valueOf(team.getPlayers().size());
        String maxPlayerAmount = String.valueOf(gameSettings.getTeamSize());
        return messageConfig.getMessage("teamSelectionItem", team.getColouredTeamName(), currentPlayerAmount, maxPlayerAmount);
    }

    private List<String> generateItemDescription(Team team) {
        List<String> itemDescriptionLines = new ArrayList<>();
        for (int i = 0; i < gameSettings.getTeamSize(); i++) {
            String playerName = (i < team.getPlayers().size() ? team.getPlayers().get(i).getName() : "");
            String descriptionTemplate = messageConfig.getMessage("teamSelectionItemDescription", playerName);
            itemDescriptionLines.add(descriptionTemplate);
        }
        return itemDescriptionLines;
    }

    private void fillInventory() {
        ItemStack itemStack = new ItemBuilder(Material.WHITE_STAINED_GLASS_PANE).setDisplayName(" ").build();
        for (int i = 0; i < this.inventory.getSize(); i++) {
            this.inventory.setItem(i, itemStack);
        }
    }
}
