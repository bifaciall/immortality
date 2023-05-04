package dev.bifacial.immortality.Listeners;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.GameMode;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractEvent;

public class RightClickEvent implements Listener {
    @EventHandler
    public void onRightClick(PlayerInteractEvent playerInteractEvent) {
        playerInteractEvent.getPlayer().sendMessage(playerInteractEvent.getItem().getItemMeta().getDisplayName());
        assert playerInteractEvent.getItem() != null;
        Player p = Bukkit.getPlayer(playerInteractEvent.getItem().getItemMeta().getDisplayName());
        if (playerInteractEvent.getPlayer().getItemInHand().getType().equals(Material.PLAYER_HEAD) && !playerInteractEvent.getPlayer().getInventory().getItemInMainHand().getItemMeta().getDisplayName().equals("Player Head")) {
            playerInteractEvent.getPlayer().sendMessage(playerInteractEvent.getItem().displayName());
            if (p.isOnline()) {
                playerInteractEvent.getPlayer().getInventory().remove(playerInteractEvent.getItem());
                playerInteractEvent.getPlayer().sendMessage(ChatColor.GREEN + "Der Spieler " + p.getName() + " wurde wiederbelebt. Der Name ist " + p.getName());
            } else
                playerInteractEvent.getPlayer().sendMessage(ChatColor.RED + "Dieser Spieler ist momentan nicht Online.");

            if (p.isOnline()) p.setGameMode(GameMode.SURVIVAL);
            p.teleport(playerInteractEvent.getPlayer().getLocation());
        }
    }
}
