package de.scrupy.skywars.game.kit;

import de.scrupy.skywars.MessageConfig;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;

import java.util.Objects;

public class KitGuiEvent implements Listener {
    private final MessageConfig messageConfig;
    private final KitHandler kitHandler;

    public KitGuiEvent(MessageConfig messageConfig, KitHandler kitHandler) {
        this.messageConfig = messageConfig;
        this.kitHandler = kitHandler;
    }

    @EventHandler
    public void onClick(InventoryClickEvent inventoryClick) {
        Inventory topInventory = inventoryClick.getView().getTopInventory();
        if (inventoryClick.getView().getTitle().equals(messageConfig.getMessage("kitGuiName"))) {
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
            String displayName = Objects.requireNonNull(inventoryClick.getCurrentItem().getItemMeta()).getDisplayName();
            for (Kit kit : kitHandler.getKits()) {
                if (kit.getDisplayName().equals(displayName)) {
                    kitHandler.selectKit(player, kit);
                }
            }
        }
    }
}
