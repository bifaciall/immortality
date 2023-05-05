package dev.bifacial.immortality.Commands;

import dev.bifacial.immortality.Main;
import org.bukkit.Bukkit;
import org.bukkit.GameMode;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

import java.io.IOException;
import java.util.Objects;

public class RemovePlayerCommand implements CommandExecutor {
    private final Main plugin;

    public RemovePlayerCommand(Main plugin) {
        this.plugin = plugin;
    }
    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {
        Player p = (Player) sender;
        if (args.length > 0) {
            if (p.hasPermission("immortality.revive")) {
                try {
                    plugin.removeDeadPlayer(args[0]);
                    Objects.requireNonNull(Bukkit.getPlayer(args[0])).setGameMode(GameMode.SURVIVAL);
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
                return true;
            }
        }
        return false;
    }
}
