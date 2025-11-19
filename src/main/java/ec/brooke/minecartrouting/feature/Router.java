package ec.brooke.minecartrouting.feature;

import ec.brooke.minecartrouting.MinecartRouting;
import ec.brooke.minecartrouting.Utils;
import ec.brooke.minecartrouting.store.DyeFilter;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.block.Block;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Minecart;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockRedstoneEvent;
import org.bukkit.inventory.InventoryHolder;

public class Router implements Listener {

    @EventHandler(priority = EventPriority.HIGHEST)
    private void onBlockRedstone(BlockRedstoneEvent event) {
        Block block = event.getBlock();

        if (block.getType() != Material.DETECTOR_RAIL || event.getNewCurrent() == 0) return;

        World world = block.getWorld();
        DyeFilter filter = MinecartRouting.FILTERS.get(block);

        boolean passed = filter == null || Utils.getNearbyEntities(Minecart.class, block.getLocation(), 0.2)
            .stream().anyMatch(minecart -> test(minecart, filter) || minecart.getPassengers()
                .stream().anyMatch(passenger -> test(passenger, filter)));

        event.setNewCurrent(passed ? 15 : 0);
    }

    private boolean test(Entity entity, DyeFilter filter) {
        if (!(entity instanceof InventoryHolder holder)) return false;
        return holder.getInventory().all(Material.FILLED_MAP).values().stream().anyMatch(
            item -> Ticket.isTicket(item) && filter.test(Ticket.getTicket(item))
        );
    }
}
