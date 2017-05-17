package com.example.model;

import com.example.feature.IDrink;

/**
 * Created by catherine on 17/5/17.
 */

public abstract class Stuff implements IDrink {
    private IDrink iDrink;

    public Stuff(IDrink iDrink) {
        this.iDrink = iDrink;
    }

    @Override
    public String make() {
        return iDrink.make() + ",加一份" + stuffName();
    }

    abstract String stuffName();
}
