package dev.bifacial.immortality;

import dev.bifacial.immortality.Commands.ImmortalityCommand;
import dev.bifacial.immortality.Listeners.DeadPlayerMoveEvent;
import dev.bifacial.immortality.Listeners.DeathEvent;
import dev.bifacial.immortality.Listeners.RightClickEvent;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.File;
import java.io.IOException;
import java.util.List;


public final class Main extends JavaPlugin{
    private File file;
    private YamlConfiguration config;
    List<String> deadPlayers = loadDeadPlayers();

    @Override
    public void onEnable() {
        getLogger().info("Started.");
        getCommand("toggle").setExecutor(new ImmortalityCommand());
        ImmortalityCommand command = new ImmortalityCommand();
        if (command.isActivated()) {
            getServer().getPluginManager().registerEvents(new DeathEvent(), this);
            getServer().getPluginManager().registerEvents(new RightClickEvent(), this);
            getServer().getPluginManager().registerEvents(new DeadPlayerMoveEvent(), this);
            //Bukkit.getServer().getPluginManager().registerEvents(new HeadPlaceEvent(), this);
        }
        file = new File(getDataFolder(), "dead_players.yml");
        if (!file.exists()) {
            saveResource("dead_players.yml", false);
        }
        config = YamlConfiguration.loadConfiguration(file);

    }

    @Override
    public void onDisable() {
        getLogger().info("Disabled.");
        try {
            config.save(file);
        } catch (IOException e) {
            getLogger().warning("Failed to save dead_players.yml: " + e.getMessage());
        }
    }
    public void saveDeadPlayers() {
        // Add the list of dead players to the configuration file
        config.set("dead_players", deadPlayers);
    }

    public List<String> loadDeadPlayers() {
        // Load the list of dead players from the configuration file
        assert config != null;
        return config.getStringList("dead_players");
    }

    public void removeDeadPlayer(String deadPlayer){
        this.deadPlayers.remove(deadPlayer);
        saveDeadPlayers();
    }

    public void addDeadPlayer(String deadPlayer) {
        this.deadPlayers.add(deadPlayer);
        saveDeadPlayers();
    }
    public boolean isPlayerDead(String Playername) {
        return deadPlayers.contains(Playername);
    }
}
