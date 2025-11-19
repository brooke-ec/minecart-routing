package ec.brooke.minecartrouting;

import org.bukkit.DyeColor;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;

import java.util.Map;

public class Maps {

    public static final Map<Material, DyeColor> MATERIAL_TO_DYE = Map.ofEntries(
            Map.entry(Material.WHITE_DYE, DyeColor.WHITE),
            Map.entry(Material.LIGHT_GRAY_DYE, DyeColor.LIGHT_GRAY),
            Map.entry(Material.GRAY_DYE, DyeColor.GRAY),
            Map.entry(Material.BLACK_DYE, DyeColor.BLACK),
            Map.entry(Material.BROWN_DYE, DyeColor.BROWN),
            Map.entry(Material.RED_DYE, DyeColor.RED),
            Map.entry(Material.ORANGE_DYE, DyeColor.ORANGE),
            Map.entry(Material.YELLOW_DYE, DyeColor.YELLOW),
            Map.entry(Material.LIME_DYE, DyeColor.LIME),
            Map.entry(Material.GREEN_DYE, DyeColor.GREEN),
            Map.entry(Material.CYAN_DYE, DyeColor.CYAN),
            Map.entry(Material.LIGHT_BLUE_DYE, DyeColor.LIGHT_BLUE),
            Map.entry(Material.BLUE_DYE, DyeColor.BLUE),
            Map.entry(Material.PURPLE_DYE, DyeColor.PURPLE),
            Map.entry(Material.MAGENTA_DYE, DyeColor.MAGENTA),
            Map.entry(Material.PINK_DYE, DyeColor.PINK)
    );

    public static final Map<DyeColor, ItemStack> DYE_TO_CONCRETE = Map.ofEntries(
            Map.entry(DyeColor.WHITE, new ItemStack(Material.WHITE_CONCRETE)),
            Map.entry(DyeColor.LIGHT_GRAY, new ItemStack(Material.LIGHT_GRAY_CONCRETE)),
            Map.entry(DyeColor.GRAY, new ItemStack(Material.GRAY_CONCRETE)),
            Map.entry(DyeColor.BLACK, new ItemStack(Material.BLACK_CONCRETE)),
            Map.entry(DyeColor.BROWN, new ItemStack(Material.BROWN_CONCRETE)),
            Map.entry(DyeColor.RED, new ItemStack(Material.RED_CONCRETE)),
            Map.entry(DyeColor.ORANGE, new ItemStack(Material.ORANGE_CONCRETE)),
            Map.entry(DyeColor.YELLOW, new ItemStack(Material.YELLOW_CONCRETE)),
            Map.entry(DyeColor.LIME, new ItemStack(Material.LIME_CONCRETE)),
            Map.entry(DyeColor.GREEN, new ItemStack(Material.GREEN_CONCRETE)),
            Map.entry(DyeColor.CYAN, new ItemStack(Material.CYAN_CONCRETE)),
            Map.entry(DyeColor.LIGHT_BLUE, new ItemStack(Material.LIGHT_BLUE_CONCRETE)),
            Map.entry(DyeColor.BLUE, new ItemStack(Material.BLUE_CONCRETE)),
            Map.entry(DyeColor.PURPLE, new ItemStack(Material.PURPLE_CONCRETE)),
            Map.entry(DyeColor.MAGENTA, new ItemStack(Material.MAGENTA_CONCRETE)),
            Map.entry(DyeColor.PINK, new ItemStack(Material.PINK_CONCRETE))
    );

    public static final Map<DyeColor, ItemStack> DYE_TO_STAINED_GLASS = Map.ofEntries(
            Map.entry(DyeColor.WHITE, new ItemStack(Material.WHITE_STAINED_GLASS)),
            Map.entry(DyeColor.LIGHT_GRAY, new ItemStack(Material.LIGHT_GRAY_STAINED_GLASS)),
            Map.entry(DyeColor.GRAY, new ItemStack(Material.GRAY_STAINED_GLASS)),
            Map.entry(DyeColor.BLACK, new ItemStack(Material.BLACK_STAINED_GLASS)),
            Map.entry(DyeColor.BROWN, new ItemStack(Material.BROWN_STAINED_GLASS)),
            Map.entry(DyeColor.RED, new ItemStack(Material.RED_STAINED_GLASS)),
            Map.entry(DyeColor.ORANGE, new ItemStack(Material.ORANGE_STAINED_GLASS)),
            Map.entry(DyeColor.YELLOW, new ItemStack(Material.YELLOW_STAINED_GLASS)),
            Map.entry(DyeColor.LIME, new ItemStack(Material.LIME_STAINED_GLASS)),
            Map.entry(DyeColor.GREEN, new ItemStack(Material.GREEN_STAINED_GLASS)),
            Map.entry(DyeColor.CYAN, new ItemStack(Material.CYAN_STAINED_GLASS)),
            Map.entry(DyeColor.LIGHT_BLUE, new ItemStack(Material.LIGHT_BLUE_STAINED_GLASS)),
            Map.entry(DyeColor.BLUE, new ItemStack(Material.BLUE_STAINED_GLASS)),
            Map.entry(DyeColor.PURPLE, new ItemStack(Material.PURPLE_STAINED_GLASS)),
            Map.entry(DyeColor.MAGENTA, new ItemStack(Material.MAGENTA_STAINED_GLASS)),
            Map.entry(DyeColor.PINK, new ItemStack(Material.PINK_STAINED_GLASS))
    );
}
