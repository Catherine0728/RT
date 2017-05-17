package com.example;


import java.util.Collections;
import java.util.concurrent.TimeUnit;

import io.reactivex.Flowable;
import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.ObservableSource;
import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.BiFunction;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Function;


public class MyClass {
    public static void main(String[] arg) {
//        skip();
//        r2Skip();
//        first();
//        create();
//        map();
//        flatmap();
//        range();
//        retryWhen();
        newOserver();
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

    public static void map() {
        String[] str = {"this", "is", "a", "test"};
        Observable.fromArray(str)
                .map(s -> {
                    System.out.println("emit data===>" + s);
                    return s.toUpperCase();
                })
                .filter(s -> !s.contains("A"))
                .toList()
                .map(strings -> {
                    System.out.println("the data is==>" + strings);
                    Collections.reverse(strings);
                    return strings;
                })
                .subscribe(strings -> System.out.println("the result is===>" + strings));
    }

    public static void flatmap() {
        String[] urlArray = {"http://www.baidu.com", "http://www.ca.com", "http://www.jiguangtuisong.com"};
        Observable.fromArray(urlArray)
                .flatMap(new Function<String, ObservableSource<?>>() {
                    @Override
                    public ObservableSource<String> apply(String s) throws Exception {
                        return getUrlIp(s);
                    }

                    private ObservableSource<String> getUrlIp(String str) {
                        return Observable.create(new ObservableOnSubscribe<String>() {
                            @Override
                            public void subscribe(ObservableEmitter<String> e) throws Exception {
                                String ip = str.substring(3);
                                e.onNext(ip);
                                System.out.println("emit data is===>" + ip);
                            }
                        });
                    }

                })
                .subscribe(new Consumer<Object>() {
                    @Override
                    public void accept(Object o) throws Exception {
                        System.out.println("the result is====>" + o.toString());
                    }
                });
    }

    /**
     * range(m,n)
     * 产生一组大于m的n个数据
     */
    public static void range() {
        Observable.range(5, 4)
                .subscribe(integer -> System.out.println(integer));

    }


    /**
     * 用于事件流中，可以延迟发送事件流中的某一次发送
     */
    public static void delay() {
    }

    /**
     * 对当前失败的接口进行重试
     */
    public static void retryWhen() {
        System.out.println(System.currentTimeMillis());
        Observable.interval(2, 3, TimeUnit.MILLISECONDS)
                .retryWhen(new Function<Observable<Throwable>, ObservableSource<?>>() {
                    @Override
                    public ObservableSource<?> apply(Observable<Throwable> throwableObservable) throws Exception {
                        return Observable.just(throwableObservable)
                                .zipWith(Observable.range(4, 3), new BiFunction<Observable<Throwable>, Integer, Object>() {
                                    @Override
                                    public Object apply(Observable<Throwable> throwableObservable, Integer integer) throws Exception {
                                        System.out.println(System.currentTimeMillis());
                                        System.out.println("the emit data===>" + integer);
                                        return integer;
                                    }
                                })
                                .flatMap(new Function<Object, ObservableSource<?>>() {
                                    @Override
                                    public ObservableSource<?> apply(Object i) throws Exception {
                                        System.out.println(System.currentTimeMillis());
                                        System.out.println("flatmap is==>" + i);
                                        System.out.println("flatmap is==>" + (long) Math.pow(5, 3));
                                        return Observable.timer((long) Math.pow(5, 3), TimeUnit.MILLISECONDS);
                                    }
                                });
                    }
                })
                .subscribe(aLong -> System.out.println(aLong));


    }

    public static void newOserver() {
        //第一步，创建一个可观察者
        Observable observable = Observable.create(new ObservableOnSubscribe<Object>() {
            @Override
            public void subscribe(ObservableEmitter<Object> e) throws Exception {
                e.onNext("this");
                e.onNext("is a test");
                e.onComplete();
            }
        });
        //第二步：创建一个观察者
        Observer o = new Observer() {
            private Disposable disposable;

            @Override
            public void onSubscribe(Disposable d) {
                this.disposable = d;
            }

            @Override
            public void onNext(Object value) {
                if (value.equals("is a test")){
                    disposable.dispose();
                }
                System.out.println(value);
            }

            @Override
            public void onError(Throwable e) {

            }

            @Override
            public void onComplete() {
                System.out.println("oncomplete");
            }
        };
        //第三步：建立订阅关系
        observable.subscribe(o);

    }


}
