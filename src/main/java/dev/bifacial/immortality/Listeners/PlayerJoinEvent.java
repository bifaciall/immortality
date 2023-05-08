package dev.bifacial.immortality.Listeners;

import dev.bifacial.immortality.Main;
import org.bukkit.GameMode;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;

public class PlayerJoinEvent implements Listener {
    public Main plugin;

    public PlayerJoinEvent(Main plugin){
        this.plugin = plugin;
    }
    public void onJoin(org.bukkit.event.player.PlayerJoinEvent joinEvent){
        Player p = joinEvent.getPlayer();
        if (plugin.isPlayerDead(p.getName())){
            p.setGameMode(GameMode.SPECTATOR);
        }else {
            p.setGameMode(GameMode.SURVIVAL);
        }
    }
}
