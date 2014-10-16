package pl.semantyk.domain;

import java.io.Serializable;

/**
 * Przechowuje pare 2 obiektow.
 *
 * @param <K>
 * @param <V>
 */
public class Pair<K, V> implements Serializable, Cloneable {

    private static final long serialVersionUID = 5219840050925743225L;

    private K key;

    private V value;

    public Pair(K key, V value) {
        this.key = key;
        this.value = value;
    }

    public final K getKey() {
        return key;
    }

    public final void setKey(K key) {
        this.key = key;
    }

    public final V getValue() {
        return value;
    }

    public final void setValue(V value) {
        this.value = value;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        Pair<?, ?> pair = (Pair<?, ?>) o;

        if (key != null ? !key.equals(pair.key) : pair.key != null) {
            return false;
        }
        return !(value != null ? !value.equals(pair.value) : pair.value != null);

    }

    @Override
    public int hashCode() {
        int result = key != null ? key.hashCode() : 0;
        result = 31 * result + (value != null ? value.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "Pair{" + "key=" + key + ", value=" + value + '}';
    }
}
