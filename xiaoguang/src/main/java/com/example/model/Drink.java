package com.example.model;

/**
 * Created by catherine on 17/5/17.
 */

public abstract class Drink {
    private String name;

    public Drink make() {
        this.name = getName();
        return this;
    }

    abstract String getName();

    @Override
    public String toString() {
        return "This is a cup of:" + this.name;
    }
}



