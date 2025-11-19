package ec.brooke.minecartrouting.feature;

import ec.brooke.minecartrouting.Conversions;
import ec.brooke.minecartrouting.MinecartRouting;
import ec.brooke.minecartrouting.store.DyeFilter;
import org.bukkit.*;
import org.bukkit.block.Block;
import org.bukkit.block.data.Rail;
import org.bukkit.entity.Entity;
import org.bukkit.entity.ItemDisplay;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.block.BlockPhysicsEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.EquipmentSlot;
import org.bukkit.inventory.ItemStack;

import java.util.function.Consumer;

public class Assigner implements Listener {
    private static final String DISPLAY_TAG = "minecart_filter";

    @EventHandler
    private void onPlayerInteract(PlayerInteractEvent event) {
        Block block = event.getClickedBlock();
        Player player = event.getPlayer();
        World world = player.getWorld();

        if (
            block == null
            || player.isSneaking()
            || event.getHand() != EquipmentSlot.HAND
            || block.getType() != Material.DETECTOR_RAIL
        ) return;

        DyeFilter current = MinecartRouting.FILTERS.get(block);
        Location location = block.getLocation();
        ItemStack item = event.getItem();

        if (event.getAction() == Action.RIGHT_CLICK_BLOCK) {
            if (item == null) {
                if (current != null) {
                    world.playSound(location, Sound.BLOCK_LEVER_CLICK, 1, 1.5f);
                    update(block, current.invert());
                    event.setCancelled(true);
                }
            } else if (Conversions.MATERIAL_TO_DYE.containsKey(item.getType())) {
                DyeColor dye = Conversions.MATERIAL_TO_DYE.get(item.getType());
                if (current != null && current.color == dye) return;

                world.playSound(location, Sound.ENTITY_ITEM_FRAME_ADD_ITEM, 1, 1);
                update(block, new DyeFilter(dye, true));
                event.setCancelled(true);
            }
        } else if (event.getAction() == Action.LEFT_CLICK_BLOCK && current != null) {
            world.playSound(location, Sound.ENTITY_ITEM_FRAME_REMOVE_ITEM, 1, 1);
            update(block, null);
            event.setCancelled(true);
        }
    }

    @EventHandler
    public void onBlockPhysics(BlockPhysicsEvent event) {
        Block block = event.getBlock();
        DyeFilter filter = MinecartRouting.FILTERS.get(block);
        if (filter != null) update(block, block.getType() == Material.DETECTOR_RAIL ? filter : null);
    }

    private void update(Block block, DyeFilter filter) {
        if (filter == null) MinecartRouting.FILTERS.remove(block);
        else MinecartRouting.FILTERS.put(block, filter);

        Location location = block.getLocation();
        World world = block.getWorld();

        ItemDisplay existing = null;
        for (Entity entity : world.getNearbyEntities(location, 1, 1, 1))
            if (entity instanceof ItemDisplay display && display.getScoreboardTags().contains(DISPLAY_TAG)) {
                existing = display;
                break;
            }

        if (filter != null) {
            Consumer<ItemDisplay> setup = display -> {
                display.addScoreboardTag(DISPLAY_TAG);
                display.setItemStack(
                    filter.whitelist
                    ? Conversions.DYE_TO_CONCRETE.get(filter.color)
                    : Conversions.DYE_TO_STAINED_GLASS.get(filter.color)
                );

                Rail.Shape shape = ((Rail) block.getBlockData()).getShape();
                display.setTransformation(Conversions.shapeTransformation(shape));
            };

            if (existing == null) world.spawn(location, ItemDisplay.class, setup);
            else setup.accept(existing);
        } else if (existing != null) {
            existing.remove();
        }
    }
}
