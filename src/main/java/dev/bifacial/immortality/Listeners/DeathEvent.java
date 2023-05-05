package dev.bifacial.immortality.Listeners;

import dev.bifacial.immortality.Main;
import net.kyori.adventure.text.Component;
import org.bukkit.GameMode;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.SkullMeta;

import java.io.IOException;
import java.util.Objects;

public class DeathEvent implements Listener {
    private final Main plugin;

    public DeathEvent(Main plugin) {
        this.plugin = plugin;
    }
    @EventHandler
    public void onPlayerDeath(PlayerDeathEvent deathEvent) throws IOException {
        Player player = deathEvent.getPlayer();
        Player killer = player.getKiller();
        ItemStack skull = new ItemStack(Material.PLAYER_HEAD);
        SkullMeta skullMeta = (SkullMeta) skull.getItemMeta();
        skullMeta.displayName(deathEvent.getPlayer().name());
        skullMeta.setOwningPlayer(deathEvent.getPlayer());
        skullMeta.setUnbreakable(true);
        //skullMeta.lore((List<Component>) Component.text("Right Click with the Head to revive the Player. You can change what Player should be revived by renaming the Head in an Anvil."));
        skull.setItemMeta(skullMeta);
        assert killer != null;
        killer.getWorld().dropItem(deathEvent.getPlayer().getLocation(), skull);
        killer.sendMessage(Component.text("You killed "+player.getName()));
        if (player.getKiller() != null){
            Objects.requireNonNull(player.getPlayer()).setGameMode(GameMode.SPECTATOR);
            plugin.addDeadPlayer(player.getName());
        }
    }


}
