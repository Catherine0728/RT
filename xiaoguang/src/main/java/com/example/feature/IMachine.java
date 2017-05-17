package com.example.feature;

import com.example.model.Drink;

/**
 * Created by catherine on 17/5/17.
 * <p>
 * 接口和抽象类的区别
 * <p>
 * 接口代表的是这个对象能做什么，对动作的抽象
 * 抽象代表的是这个对象属于什么，对根源的抽象
 * <p>
 * <p>
 * 第一点． 接口是抽象类的变体，接口中所有的方法都是抽象的。而抽象类是声明方法的存在而不去实现它的类。
 * 第二点． 接口可以多继承，抽象类不行
 * 第三点． 接口定义方法，不能实现，而抽象类可以实现部分方法。
 * 第四点． 接口中基本数据类型为static 而抽类象不是的。
 * 当你关注一个事物的本质的时候，用抽象类；当你关注一个操作的时候，用接口。
 */

public interface IMachine {
    Drink makeDrink();

}
