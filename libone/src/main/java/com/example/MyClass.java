package com.example;


import rx.Observable;
import rx.Scheduler;
import rx.Subscriber;
import rx.functions.Action1;
import rx.schedulers.Schedulers;

public class MyClass {

    public static void main(String[] args) {
        create();
        first();

    }

    public static void create() {
        Observable o = Observable.create(new Observable.OnSubscribe<Object>() {
            @Override
            public void call(Subscriber<? super Object> subscriber) {
                subscriber.onCompleted();
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

    public static void first() {
        Observable.concat(getDataFromNet(),
                getDataFromLocal())
                .first()
                .subscribe(new Subscriber() {
                    @Override
                    public void onCompleted() {
                        System.out.println("onCompleted");
                    }

                    @Override
                    public void onError(Throwable e) {
                        System.out.println("onError===>" + e);
                    }

                    @Override
                    public void onNext(Object o) {
                        System.out.println("onNext===>" + o);
                    }
                });

    }

    public static Observable getDataFromLocal() {
        return Observable.create(new Observable.OnSubscribe<Object>() {

            @Override
            public void call(Subscriber<? super Object> subscriber) {
                try {
                    Thread.sleep(3000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println("getDataFromLocal" + System.currentTimeMillis());
                subscriber.onNext(1);
                subscriber.onCompleted();

            }
        });
    }

    public static Observable getDataFromNet() {
        return Observable.create(new Observable.OnSubscribe<Object>() {

            @Override
            public void call(Subscriber<? super Object> subscriber) {
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println("getDataFromNet" + System.currentTimeMillis());
                subscriber.onNext(2);
                subscriber.onCompleted();
            }
        });
    }
}
