package dev.bifacial.immortality.Listeners;

import org.bukkit.GameMode;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerMoveEvent;

public class DeadPlayerMoveEvent implements Listener {
    @EventHandler
    public void onMove(PlayerMoveEvent playerMoveEvent){
        DeathEvent d = new DeathEvent();
        if (d.isDead()){
            playerMoveEvent.setCancelled(true);
        }
    }
}
