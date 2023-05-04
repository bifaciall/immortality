package dev.bifacial.immortality;
import dev.bifacial.immortality.Commands.ImmortalityCommand;
import dev.bifacial.immortality.Listeners.DeadPlayerMoveEvent;
import dev.bifacial.immortality.Listeners.DeathEvent;
import dev.bifacial.immortality.Listeners.RightClickEvent;
import org.bukkit.Bukkit;
import org.bukkit.event.Listener;
import org.bukkit.plugin.java.JavaPlugin;



public final class Main extends JavaPlugin implements Listener{

    @Override
    public void onEnable() {
        Bukkit.getServer().getLogger().info("Started.");
        getCommand("toggle").setExecutor(new ImmortalityCommand());
        ImmortalityCommand command = new ImmortalityCommand();
        if (command.isActivated()) {
            getServer().getPluginManager().registerEvents(new DeathEvent(), this);
            getServer().getPluginManager().registerEvents(new RightClickEvent(), this);
            getServer().getPluginManager().registerEvents(new DeadPlayerMoveEvent(), this);
            //Bukkit.getServer().getPluginManager().registerEvents(new HeadPlaceEvent(), this);
        }
    }

    @Override
    public void onDisable() {
        getServer().getLogger().info("Disabled.");
    }


}
