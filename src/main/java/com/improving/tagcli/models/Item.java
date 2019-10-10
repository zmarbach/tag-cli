package com.improving.tagcli.models;

public class Item {
    private String name;
    private int weight;
    private int value;
    private boolean burnable;
    private String magicQuality;

    public Item(String name, int weight, int value, boolean burnable, String magicQuality) {
        this.name = name;
        this.weight = weight;
        this.value = value;
        this.burnable = burnable;
        this.magicQuality = magicQuality;
    }

    public String getName() {
        return name;
    }

    public int getWeight() {
        return weight;
    }

    public int getValue() {
        return value;
    }

    public boolean isBurnable() {
        return burnable;
    }

    public String getMagicQuality() {
        return magicQuality;
    }
}
