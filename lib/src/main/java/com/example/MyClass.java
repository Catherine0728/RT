package com.example;


public class MyClass {
    public static void main(String args[]) {
        System.out.println("hello=");
        merge();
    }


    private static void merge() {
        Observable.merge(
                getDataFromLocal(),
                getDataFromNet())
                .subscribe(new Action1() {
                    @Override
                    public void call(Object o) {
                        System.out.println(o);
                    }
                });
    }

    private static Observable getDataFromLocal() {
        return Observable.create(new Observable.OnSubscribe() {
            @Override
            public void call(Object o) {
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println(System.currentTimeMillis());
            }
        });
    }

    private static Observable getDataFromNet() {
        return Observable.create(new Observable.OnSubscribe() {
            @Override
            public void call(Object o) {
                try {
                    Thread.sleep(5000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println(System.currentTimeMillis());
            }
        });
    }
}
