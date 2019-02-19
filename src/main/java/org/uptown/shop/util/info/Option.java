package org.uptown.shop.util.info;

import com.fasterxml.jackson.annotation.JsonIgnore;

import java.util.HashSet;
import java.util.Set;

public abstract class Option {
    private final int id;
    private final String name;
    private Info info;
    private static Set<Integer> usedIds = new HashSet<>();

    public Option(int id, String name) {
        if (usedIds.contains(id)) {
            throw new RuntimeException(String.format("Id (%d) is already in use", id));
        } else {
            usedIds.add(id);
        }
        this.id = id;
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    @JsonIgnore
    public Info getInfo() {
        return info;
    }

    public void setInfo(Info info) {
        this.info = info;
    }
}
