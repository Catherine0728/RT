package com.example;

import com.example.model.CheckStand;
import com.example.model.DoubleTwelveDayStrategy;
import com.example.model.ThanksGivingDayStrategy;

/**
 * Created by catherine on 17/5/17.
 *
 * 策略模式
 * 定义一组算法，并将每一个单独算法封装起来，让它们可以相互替换
 *
 *1， 简单工厂和工厂方法是创建型的模式，而策略模式是行为型的模式
 *2， 所谓创建型就是用来生产对象的，注重生产（new）这个部分，用创建型的模式来代替直接new一个实例，更多是想将直接的实例
 * 依赖通过不同的方法转发接口依赖
 * 3，所谓行为型模式更多的是描述一种行为，A使用B，怎么使用的这个关系上
 * 实际上，在上一个"工厂方法"的故事中，我们已经使用到了策略模式
 *
 */

public class XiaoGuangActivity {

    public static void main(String [] args){
        //收银台默认
        CheckStand checkStand=new CheckStand();
        checkStand.printBill();
        //感恩节
        checkStand.setiActivityStrategy(new ThanksGivingDayStrategy());
        checkStand.printBill();
        //双十二
        checkStand.setiActivityStrategy(new DoubleTwelveDayStrategy());
        checkStand.printBill();
    }
}
