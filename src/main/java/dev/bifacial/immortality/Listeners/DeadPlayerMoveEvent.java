package dev.bifacial.immortality.Listeners;

import dev.bifacial.immortality.Main;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerMoveEvent;

public class DeadPlayerMoveEvent implements Listener {
    @EventHandler
    public void onMove(PlayerMoveEvent playerMoveEvent){
        Main DeadPlayerManager = new Main();
        if (DeadPlayerManager.isPlayerDead(playerMoveEvent.getPlayer().getName())){
            playerMoveEvent.setCancelled(true);
        }
    }
}
