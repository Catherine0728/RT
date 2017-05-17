package com.example;


import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.TimeZone;

import rx.Observable;
import rx.Subscriber;
import rx.functions.Action1;
import rx.functions.Func2;

public class MyClass {

    public static void main(String[] args) {
//        create();
//        first();
//        OralTestResult();
//        scan();
       boolean is= aboveSevenOclock();
        System.out.println(is);
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

    /**
     * 如果这个数值就是0的话，就必须要用0.0,而不是＃.0,否则，就会个位缺失
     */
    public static void OralTestResult() {
        DecimalFormat decimalFormat = (DecimalFormat) NumberFormat.getInstance(Locale.CHINA);
        decimalFormat.applyPattern("0.0");//zero shows as absen
//        decimalFormat.applyPattern("#.0");//zero shows as absen
        System.out.println(decimalFormat.format(0.0));
    }

    /**
     * 暂时没有找到太大的用处，可以替代for循环？
     */
    public static void scan() {
        String[] str = {"liu", "hong", "ling", "catherine"};
        Observable<String> scanAble = Observable.from(str);
        final List<String> newStr = new ArrayList<>();
        scanAble.scan("iam", new Func2<String, String, String>() {
            @Override
            public String call(String s, String s2) {
                String result = s + s2;
                System.out.println("result is==>" + result);
                return result;

            }
        })
        ;

    }

    /**
     * 是否超过了下午七点，如果是，那么就只判断是否完成自主学习的任务
     */
    public static boolean aboveSevenOclock() {
        SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Calendar c = Calendar.getInstance();
        c.setTimeZone(TimeZone.getTimeZone("GMT+8:00"));

        Date oldDate=null;
        try {
            oldDate = sf.parse(sf.format(c.getTime()));
        } catch (ParseException e) {
            e.printStackTrace();
        }
        System.out.println("当前时间是＝＝＝＝》" + oldDate.getHours());
        return oldDate.getHours() > 9;
    }



}
