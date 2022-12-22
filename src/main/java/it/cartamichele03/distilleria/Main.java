package it.cartamichele03.distilleria;

import io.github.rysefoxx.inventory.plugin.pagination.InventoryManager;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.HashMap;
import java.util.Map;

public class Main extends JavaPlugin {
    private static Main instance;
    private final InventoryManager inventoryManager = new InventoryManager(this);
    @Override
    public void onEnable() {
        instance = this;
        this.inventoryManager.invoke();
        this.getCommand("ds").setExecutor(new Command());
    }

    public static Main getInstance() {
        return instance;
    }

}