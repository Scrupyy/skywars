package de.scrupy.skywars.game.kit;

import de.scrupy.skywars.util.ItemBuilder;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

public class DefaultKit extends Kit {
    public DefaultKit(String name, String displayName) {
        super(name, displayName);
    }

    @Override
    public ItemStack getKitIcon() {
        return new ItemBuilder(Material.IRON_SWORD)
                .setDisplayName(getDisplayName())
                .setLore(" ", "§e1x Stone Sword", "§e1x Stone Pickaxe", "§e1x Stone Axe", "§e1x Stone Shovel")
                .build();
    }

    @Override
    public void equip(Player player) {
        player.getInventory().addItem(new ItemStack(Material.STONE_SWORD));
        player.getInventory().addItem(new ItemStack(Material.STONE_PICKAXE));
        player.getInventory().addItem(new ItemStack(Material.STONE_AXE));
        player.getInventory().addItem(new ItemStack(Material.STONE_SHOVEL));
    }
}
