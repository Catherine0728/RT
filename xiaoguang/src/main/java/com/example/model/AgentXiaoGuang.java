package com.example.model;

import com.example.feature.IPerson;

/**
 * Created by catherine on 17/5/18.
 */

public class AgentXiaoGuang implements IPerson {
    @Override
    public void signing(int price) {
        System.out.println("小光以" + price + "每箱的价格签单.");
    }
}
