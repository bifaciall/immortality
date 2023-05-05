package dev.bifacial.immortality.Listeners;
import dev.bifacial.immortality.Main;
import net.kyori.adventure.text.Component;
import org.bukkit.Bukkit;
import org.bukkit.GameMode;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.SkullMeta;
import java.util.List;
import java.util.Objects;

public class DeathEvent implements Listener {
    Main deadPlayerSaver = new Main();
    @EventHandler
    public void onPlayerDeath(PlayerDeathEvent deathEvent){
        Player player = Bukkit.getPlayerExact(deathEvent.getPlayer().getName());
        ItemStack skull = new ItemStack(Material.PLAYER_HEAD);
        SkullMeta skullMeta = (SkullMeta) skull.getItemMeta();
        skullMeta.displayName(deathEvent.getPlayer().name());
        skullMeta.setOwningPlayer(deathEvent.getPlayer());
        skullMeta.setUnbreakable(true);
        skullMeta.lore((List<Component>) Component.text("Right Click with the Head to revive the Player. You can change what Player should be revived by renaming the Head in an Anvil."));
        skull.setItemMeta(skullMeta);
        Objects.requireNonNull(deathEvent.getPlayer().getKiller()).getWorld().dropItem(deathEvent.getPlayer().getLocation(), skull);
        if (deathEvent.getPlayer().getKiller() != null){
            deathEvent.getPlayer().setGameMode(GameMode.SPECTATOR);
        }
        assert player != null;
        deadPlayerSaver.addDeadPlayer(player.getName());
    }


}
