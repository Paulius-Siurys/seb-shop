package org.uptown.shop.util.info;

public class NumericOption extends Option implements Comparable<NumericOption> {

    private Integer value;

    public NumericOption(int id, String name, Integer value) {
        super(id, name);
        this.value = value;
    }

    @Override
    public int compareTo(NumericOption o) {
        return value.compareTo(o.value);
    }

    public Integer getValue() {
        return value;
    }

    public void setValue(Integer value) {
        this.value = value;
    }
}