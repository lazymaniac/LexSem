package pl.semantyk.utils;

import java.util.*;

/**
 * Map implemented in  <value, vlaue> manner.
 * @param <K> Key type.
 * @param <V> Value type.
 * @author Sebastian Fabisz
 */
public class WNMap<K, V> implements Map<K, V> {

    /**
     * Lista kluczy.
     */
    private final LinkedHashSet<K> keys;
    /**
     * Lista wartości.
     */
    private final LinkedHashSet<V> values;

    /**
     * Domyślny konstruktor.
     */
    public WNMap() {
        keys = new LinkedHashSet<>();
        values = new LinkedHashSet<>();
    }

    @Override
    public final int size() {
        return keys.size();
    }

    @Override
    public final boolean isEmpty() {
        return keys.isEmpty();
    }

    @Override
    public final boolean containsKey(final Object key) {
        return keys.contains(key);
    }

    @Override
    public final boolean containsValue(final Object value) {
        return values.contains(value);
    }

    @Override
    public final V get(final Object key) {
        if (!keys.contains(key)) {
            return null;
        }

        Iterator<? extends K> keyIterator = keys.iterator();
        Iterator<? extends V> valueIterator = values.iterator();

        while (keyIterator.hasNext()) {
            if (key.equals(keyIterator.next())) {
                break;
            }
            valueIterator.next();
        }

        return valueIterator.next();
    }

    /**
     * Zwraca wartość na podstawie klucza.
     *
     * @param key klucz dla którego poszukujemy wartość
     * @return poszukiwana wartość.
     */
    public final V getVal(final Object key) {
        return get(key);
    }

    /**
     * Zwraca klucz na podstawie wartości.
     *
     * @param value wartość dla której poszukujemy klucz
     * @return wartość klucza.
     */
    public final K getKey(final Object value) {
        if (!values.contains(value)) {
            return null;
        }

        Iterator<? extends K> keyIterator = keys.iterator();

        for (V value1 : values) {
            if (value.equals(value1)) {
                break;
            }
            keyIterator.next();
        }

        return keyIterator.next();
    }

    @Override
    public final V put(final K key, final V value) {
        if (keys.contains(key)) {
            return getVal(key);
        }

        if (values.contains(value)) {
            return value;
        }

        keys.add(key);
        values.add(value);
        return value;
    }

    /**
     * Zwraca index klucza.
     *
     * @param key klucz dla którego index wyszukujemy.
     * @return indeks klucza.
     */
    public final int getKeyIndex(final Object key) {
        if (!keys.contains(key)) {
            return -1;
        }

        Iterator<? extends K> keyIterator = keys.iterator();
        int index = 0;

        while (keyIterator.hasNext()) {
            index++;
            if (key.equals(keyIterator.next())) {
                break;
            }
        }

        return index;
    }

    /**
     * Zwraca index wartości.
     *
     * @param value wartosc dla której index wyszukujemy.
     * @return indeks klucza.
     */
    public final int getValIndex(final Object value) {
        if (!values.contains(value)) {
            return -1;
        }

        Iterator<? extends V> valueIterator = values.iterator();
        int index = 0;

        while (valueIterator.hasNext()) {
            index++;
            if (value.equals(valueIterator.next())) {
                break;
            }
        }

        return index;
    }

    @Override
    public final V remove(final Object key) {
        if (getKeyIndex(key) == -1) {
            return null;
        }

        V value = getVal(key);
        keys.remove(key);
        values.remove(value);
        return value;
    }

    /**
     * Usuwa rekord na podstawie klucza.
     *
     * @param key klucz rekordu.
     * @return usunięta wartość.
     */
    public final V removeVal(final Object key) {
        return remove(key);
    }

    /**
     * Usuwa rekord na podstawie wartości.
     *
     * @param value Usuwana wartość.
     * @return Usunięty klucz.
     */
    public final K removeKey(final Object value) {
        if (getValIndex(value) == -1) {
            return null;
        }

        K key = getKey(value);
        keys.remove(key);
        values.remove(value);
        return key;
    }

    @Override
    public final void putAll(final Map<? extends K, ? extends V> m) {

        for (K key : m.keySet()) {
            V value = m.get(key);
            put(key, value);
        }
    }

    /**
     * Czyści mape i dodaje do mapy wartości i klucze podane w argumentach.
     *
     * @param wartosci Set z wartościami.
     * @param klucze   tablica z kluczami.
     * @throws Exception Jeżeli rozmiar kluczy nie zgadza się z romiarem Setu
     */
    public final void putSet(final ArrayList<? extends K> klucze, final Set<? extends V> wartosci) throws Exception {
        if (klucze.size() != wartosci.size()) {
            throw new Exception("Rozmiar tablicy kluczy " + "nie pasuje do rozmiaru tablicy wartości");
        }
        clear();

        Iterator<? extends V> valueIterator = wartosci.iterator();

        int index = 0;
        while (valueIterator.hasNext()) {
            V value = valueIterator.next();
            K key = klucze.get(index);
            put(key, value);
            index++;
        }
    }

    @Override
    public final void clear() {
        keys.clear();
        values.clear();
    }

    @Override
    public final Set<K> keySet() {
        return new LinkedHashSet<>(keys);
    }

    @Override
    public final Collection<V> values() {
        return new ArrayList<>(values);
    }

    @SuppressWarnings({"rawtypes", "unchecked"})
    @Override
    public final Set<java.util.Map.Entry<K, V>> entrySet() {

        if (keys.size() != values.size()) {
            throw new IllegalStateException("Liczba kluczy nie " + "zgadza się z liczbą wartości.");
        }

        Set set = new TreeSet<Map.Entry<K, V>>();
        Iterator<? extends K> keyIterator = keys.iterator();
        Iterator<? extends V> valueIterator = values.iterator();
        while (keyIterator.hasNext()) {
            set.add((new AbstractMap.SimpleEntry<>(keyIterator.next(), valueIterator.next())));
        }

        return set;
    }

}
