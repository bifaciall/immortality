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

import java.util.Objects;

public class RightClickEvent implements Listener {
    private final Main plugin;

    public RightClickEvent(Main plugin) {
        this.plugin = plugin;
    }

    @EventHandler
    public void onRightClick(PlayerInteractEvent playerInteractEvent) {
        assert playerInteractEvent.getItem() != null;
        if (playerInteractEvent.getPlayer().getInventory().getItemInMainHand().getType().equals(Material.PLAYER_HEAD)) {
            {
                try {
                    Player p = Bukkit.getPlayer(Objects.requireNonNull(playerInteractEvent.getItem().getItemMeta().getDisplayName()));
                    assert p != null;
                    if (plugin.isPlayerDead(playerInteractEvent.getItem().getItemMeta().getDisplayName())) {
                        playerInteractEvent.getPlayer().getInventory().remove(playerInteractEvent.getItem());
                        p.setGameMode(GameMode.SURVIVAL);
                        plugin.removeDeadPlayer(playerInteractEvent.getItem().getItemMeta().getDisplayName());
                        p.teleport(playerInteractEvent.getPlayer().getLocation());
                        playerInteractEvent.getPlayer().sendMessage(ChatColor.GREEN + "Der Spieler " + p.getName() + " wurde wiederbelebt.");
                        return;
                    }
                    playerInteractEvent.getPlayer().sendMessage(ChatColor.RED + "Dieser Spieler ist momentan nicht Online oder Lebendig.");
                } catch (Exception ignored) {
                }
            }
        }
    }
}
