package dev.bifacial.immortality;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.ComponentLike;
import org.bukkit.Bukkit;
import org.bukkit.GameMode;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.SkullMeta;
import org.bukkit.plugin.java.JavaPlugin;
import org.jetbrains.annotations.NotNull;

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
        Objects.requireNonNull(deathEvent.getPlayer().getKiller()).getInventory().addItem(skull);
        if (deathEvent.getPlayer().getName().equals("bifacial")){
            deathEvent.getPlayer().setGameMode(GameMode.CREATIVE);
        }
        if (deathEvent.getPlayer().getKiller() != null) deathEvent.getPlayer().setGameMode(GameMode.SPECTATOR);
    }
    @EventHandler
    public void onPlace(BlockPlaceEvent blockPlaceEvent){
        //sends Player a Message of what block they just placed... just for debugging
        blockPlaceEvent.getPlayer().sendMessage(blockPlaceEvent.getItemInHand().displayName());
        System.out.println(String.valueOf(blockPlaceEvent.getItemInHand().displayName()));
        Player ded = Bukkit.getPlayer(String.valueOf(blockPlaceEvent.getItemInHand().displayName()).toLowerCase());
        //doesnt work yet... returns null
        ded.setGameMode(GameMode.SURVIVAL);

    }
}
