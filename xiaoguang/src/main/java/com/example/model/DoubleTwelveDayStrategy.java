package com.example.model;

import com.example.feature.IActivityStrategy;

/**
 * Created by catherine on 17/5/17.
 */

public class DoubleTwelveDayStrategy implements IActivityStrategy {
    @Override
    public String getActivityStrategyPrice() {
        return "(双十二)满12立减2元";
    }
}
