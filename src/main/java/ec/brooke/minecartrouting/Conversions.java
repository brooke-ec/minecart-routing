package ec.brooke.minecartrouting;

import org.bukkit.DyeColor;
import org.bukkit.Material;
import org.bukkit.block.data.Rail;
import org.bukkit.inventory.ItemStack;
import org.bukkit.util.Transformation;
import org.joml.Quaternionf;
import org.joml.Vector3f;

import java.util.Map;

public class Conversions {

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

    private static final float PI2 = (float) Math.PI / 2;
    private static final float PI4 = (float) Math.PI / 4;
    private static final Map<Rail.Shape, Integer> SHAPES = Map.of(
            Rail.Shape.ASCENDING_NORTH, 0,
            Rail.Shape.ASCENDING_WEST, 1,
            Rail.Shape.ASCENDING_SOUTH, 2,
            Rail.Shape.ASCENDING_EAST, 3
    );

    public static Transformation shapeTransformation(Rail.Shape shape) {
        if (shape.name().contains("ASCENDING")) return new Transformation(
                new Vector3f(0.5f, 0.5625f, 0.5f),
                new Quaternionf().rotateY(PI2 * SHAPES.get(shape)).rotateX(PI4),
                new Vector3f(0.25f, 0.05f, 0.25f * 1.41f),
                new Quaternionf()
        );
        else return new Transformation(
                new Vector3f(0.5f, 0.075f, 0.5f),
                new Quaternionf(),
                new Vector3f(0.25f, 0.05f, 0.25f),
                new Quaternionf()
        );
    }
}
