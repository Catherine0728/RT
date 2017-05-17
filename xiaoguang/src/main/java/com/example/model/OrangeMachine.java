package com.example.model;

import com.example.feature.IMachine;

/**
 * Created by catherine on 17/5/17.
 */

public class OrangeMachine implements IMachine {

    @Override
    public Drink makeDrink() {
        return new OrangeJuice().make();
    }
}
