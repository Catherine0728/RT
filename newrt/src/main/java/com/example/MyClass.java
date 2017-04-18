package com.example;


import io.reactivex.Flowable;
import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;


public class MyClass {
    public static void main(String[] arg) {
//        skip();
//        r2Skip();
        first();
//        create();
    }

    public static void skip() {
        Observable.range(0, 10)
                .skip(1)
                .subscribe(new Observer<Integer>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                        d.isDisposed();
                    }

                    @Override
                    public void onNext(Integer value) {
                        System.out.println(value);
                    }

                    @Override
                    public void onError(Throwable e) {
                        System.out.println(e);
                    }

                    @Override
                    public void onComplete() {
                        System.out.println("oncompleter");
                    }
                });

    }

    public static void r2Skip() {
        Flowable.range(0, 10)
                .skip(2)
                .subscribe(new Consumer<Integer>() {
                    @Override
                    public void accept(Integer integer) throws Exception {
                        System.out.println(integer);
                    }
                });

    }

    /**
     * first操作符是把源Observable产生的结果的第一个提交给订阅者，first操作符可以使用elementAt(0)和take(1)替代
     */
    // TODO: 17/4/17 rxjava 2之后好像有改动
    public static void first() {
        Observable.concat(getDataFromLocal(),
                getDataFromNet())
                .firstElement()
                .toObservable()
                .publish()
        .subscribe(new Consumer() {
            @Override
            public void accept(Object o) throws Exception {
                System.out.println(o);
            }
        });
    }

    public static Observable getDataFromLocal() {
        return Observable.create(new ObservableOnSubscribe() {
            @Override
            public void subscribe(ObservableEmitter e) throws Exception {
                Thread.sleep(3000);
                System.out.println("getDataFromLocal" + System.currentTimeMillis());
                e.onNext(1);
                e.onComplete();
            }
        });
    }

    public static Observable getDataFromNet() {
        return Observable.create(new ObservableOnSubscribe() {
            @Override
            public void subscribe(ObservableEmitter e) throws Exception {
                Thread.sleep(1000);
                System.out.println("getDataFromNet" + System.currentTimeMillis());
                e.onNext(2);
                e.onComplete();
            }
        });
    }

    public static void create() {
        Observable o = Observable.create(new ObservableOnSubscribe<Object>() {
            @Override
            public void subscribe(ObservableEmitter<Object> e) throws Exception {
                e.onComplete();
                for (int i = 0; i < 5; i++) {
                    System.out.println("emit data" + i);
                    e.onNext("create " + i);
                }
            }
        });
        o.subscribe(new Consumer() {
            @Override
            public void accept(Object o) throws Exception {
                System.out.println("data is===>" + o);
            }
        });
    }


}
