package dev.bifacial.immortality.Commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.jetbrains.annotations.NotNull;

public class ImmortalityCommand implements CommandExecutor {
private boolean activated;
    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {
        activated = !activated;
        sender.sendMessage("Immortality Status is now " + activated);
        return true;
    }

    public boolean isActivated() {
        return activated;
    }
}
