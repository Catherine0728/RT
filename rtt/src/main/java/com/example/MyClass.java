package com.example;


public class MyClass {
    public static void main(String[] arg) {
        System.out.println("main");
    }


//    public static void skip() {
//        Observable<Long> observable = Observable.interval(4, 5, TimeUnit.MINUTES)
//                .skip(4);
//
//        observable.subscribe(new Consumer<Long>() {
//            @Override
//            public void accept(Long aLong) throws Exception {
//                System.out.println(aLong);
//            }
//        });
//    }
}
