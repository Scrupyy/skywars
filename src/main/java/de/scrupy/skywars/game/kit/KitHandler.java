package de.scrupy.skywars.game.kit;

import de.scrupy.skywars.MessageConfig;
import de.scrupy.skywars.game.scoreboard.PlayerScoreboard;
import org.bukkit.entity.Player;

import java.util.*;

public class KitHandler {
    private final MessageConfig messageConfig;
    private final List<Kit> kits;
    private final Map<Player, Kit> playerKits;
    private final Kit defaultKit;

    public KitHandler(MessageConfig messageConfig) {
        this.messageConfig = messageConfig;
        this.kits = new ArrayList<>();
        this.playerKits = new HashMap<>();
        this.defaultKit = new DefaultKit("Default", "§7Default");
        registerKits();
    }

    private void registerKits() {
        kits.add(defaultKit);
    }

    public void selectKit(Player player, Kit kit) {
        Kit selectedKit = getSelectedKit(player);
        if (selectedKit.equals(kit)) {
            player.sendMessage(messageConfig.getMessage("alreadySelectedKit"));
            return;
        }
        playerKits.put(player, kit);
        PlayerScoreboard.updateKit(player, kit);
        player.sendMessage(messageConfig.getMessage("selectedKit", kit.getDisplayName()));
    }

    public Kit getSelectedKit(Player player) {
       return playerKits.getOrDefault(player, defaultKit);
    }

    public List<Kit> getKits() {
        return kits;
    }
}
