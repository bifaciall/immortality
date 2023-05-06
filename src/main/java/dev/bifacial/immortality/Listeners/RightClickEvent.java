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

        //playerInteractEvent.getPlayer().sendMessage(Objects.requireNonNull(playerInteractEvent.getItem()).getItemMeta().getDisplayName());
        assert playerInteractEvent.getItem() != null;
        Player p = Bukkit.getPlayer(Objects.requireNonNull(playerInteractEvent.getItem().getItemMeta().getDisplayName()));
        assert p != null;
        if (playerInteractEvent.getPlayer().getInventory().getItemInMainHand().getType().equals(Material.PLAYER_HEAD) && !Objects.requireNonNull(playerInteractEvent.getPlayer().getInventory().getItemInMainHand().getItemMeta().displayName()).toString().equals("Player Head")) {
            playerInteractEvent.getPlayer().sendMessage(p.getName());
            try {
                if (p.isOnline() && plugin.isPlayerDead(p.getName())) {
                    playerInteractEvent.getPlayer().getInventory().remove(playerInteractEvent.getItem());
                    p.setGameMode(GameMode.SURVIVAL);
                    plugin.removeDeadPlayer(p.getName());
                    p.teleport(playerInteractEvent.getPlayer().getLocation());
                    playerInteractEvent.getPlayer().sendMessage(ChatColor.GREEN + "Der Spieler " + p.getName() + " wurde wiederbelebt.");
                } else
                    playerInteractEvent.getPlayer().sendMessage(ChatColor.RED + "Dieser Spieler ist momentan nicht Online oder Lebendig.");
            }catch (NullPointerException ignored){}
        }
    }
}
