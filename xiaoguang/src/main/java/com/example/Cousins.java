package com.example;

import com.example.feature.IMachine;
import com.example.model.CokeMachine;
import com.example.model.Drink;
import com.example.model.OrangeMachine;

/**
 * Created by catherine on 17/5/17.
 * 简单工厂 －－》 工厂方法
 * <p>
 * 工厂方法如同Builder模式，是一些开源库中非常常见的用来创建实例的设计模式
 * <p>
 * <p>
 * 1, 为何叫工厂方法, 是因为每个工厂有一个方法来创建产品.
 * 2, 每个产品对应一个工厂实例来生产这个产品实例.
 * 3, 因为产品和其对应的工厂都与其他产品分离, 我们可以很轻易的去增加新的产品和其对应的工厂, 而不改变原来的结构. (开闭原则, 实际上还蕴含了职责单一)
 */

public class Cousins {

    IMachine iMachine;

    public void setiMachine(IMachine iMachine) {
        this.iMachine = iMachine;
    }

    private Drink takeDrink() {
        if (iMachine == null) throw new NullPointerException("Should set machine firstly");
        return iMachine.makeDrink();
    }

    public static void main(String[] main) {
        Cousins cousins = new Cousins();
        cousins.setiMachine(new CokeMachine());
        System.out.println(cousins.takeDrink());

        //for b
        cousins.setiMachine(new OrangeMachine());
        System.out.println(cousins.takeDrink());
    }
}
