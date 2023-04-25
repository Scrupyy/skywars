package de.scrupy.skywars.commands;

import de.scrupy.skywars.game.kit.KitGui;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class TestCommand implements CommandExecutor {
    private final KitGui kitGui;

    public TestCommand(KitGui kitGui) {
        this.kitGui = kitGui;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        kitGui.open((Player) sender);
        return true;
    }
}
