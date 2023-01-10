package com.example.foodizclient.boundaries;

import java.io.Serializable;

public class Ingredient implements Serializable {
    private String name = "";
    private String unit = "";
    private float amount = 0;

    public String getName() {
        return name;
    }

    public Ingredient setName(String name) {
        this.name = name;
        return this;
    }

    public String getUnit() {
        return unit;
    }

    public Ingredient setUnit(String unit) {
        this.unit = unit;
        return this;
    }

    public float getAmount() {
        return amount;
    }

    public Ingredient setAmount(float amount) {
        this.amount = amount;
        return this;
    }

    @Override
    public String toString() {
        return String.format("-\t %.1f %s | %s",
                amount, unit, name);
    }
}
