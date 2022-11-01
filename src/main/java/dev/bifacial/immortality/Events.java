package dev.bifacial.immortality;

import org.bukkit.Material;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.SkullMeta;
import org.bukkit.plugin.java.JavaPlugin;
import java.util.ArrayList;


public class Events extends JavaPlugin implements Listener {
    
    @EventHandler
    public void onPlayerDeath(PlayerDeathEvent deathEvent){
        ItemStack skull = new ItemStack(Material.PLAYER_HEAD);
        SkullMeta skullMeta = (SkullMeta) skull.getItemMeta();
        skullMeta.displayName(deathEvent.getPlayer().displayName());
        ArrayList<String> lore = new ArrayList<String>();
        lore.add("Du besitzt nun den Kopf von " + deathEvent.getPlayer().getName() + ". Du kannst ihn benutzen um diesen oder einen anderen Spieler wiederzubeleben");
        skull.setLore(lore);
        skullMeta.setOwner(deathEvent.getPlayer().getName());
        skullMeta.setUnbreakable(true);
        skull.setItemMeta(skullMeta);
        deathEvent.getPlayer().getKiller().getInventory().addItem(skull);

    }


}

