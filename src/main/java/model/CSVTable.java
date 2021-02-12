package model;

import java.util.Map;
import java.util.Objects;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

public class CSVTable {
    public static final String SEMICOLON_SEPARATOR_CHARACTER = ";";

    private final Map<String, Set<String>> map = new ConcurrentHashMap<>();
    private final String separationCharacter;

    public CSVTable() {
        this(SEMICOLON_SEPARATOR_CHARACTER);
    }

    public CSVTable(String separationCharacter) {
        this.separationCharacter = separationCharacter;
    }

    public Set<String> getColumnSetByKey(String key) {
        return this.map.get(key);
    }

    public Set<String> getKeySet() {
        return this.map.keySet();
    }

    public void put(String key, Set<String> value) {
        this.map.put(key, value);
    }

    public void putValueInColumn(String key, String value) {
        this.map.get(key).add(value);
    }

    public String getSeparationCharacter() {
        return this.separationCharacter;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CSVTable table = (CSVTable) o;
        return Objects.equals(map, table.map);
    }

    @Override
    public int hashCode() {
        return Objects.hash(map);
    }

    @Override
    public String toString() {
        return "model.CSVTable{" +
                "map=" + map +
                '}';
    }
}
