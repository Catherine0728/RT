package com.example.model;

import com.example.feature.IActivityStrategy;

/**
 * Created by catherine on 17/5/17.
 */

public class ThanksGivingDayStrategy implements IActivityStrategy {
    @Override
    public String getActivityStrategyPrice() {
        return "(感恩节)所有饮品一律5折";
    }
}
