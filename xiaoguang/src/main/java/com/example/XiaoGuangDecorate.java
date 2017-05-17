package com.example;

import com.example.feature.IDrink;
import com.example.model.DecorateCoke;
import com.example.model.Ice;

/**
 * Created by catherine on 17/5/17.
 * <p>
 * 装饰者模式
 * 用来动态的给对象加上额外的职责
 */

public class XiaoGuangDecorate {

    public static void main(String[] args) {

        IDrink iDrink = new Ice(new DecorateCoke());
        System.out.println(iDrink.make());
    }
}
