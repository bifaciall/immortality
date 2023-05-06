package dev.bifacial.immortality;
import dev.bifacial.immortality.Commands.RemovePlayerCommand;
import dev.bifacial.immortality.Listeners.DeadPlayerMoveEvent;
import dev.bifacial.immortality.Listeners.DeathEvent;
import dev.bifacial.immortality.Listeners.HeadPlaceEvent;
import dev.bifacial.immortality.Listeners.RightClickEvent;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.plugin.java.JavaPlugin;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public final class Main extends JavaPlugin {
    File file = new File(getDataFolder(), "dead_players.yml");
    YamlConfiguration config = YamlConfiguration.loadConfiguration(file);
    List<String> deadPlayers = new ArrayList<>();

    @Override
    public void onEnable() {
        getLogger().info("Loaded Plugin :)");
        getServer().getPluginManager().registerEvents(new DeathEvent(this), this);
        Objects.requireNonNull(getCommand("revive")).setExecutor(new RemovePlayerCommand(this));
        getServer().getPluginManager().registerEvents(new DeadPlayerMoveEvent(this), this);
        getServer().getPluginManager().registerEvents(new RightClickEvent(this), this);
        getServer().getPluginManager().registerEvents(new HeadPlaceEvent(), this);

        if (!getDataFolder().exists()) {
            getDataFolder().mkdirs();
        }

        // Create the YAML file if it doesn't exist

        if (!file.exists()) {
            try {
                file.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }


        deadPlayers = loadDeadPlayers();
        try {
            saveDeadPlayers();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }


    @Override
    public void onDisable() {
        getLogger().info("Disabled Plugin.");
        try {
            config.save(file);
        } catch (IOException e) {
            getLogger().warning("Failed to save dead_players.yml: " + e.getMessage());
        }
    }
    public void saveDeadPlayers() throws IOException {
        // Add the list of dead players to the configuration file
        config.set("dead_players", deadPlayers);
        config.save(file);
    }

    public List<String> loadDeadPlayers() {
        // Load the list of dead players from the configuration file
        assert config != null;
        return config.getStringList("dead_players");
    }

    public void removeDeadPlayer(String deadPlayer) throws IOException {
        this.deadPlayers.remove(deadPlayer);
        saveDeadPlayers();
    }

    public void addDeadPlayer(String deadPlayer) throws IOException {
        this.deadPlayers.add(deadPlayer);
        saveDeadPlayers();
    }
    public boolean isPlayerDead(String name) {
        return deadPlayers.contains(name);
    }
}
