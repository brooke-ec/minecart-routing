package ec.brooke.minecartrouting.store;

import ec.brooke.minecartrouting.MinecartRouting;
import org.bukkit.Chunk;
import org.bukkit.Location;
import org.bukkit.NamespacedKey;
import org.bukkit.block.Block;
import org.bukkit.persistence.PersistentDataContainer;
import org.bukkit.persistence.PersistentDataType;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.Collection;
import java.util.Map;
import java.util.Set;

public class ChunkBlockLibrary<T> implements Map<Block, T> {

    private final PersistentDataType<?, T> type;
    private final NamespacedKey key;

    public ChunkBlockLibrary(NamespacedKey key, PersistentDataType<?, T> type) {
        this.type = type;
        this.key = key;
    }

    @Override
    public int size() {
        throw new UnsupportedOperationException();
    }

    @Override
    public boolean isEmpty() {
        throw new UnsupportedOperationException();
    }

    @Override
    public boolean containsKey(Object key) {
        if (!(key instanceof Block block)) return false;
        PersistentDataContainer pdc = getContainer(block);
        return pdc.has(toKey(block.getLocation()), type);
    }

    @Override
    public boolean containsValue(Object value) {
        throw new UnsupportedOperationException();
    }

    @Override
    @Nullable
    public T get(Object key) {
        if (!(key instanceof Block block)) return null;
        PersistentDataContainer pdc = getContainer(block);
        return pdc.get(toKey(block.getLocation()), type);
    }

    @Nullable
    @Override
    public T put(Block key, T value) {
        PersistentDataContainer pdc = getContainer(key);
        T old = pdc.get(toKey(key.getLocation()), type);
        pdc.set(toKey(key.getLocation()), type, value);
        return old;
    }

    @Override
    public T remove(Object key) {
        if (!(key instanceof Block block)) return null;
        PersistentDataContainer pdc = getContainer(block);
        T old = pdc.get(toKey(block.getLocation()), type);
        pdc.remove(toKey(block.getLocation()));
        return old;
    }

    @Override
    public void putAll(@NotNull Map<? extends Block, ? extends T> m) {
        for (Entry<? extends Block, ? extends T> entry : m.entrySet()) {
            put(entry.getKey(), entry.getValue());
        }
    }

    @Override
    public void clear() {
        throw new UnsupportedOperationException();
    }

    @NotNull
    @Override
    public Set<Block> keySet() {
        throw new UnsupportedOperationException();
    }

    @NotNull
    @Override
    public Collection<T> values() {
        throw new UnsupportedOperationException();
    }

    @NotNull
    @Override
    public Set<Entry<Block, T>> entrySet() {
        throw new UnsupportedOperationException();
    }

    private PersistentDataContainer getContainer(Block block) {
        PersistentDataContainer pdc = block.getChunk().getPersistentDataContainer();
        return SavingPersistentDataContainer.from(pdc, key);
    }

    private static NamespacedKey toKey(Location location) {
        Chunk chunk = location.getChunk();
        return new NamespacedKey(MinecartRouting.INSTANCE, "%s_%s_%s".formatted(
                location.getBlockX() - chunk.getX(),
                location.getBlockY(),
                location.getBlockZ() - chunk.getZ()
        ));
    }
}
