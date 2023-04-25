package de.scrupy.skywars.game.kit;

import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

public abstract class Kit {

    private final String name;
    private final String displayName;

    public abstract ItemStack getKitIcon();
    public abstract void equip(Player player);

    public Kit(String name, String displayName) {
        this.name = name;
        this.displayName = displayName;
    }

    public String getName() {
        return name;
    }

    public String getDisplayName() {
        return displayName;
    }
}
