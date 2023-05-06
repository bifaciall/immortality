package dev.bifacial.immortality.Listeners;

import org.bukkit.Material;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockPlaceEvent;

public class HeadPlaceEvent implements Listener {
    /*private final Main plugin;

    public HeadPlaceEvent(Main plugin) {
        this.plugin = plugin;
    }*/
    @EventHandler
    public void onPlace(BlockPlaceEvent blockPlaceEvent) {

        //Player p = Bukkit.getPlayer(blockPlaceEvent.getItemInHand().getItemMeta().getDisplayName());
        if (blockPlaceEvent.getBlock().getType().equals(Material.PLAYER_HEAD)) {
            //assert p != null;
//            if (p.isOnline()) {
//                p.setGameMode(GameMode.SURVIVAL);
//                plugin.removeDeadPlayer(p.getName());
//                p.teleport(blockPlaceEvent.getBlock().getLocation());
//                blockPlaceEvent.getPlayer().getInventory().remove(blockPlaceEvent.getItemInHand());
//                blockPlaceEvent.getPlayer().sendMessage(ChatColor.GREEN + "Der Spieler " + p.getName() + " wurde wiederbelebt");
//            }else blockPlaceEvent.getPlayer().sendMessage("Dieser Spieler ist momentan nicht Online.");
            blockPlaceEvent.setCancelled(true);
        }



    }
}
