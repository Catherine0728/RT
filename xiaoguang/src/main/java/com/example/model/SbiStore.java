package com.example.model;

import com.example.feature.IStore;

/**
 * Created by catherine on 17/5/18.
 */

public class SbiStore implements IStore {
    @Override
    public String getAddress() {
        return "北京市海淀区";
    }

    @Override
    public String getName() {
        return "红牛店";
    }
}
