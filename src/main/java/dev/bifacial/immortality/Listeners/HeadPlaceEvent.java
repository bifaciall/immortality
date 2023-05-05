package dev.bifacial.immortality.Listeners;

import dev.bifacial.immortality.Main;
import net.md_5.bungee.api.ChatColor;
import org.bukkit.Bukkit;
import org.bukkit.GameMode;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockPlaceEvent;

import java.io.IOException;

public class HeadPlaceEvent implements Listener {
    private final Main plugin;

    public HeadPlaceEvent(Main plugin) {
        this.plugin = plugin;
    }
    @EventHandler
    public void onPlace(BlockPlaceEvent blockPlaceEvent) throws IOException {

        Player p = Bukkit.getPlayer(blockPlaceEvent.getItemInHand().getItemMeta().getDisplayName());
        if (blockPlaceEvent.getBlock().getType().equals(Material.PLAYER_HEAD)) {
            assert p != null;
            if (p.isOnline()) {
                p.setGameMode(GameMode.SURVIVAL);
                plugin.removeDeadPlayer(p.getName());
                p.teleport(blockPlaceEvent.getBlock().getLocation());
                blockPlaceEvent.getPlayer().getInventory().remove(blockPlaceEvent.getItemInHand());
                blockPlaceEvent.getPlayer().sendMessage(ChatColor.GREEN + "Der Spieler " + p.getName() + " wurde wiederbelebt");
            }else blockPlaceEvent.getPlayer().sendMessage("Dieser Spieler ist momentan nicht Online.");
            blockPlaceEvent.setCancelled(true);
        }



    }
}
