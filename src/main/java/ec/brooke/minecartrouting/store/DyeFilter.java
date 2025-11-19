package ec.brooke.minecartrouting.store;

import org.bukkit.DyeColor;
import org.bukkit.persistence.PersistentDataAdapterContext;
import org.bukkit.persistence.PersistentDataType;
import org.jetbrains.annotations.NotNull;

public class DyeFilter {

    public final boolean whitelist;
    public final DyeColor color;

    public DyeFilter(DyeColor color, boolean whitelist) {
        this.whitelist = whitelist;
        this.color = color;
    }

    public DyeFilter invert() {
        return new DyeFilter(color, !whitelist);
    }

    public boolean test(DyeColor color) {
        return whitelist == (this.color == color);
    }

    public static class DyeFilterType implements PersistentDataType<String, DyeFilter> {

        public static final DyeFilterType INSTANCE = new DyeFilterType();

        @NotNull
        @Override
        public Class<String> getPrimitiveType() {
            return String.class;
        }

        @NotNull
        @Override
        public Class<DyeFilter> getComplexType() {
            return DyeFilter.class;
        }

        @NotNull
        @Override
        public String toPrimitive(@NotNull DyeFilter complex, @NotNull PersistentDataAdapterContext persistentDataAdapterContext) {
            return (complex.whitelist ? "" : "!") + DyeColorType.INSTANCE.toPrimitive(complex.color, persistentDataAdapterContext);
        }

        @NotNull
        @Override
        public DyeFilter fromPrimitive(@NotNull String primitive, @NotNull PersistentDataAdapterContext persistentDataAdapterContext) {
            boolean whitelist = primitive.charAt(0) != '!';
            primitive = primitive.substring(whitelist ? 0 : 1);
            DyeColor color = DyeColorType.INSTANCE.fromPrimitive(primitive, persistentDataAdapterContext);
            return new DyeFilter(color, whitelist);
        }
    }
}
