package it.cartamichele03.distilleria;

import io.github.rysefoxx.inventory.plugin.content.IntelligentItem;
import io.github.rysefoxx.inventory.plugin.content.InventoryContents;
import io.github.rysefoxx.inventory.plugin.content.InventoryProvider;
import io.github.rysefoxx.inventory.plugin.pagination.RyseInventory;
import org.bukkit.Material;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Item;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.jetbrains.annotations.NotNull;

public class Command implements CommandExecutor {


    @Override
    public boolean onCommand(@NotNull CommandSender commandSender, org.bukkit.command.@NotNull Command command, @NotNull String s, @NotNull String[] strings) {
        if(!(commandSender instanceof Player)) {
            return true;
        }

        Player pl = (Player) commandSender;

        if(!pl.hasPermission("distilleria.command")) {
            pl.sendMessage("§cNon hai il permesso!");
            return true;
        }

        RyseInventory inv = RyseInventory.builder()
                .title("This is a basic inventory")
                .rows(3)
                .disableUpdateTask()
                .provider(new InventoryProvider() {
                    @Override
                    public void init(Player player, InventoryContents contents) {
                        contents.set(0, IntelligentItem.of(new ItemBuilder(Material.STONE).displayname("Visualizza il numero di bottiglie").build(), event -> {
                            int bottiglie = 0;
                            for(ItemStack i : pl.getInventory().getContents()) {
                                if(i != null && i.getType().equals(Material.POTION)) {
                                    bottiglie++;
                                }
                            }
                            pl.sendMessage("hai " + bottiglie + " bottiglie.");
                        }));
                        contents.set(1, IntelligentItem.ignored(new ItemBuilder(Material.STONE).displayname("I am a stone that can be taken out of the inventory.").build()));
                        contents.set(2, IntelligentItem.of(new ItemBuilder(Material.STONE).displayname("I am a stone where something happens when you click on me.").build(), event -> {
                            event.getWhoClicked().sendMessage("You clicked on a stone!");
                            event.getWhoClicked().sendMessage("Goobye!");
                        }));
                    }
                })
                .build(Main.getInstance());

        inv.open(pl);

        return true;
    }
}
