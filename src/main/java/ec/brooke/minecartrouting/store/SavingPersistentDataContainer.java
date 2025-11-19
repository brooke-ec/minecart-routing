package ec.brooke.minecartrouting.store;

import org.bukkit.NamespacedKey;
import org.bukkit.persistence.PersistentDataAdapterContext;
import org.bukkit.persistence.PersistentDataContainer;
import org.bukkit.persistence.PersistentDataType;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.Set;

public class SavingPersistentDataContainer implements PersistentDataContainer {
    private final PersistentDataContainer delegate;
    private final PersistentDataContainer parent;
    private final NamespacedKey key;

    public static SavingPersistentDataContainer from(PersistentDataContainer parent, NamespacedKey key) {
        if (parent.has(key, PersistentDataType.TAG_CONTAINER))
            return new SavingPersistentDataContainer(parent, parent.get(key, PersistentDataType.TAG_CONTAINER), key);
        else return new SavingPersistentDataContainer(parent, parent.getAdapterContext().newPersistentDataContainer(), key);
    }

    public SavingPersistentDataContainer(PersistentDataContainer parent, PersistentDataContainer delegate, NamespacedKey key) {
        this.delegate = delegate;
        this.parent = parent;
        this.key = key;
    }

    private void save() {
        parent.set(key, PersistentDataType.TAG_CONTAINER, delegate);
    }

    @Override
    public <P, C> void set(@NotNull NamespacedKey key, @NotNull PersistentDataType<P, C> type, @NotNull C value) {
        delegate.set(key, type, value);
        save();
    }

    @Override
    public <P, C> boolean has(@NotNull NamespacedKey key, @NotNull PersistentDataType<P, C> type) {
        return delegate.has(key, type);
    }

    @Override
    public boolean has(@NotNull NamespacedKey key) {
        return delegate.has(key);
    }

    @Nullable
    @Override
    public <P, C> C get(@NotNull NamespacedKey key, @NotNull PersistentDataType<P, C> type) {
        return delegate.get(key, type);
    }

    @NotNull
    @Override
    public <P, C> C getOrDefault(@NotNull NamespacedKey key, @NotNull PersistentDataType<P, C> type, @NotNull C defaultValue) {
        return delegate.getOrDefault(key, type, defaultValue);
    }

    @NotNull
    @Override
    public Set<NamespacedKey> getKeys() {
        return delegate.getKeys();
    }

    @Override
    public void remove(@NotNull NamespacedKey key) {
        delegate.remove(key);
        save();
    }

    @Override
    public boolean isEmpty() {
        return delegate.isEmpty();
    }

    @Override
    public void copyTo(@NotNull PersistentDataContainer other, boolean replace) {
        delegate.copyTo(other, replace);
    }

    @NotNull
    @Override
    public PersistentDataAdapterContext getAdapterContext() {
        return delegate.getAdapterContext();
    }
}
