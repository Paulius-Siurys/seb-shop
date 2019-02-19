package org.uptown.shop.util.info;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public abstract class Info<T extends Option> {
    private final int id;
    private final String name;
    private List<T> options;
    private static Set<Integer> usedIds = new HashSet<>();

    public Info(int id, String name, List<T> options) {
        if (usedIds.contains(id)) {
            throw new RuntimeException(String.format("Id (%d) is already in use", id));
        } else {
            usedIds.add(id);
        }
        this.id = id;
        this.name = name;
        if (options != null) {
            options.forEach(o -> o.setInfo(this));
        }
        this.options = options;
    }
    public List<T> getOptions() {
        return options;
    }

    public void setOptions(List<T> options) {
        this.options = options;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }
}
