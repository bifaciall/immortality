package dev.bifacial.immortality;
import org.bukkit.Bukkit;
import org.bukkit.GameMode;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.SkullMeta;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.Collection;
import java.util.Objects;

public final class Main extends JavaPlugin implements Listener{

    @Override
    public void onEnable() {
        System.out.println("Started Immortality");
        Bukkit.getServer().getPluginManager().registerEvents(this, this);

    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }

    @EventHandler
    public void onPlayerDeath(PlayerDeathEvent deathEvent){
        ItemStack skull = new ItemStack(Material.PLAYER_HEAD);
        SkullMeta skullMeta = (SkullMeta) skull.getItemMeta();
        skullMeta.displayName(deathEvent.getPlayer().name());
        skullMeta.setOwningPlayer(deathEvent.getPlayer());
        skullMeta.setUnbreakable(true);
        skull.setItemMeta(skullMeta);
        Bukkit.getWorld(deathEvent.getPlayer().getName()).dropItem(deathEvent.getPlayer().getLocation(), skull);
        if (deathEvent.getPlayer().getKiller() != null) deathEvent.getPlayer().setGameMode(GameMode.SPECTATOR);
    }
    @EventHandler
    public void onPlace(BlockPlaceEvent blockPlaceEvent){
        //sends Player a Message of what block they just placed... just for debugging
        blockPlaceEvent.getPlayer().sendMessage(blockPlaceEvent.getItemInHand().displayName());
        if (blockPlaceEvent.getBlock().getType().equals(Material.PLAYER_HEAD) && !blockPlaceEvent.getItemInHand().getItemMeta().getDisplayName().equals("Player Head")) {
            Player p = Bukkit.getPlayer(blockPlaceEvent.getItemInHand().getItemMeta().getDisplayName());
            assert p != null;
            if (p.isOnline()) p.setGameMode(GameMode.SURVIVAL);
            p.teleport(blockPlaceEvent.getBlock().getLocation());
        }
        if(blockPlaceEvent.getBlock().getType().equals(Material.PLAYER_HEAD)){
            blockPlaceEvent.setCancelled(true);
            blockPlaceEvent.getPlayer().getInventory().remove(blockPlaceEvent.getItemInHand());
        }


    }
    @EventHandler
    public void onMove(PlayerMoveEvent playerMoveEvent){
        if (playerMoveEvent.getPlayer().getGameMode().equals(GameMode.SPECTATOR)){
            playerMoveEvent.setCancelled(true);
        }
    }
}
