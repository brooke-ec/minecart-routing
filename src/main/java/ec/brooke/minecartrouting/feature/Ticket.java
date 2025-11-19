package ec.brooke.minecartrouting.feature;

import ec.brooke.minecartrouting.Maps;
import ec.brooke.minecartrouting.MinecartRouting;
import ec.brooke.minecartrouting.store.DyeColorType;
import net.md_5.bungee.api.ChatColor;
import org.bukkit.*;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.ShapelessRecipe;
import org.bukkit.inventory.meta.MapMeta;
import org.bukkit.persistence.PersistentDataContainer;

import java.util.List;
import java.util.Map;

public class Ticket {
    private static final NamespacedKey TICKET_CONTAINER = new NamespacedKey(MinecartRouting.INSTANCE, "minecart_ticket");
    private static final String RECIPE_KEY_TEMPLATE = "%s_ticket";

    public static DyeColor getTicket(ItemStack item) {
        if (item.getType() != Material.FILLED_MAP) return null;

        MapMeta meta = (MapMeta) item.getItemMeta();
        if (meta == null) return null;
        PersistentDataContainer pdc = meta.getPersistentDataContainer();

        if (!pdc.has(TICKET_CONTAINER, DyeColorType.INSTANCE)) return null;
        else return pdc.get(TICKET_CONTAINER, DyeColorType.INSTANCE);
    }

    public static boolean isTicket(ItemStack item) {
        return getTicket(item) != null;
    }

    public static void setup() {
        for (Map.Entry<Material, DyeColor> entry : Maps.MATERIAL_TO_DYE.entrySet()) {
            String name = entry.getValue().name().toLowerCase();
            NamespacedKey key = new NamespacedKey(MinecartRouting.INSTANCE, RECIPE_KEY_TEMPLATE.formatted(name));

            Color color = entry.getValue().getColor();
            ItemStack item = new ItemStack(Material.FILLED_MAP);

            MapMeta meta = (MapMeta) item.getItemMeta();
            assert meta != null;

            meta.getPersistentDataContainer().set(TICKET_CONTAINER, DyeColorType.INSTANCE, entry.getValue());
            meta.setLore(List.of(Ticket.color(color) + capitalize(name) + " Ticket"));
            meta.setItemName("Minecart Ticket");
            meta.setColor(color);
            item.setItemMeta(meta);

            ShapelessRecipe recipe = new ShapelessRecipe(key, item);
            recipe.addIngredient(Material.PAPER);
            recipe.addIngredient(entry.getKey());
            Bukkit.getServer().addRecipe(recipe);
        }
    }

    private static String color(Color color) {
        return ChatColor.of(new java.awt.Color(color.getRed(), color.getGreen(), color.getBlue())).toString();
    }

    private static String capitalize(String snake) {
        if (snake == null || snake.isEmpty()) return snake;

        String[] words = snake.split("_");
        StringBuilder result = new StringBuilder();

        for (int i = 0; i < words.length; i++) {
            String word = words[i].toLowerCase();
            if (!word.isEmpty()) {
                result.append(Character.toUpperCase(word.charAt(0)));
                if (word.length() > 1) result.append(word.substring(1));
            }
            if (i < words.length - 1) result.append(" ");
        }

        return result.toString();
    }
}
