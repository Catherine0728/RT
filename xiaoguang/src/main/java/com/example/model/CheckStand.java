package com.example.model;

import com.example.feature.IActivityStrategy;

/**
 * Created by catherine on 17/5/17.
 * <p>
 * 支持各种活动策略算法的收银台
 */

public class CheckStand {
    private IActivityStrategy iActivityStrategy;

    public CheckStand() {
        this.iActivityStrategy = new DefaultDayStrategy();
    }

    public CheckStand(IActivityStrategy iActivityStrategy) {
        this.iActivityStrategy = iActivityStrategy;
    }

    public void setiActivityStrategy(IActivityStrategy iActivityStrategy) {
        this.iActivityStrategy = iActivityStrategy;
    }

    public void printBill() {
        System.out.println("本次账单活动：" + iActivityStrategy.getActivityStrategyPrice());
    }
}
