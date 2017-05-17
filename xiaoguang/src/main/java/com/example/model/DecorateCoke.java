package com.example.model;

import com.example.feature.IDrink;

/**
 * Created by catherine on 17/5/17.
 */

public class DecorateCoke implements IDrink {
    @Override
    public String make() {
        return "这是一杯可乐";
    }
}
