package com.example;


import rx.Observable;
import rx.Subscriber;
import rx.functions.Action1;

public class MyClass {

    public static void main(String[] args) {
        create();

    }

    public static void create() {
        Observable o = Observable.create(new Observable.OnSubscribe<Object>() {
            @Override
            public void call(Subscriber<? super Object> subscriber) {
                for (int i = 0; i < 5; i++) {
                    System.out.println("init data===>" + i);
                    subscriber.onNext(i);
                }
            }
        });
        o.subscribe(new Action1() {
            @Override
            public void call(Object o) {
                System.out.println("data is==>" + o);
            }
        });
    }
}
