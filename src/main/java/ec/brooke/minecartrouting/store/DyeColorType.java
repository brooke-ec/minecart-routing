package ec.brooke.minecartrouting.store;

import org.bukkit.DyeColor;
import org.bukkit.persistence.PersistentDataAdapterContext;
import org.bukkit.persistence.PersistentDataType;
import org.jetbrains.annotations.NotNull;

public class DyeColorType implements PersistentDataType<String, DyeColor> {

    public static final DyeColorType INSTANCE = new DyeColorType();

    @NotNull
    @Override
    public Class<String> getPrimitiveType() {
        return String.class;
    }

    @NotNull
    @Override
    public Class<DyeColor> getComplexType() {
        return DyeColor.class;
    }

    @NotNull
    @Override
    public String toPrimitive(@NotNull DyeColor complex, @NotNull PersistentDataAdapterContext persistentDataAdapterContext) {
        return complex.name();
    }

    @NotNull
    @Override
    public DyeColor fromPrimitive(@NotNull String primitive, @NotNull PersistentDataAdapterContext persistentDataAdapterContext) {
        try { return DyeColor.valueOf(primitive);}
        catch (IllegalArgumentException e) { return DyeColor.BLACK; }
    }
}
