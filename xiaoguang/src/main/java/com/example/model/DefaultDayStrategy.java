package com.example.model;

import com.example.feature.IActivityStrategy;

/**
 * Created by catherine on 17/5/17.
 */

public class DefaultDayStrategy implements IActivityStrategy {
    @Override
    public String getActivityStrategyPrice() {
        return "没有活动";
    }
}
