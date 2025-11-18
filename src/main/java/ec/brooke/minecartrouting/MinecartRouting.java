package ec.brooke.minecartrouting;

import ec.brooke.minecartrouting.feat.Ticket;
import org.bukkit.plugin.java.JavaPlugin;

public final class MinecartRouting extends JavaPlugin {

    public static MinecartRouting INSTANCE;

    @Override
    public void onEnable() {
        INSTANCE = this;

        Ticket.setup();
    }
}
