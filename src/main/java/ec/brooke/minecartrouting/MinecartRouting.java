package ec.brooke.minecartrouting;

import ec.brooke.minecartrouting.feature.Assigner;
import ec.brooke.minecartrouting.feature.Ticket;
import ec.brooke.minecartrouting.store.ChunkBlockLibrary;
import ec.brooke.minecartrouting.store.DyeFilter;
import org.bukkit.NamespacedKey;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

public final class MinecartRouting extends JavaPlugin {
    public static ChunkBlockLibrary<DyeFilter> FILTERS;
    public static MinecartRouting INSTANCE;

    @Override
    public void onEnable() {
        INSTANCE = this;
        FILTERS = new ChunkBlockLibrary<>(
            new NamespacedKey(this, "minecart_filter"),
            DyeFilter.DyeFilterType.INSTANCE
        );

        PluginManager manager = getServer().getPluginManager();
        manager.registerEvents(new Assigner(), this);
        Ticket.setup();
    }
}
