package com.example.model;

import com.example.feature.IMachine;

/**
 * Created by catherine on 17/5/17.
 */

public class CokeMachine implements IMachine {

    @Override
    public Drink makeDrink() {
        return new Coke().make();
    }
}
