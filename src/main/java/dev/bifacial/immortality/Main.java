package dev.bifacial.immortality;
import dev.bifacial.immortality.Listeners.DeadPlayerMoveEvent;
import dev.bifacial.immortality.Listeners.DeathEvent;
import dev.bifacial.immortality.Listeners.HeadPlaceEvent;
import dev.bifacial.immortality.Listeners.RightClickEvent;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.GameMode;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.SkullMeta;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.Collection;
import java.util.Objects;

public final class Main extends JavaPlugin implements Listener{

    @Override
    public void onEnable() {
        getServer().getLogger().info("Started.");
        Bukkit.getServer().getPluginManager().registerEvents(new DeathEvent(), this);
        Bukkit.getServer().getPluginManager().registerEvents(new RightClickEvent(), this);
        Bukkit.getServer().getPluginManager().registerEvents(new DeadPlayerMoveEvent(), this);
        //Bukkit.getServer().getPluginManager().registerEvents(new HeadPlaceEvent(), this);

    }

    @Override
    public void onDisable() {
        getServer().getLogger().info("Disabled.");
    }


}
