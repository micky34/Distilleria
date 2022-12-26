package it.cartamichele03.distilleria;

import io.github.rysefoxx.inventory.plugin.content.IntelligentItem;
import io.github.rysefoxx.inventory.plugin.content.IntelligentItemData;
import io.github.rysefoxx.inventory.plugin.content.InventoryContents;
import io.github.rysefoxx.inventory.plugin.content.InventoryProvider;
import io.github.rysefoxx.inventory.plugin.pagination.RyseInventory;
import io.github.rysefoxx.inventory.plugin.pagination.SlotIterator;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Item;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

import java.util.Iterator;
import java.util.Objects;

public class Inventories {
    public static void open(Player pl) {
        RyseInventory inv = RyseInventory.builder()
                .title(Main.INV_NAME)
                .rows(3)
                .disableUpdateTask()
                .provider(new InventoryProvider() {
                    @Override
                    public void init(Player player, InventoryContents contents) {
                        contents.addIgnoredSlot(0);
                        contents.set(2, 8, IntelligentItem.of(new ItemBuilder(Material.BREWING_STAND, 1, "Distilla").build(), event -> {
                            event.getWhoClicked().sendMessage("Mi hai cliccato!");
                            if(event.getInventory().containsAtLeast(new ItemStack(Material.CACTUS), 5)) {
                                event.getInventory().removeItem(new ItemStack(Material.CACTUS,5));
                                event.getWhoClicked().getInventory().addItem(new ItemStack(Material.DIAMOND));
                            }
                        }));
                    }
                })
                .build(Main.getInstance());

        inv.open(pl);
    }
}
