package de.scrupy.skywars.game.kit;

import de.scrupy.skywars.MessageConfig;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;

public class KitGui {
    private final MessageConfig messageConfig;
    private final KitHandler kitHandler;
    private final Inventory inventory;

    public KitGui(MessageConfig messageConfig, KitHandler kitHandler) {
        this.messageConfig = messageConfig;
        this.kitHandler = kitHandler;
        this.inventory = Bukkit.createInventory(null, 9, messageConfig.getMessage("kitGuiName"));
        addKitsToInventory();
    }

    public void open(Player player) {
        player.openInventory(inventory);
    }

    private void addKitsToInventory() {
        for (Kit kit : kitHandler.getKits()) {
            inventory.addItem(kit.getKitIcon());
        }
    }
}
