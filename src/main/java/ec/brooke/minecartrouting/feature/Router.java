package ec.brooke.minecartrouting.feature;

import ec.brooke.minecartrouting.MinecartRouting;
import ec.brooke.minecartrouting.Utils;
import ec.brooke.minecartrouting.store.DyeFilter;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Minecart;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockRedstoneEvent;
import org.bukkit.inventory.InventoryHolder;

import java.util.Collection;

public class Router implements Listener {

    @EventHandler(priority = EventPriority.HIGHEST)
    private void onBlockRedstone(BlockRedstoneEvent event) {
        Block block = event.getBlock();
        if (block.getType() != Material.DETECTOR_RAIL || event.getNewCurrent() == 0) return;
        DyeFilter filter = MinecartRouting.FILTERS.get(block);
        if (filter == null) return;

        Location location = block.getLocation().add(0.5, 0.5, 0.5);
        boolean passes = test(Utils.getNearbyEntities(Minecart.class, location, 0.2), filter);
        event.setNewCurrent(passes ? 15 : 0);
    }

    private boolean test(Collection<? extends Entity> entities, DyeFilter filter) {
        return entities.stream().anyMatch(entity ->
            entity instanceof InventoryHolder holder
            && holder.getInventory().all(Material.FILLED_MAP).values().stream().anyMatch(
                item -> Ticket.isTicket(item) && filter.test(Ticket.getTicket(item)))
            || test(entity.getPassengers(), filter)
        );
    }
}
