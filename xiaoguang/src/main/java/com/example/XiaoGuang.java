package com.example;

import com.example.model.HotDryNoodlesWithBuilder;

/**
 * 这是一个关于小光由热干面引起的一系列故事
 * builder模式
 */
public class XiaoGuang {

    public static void main(String[] args) {

        HotDryNoodlesWithBuilder noodleA = new HotDryNoodlesWithBuilder.Builder()
                .withParsley()
                .withSauerkraut()
                .build();
        System.out.println("Custom A wants:" + noodleA);
    }
}
