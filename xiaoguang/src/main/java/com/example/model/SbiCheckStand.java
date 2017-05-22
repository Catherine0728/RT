package com.example.model;

import com.example.feature.ICheckStand;

/**
 * Created by catherine on 17/5/18.
 */

public class SbiCheckStand implements ICheckStand {
    @Override
    public String getAccount() {
        return "招商银行:620123131231233";
    }
}
