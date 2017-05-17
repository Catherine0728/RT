package com.example.model;

import com.example.feature.IDrink;

/**
 * Created by catherine on 17/5/17.
 */

public class Ice extends Stuff {
    @Override
    String stuffName() {
        return "冰";
    }

    public Ice(IDrink iDrink) {
        super(iDrink);
    }
}
