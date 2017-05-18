package com.example.model;

import com.example.feature.IPerson;

/**
 * Created by catherine on 17/5/18.
 */

public class DaLong implements IPerson {
    private IPerson iPerson;

    public DaLong(IPerson iPerson) {
        this.iPerson = iPerson;
    }

    @Override
    public void signing(int price) {
        System.out.println("对方报价：" + price);
        if (price < 100) {
            this.iPerson.signing(price);

        } else {
            negotiate(price);

        }
    }

    public void negotiate(int price) {
        System.out.println(" 不接受，要求降价" + (price - 80));
    }
}
