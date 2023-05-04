package dev.bifacial.immortality.Listeners;

import net.md_5.bungee.api.ChatColor;
import org.bukkit.Bukkit;
import org.bukkit.GameMode;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockPlaceEvent;

public class HeadPlaceEvent implements Listener {
    @EventHandler
    public void onPlace(BlockPlaceEvent blockPlaceEvent) {
        DeathEvent d = new DeathEvent();
        Player p = Bukkit.getPlayer(blockPlaceEvent.getItemInHand().getItemMeta().getDisplayName());
        if (blockPlaceEvent.getBlock().getType().equals(Material.PLAYER_HEAD) && !blockPlaceEvent.getItemInHand().getItemMeta().getDisplayName().equals("Player Head")) {
            assert p != null;
            if (p.isOnline()) p.setGameMode(GameMode.SURVIVAL); d.setDead(false);
            p.teleport(blockPlaceEvent.getBlock().getLocation());
        }
        if (blockPlaceEvent.getBlock().getType().equals(Material.PLAYER_HEAD)) {
            blockPlaceEvent.setCancelled(true);
            if (p.isOnline()){ blockPlaceEvent.getPlayer().getInventory().remove(blockPlaceEvent.getItemInHand());
                blockPlaceEvent.getPlayer().sendMessage(ChatColor.GREEN + "Der Spieler " + p.getName() + " wurde wiederbelebt");}
            else blockPlaceEvent.getPlayer().sendMessage("Dieser Spieler ist momentan nicht Online.");
        }

    }
}
