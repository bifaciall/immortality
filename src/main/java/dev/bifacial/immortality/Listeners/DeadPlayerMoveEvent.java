package dev.bifacial.immortality.Listeners;

import dev.bifacial.immortality.Main;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerMoveEvent;

public class DeadPlayerMoveEvent implements Listener {
    private final Main plugin;

    public DeadPlayerMoveEvent(Main plugin) {
        this.plugin = plugin;
    }
    @EventHandler
    public void onMove(PlayerMoveEvent playerMoveEvent){
        if (plugin.isPlayerDead(playerMoveEvent.getPlayer().getName())){
            playerMoveEvent.setCancelled(true);
        }
    }
}
