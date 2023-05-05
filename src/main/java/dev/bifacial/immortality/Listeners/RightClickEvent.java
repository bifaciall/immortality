package dev.bifacial.immortality.Listeners;

import dev.bifacial.immortality.Main;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.GameMode;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractEvent;

import java.io.IOException;
import java.util.Objects;

public class RightClickEvent implements Listener {
    private final Main plugin;

    public RightClickEvent(Main plugin) {
        this.plugin = plugin;
    }
    @EventHandler
    public void onRightClick(PlayerInteractEvent playerInteractEvent) throws IOException {

        playerInteractEvent.getPlayer().sendMessage(Objects.requireNonNull(playerInteractEvent.getItem()).getItemMeta().getDisplayName());
        assert playerInteractEvent.getItem() != null;
        Player p = Bukkit.getPlayer(playerInteractEvent.getItem().getItemMeta().getDisplayName());
        if (playerInteractEvent.getPlayer().getItemInHand().getType().equals(Material.PLAYER_HEAD) && !playerInteractEvent.getPlayer().getInventory().getItemInMainHand().getItemMeta().getDisplayName().equals("Player Head")) {
            playerInteractEvent.getPlayer().sendMessage(playerInteractEvent.getItem().displayName());
            assert p != null;
            if (p.isOnline()) {
                playerInteractEvent.getPlayer().getInventory().remove(playerInteractEvent.getItem());
                p.setGameMode(GameMode.SURVIVAL);
                plugin.removeDeadPlayer(p.getName());
                p.teleport(playerInteractEvent.getPlayer().getLocation());
                playerInteractEvent.getPlayer().sendMessage(ChatColor.GREEN + "Der Spieler " + p.getName() + " wurde wiederbelebt.");
            } else
                playerInteractEvent.getPlayer().sendMessage(ChatColor.RED + "Dieser Spieler ist momentan nicht Online.");

        }
    }
}
