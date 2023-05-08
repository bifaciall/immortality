package dev.bifacial.immortality.Listeners;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryType;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.inventory.meta.SkullMeta;

import java.util.Objects;

public class AnvilRename implements Listener {
    @EventHandler
    public void onAnvilRename(InventoryClickEvent inventoryClickEvent) {
        if (inventoryClickEvent.getInventory().getType() == InventoryType.ANVIL) {
            if (inventoryClickEvent.getSlotType() == InventoryType.SlotType.RESULT) {
                if (Objects.requireNonNull(inventoryClickEvent.getCurrentItem()).getType().equals(Material.PLAYER_HEAD)) {
                    ItemStack skull = inventoryClickEvent.getCurrentItem();
                    SkullMeta skullMeta = (SkullMeta) inventoryClickEvent.getCurrentItem().getItemMeta();
                    skullMeta.setOwningPlayer(Bukkit.getOfflinePlayer(inventoryClickEvent.getCurrentItem().getItemMeta().getDisplayName()));
                    skull.setItemMeta(skullMeta);
                }
            }
        }

    }
}
