package com.example;


import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.TimeZone;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.functions.Consumer;


public class MyClass {

    public static void main(String[] args) {
//        create();
//        first();
//        OralTestResult();
//        scan();
//       boolean is= aboveSevenOclock();
        String str = "朗读韵文\nI met a snake.\nNear the lake.\nHe ate the cake.\nMade his stomach ache.";
        System.out.println(noChinsesText(str));
        create();

    }

    public static String noChinsesText(String text) {
        String reg = "[\u4e00-\u9fa5]";

        Pattern pat = Pattern.compile(reg);

        Matcher mat = pat.matcher(text);

        String repickStr = mat.replaceAll("");
        return repickStr;
    }

    /**
     * 是否超过了下午七点，如果是，那么就只判断是否完成自主学习的任务
     */
    public static boolean aboveSevenOclock() {
        SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Calendar c = Calendar.getInstance();
        c.setTimeZone(TimeZone.getTimeZone("GMT+8:00"));

        Date oldDate = null;
        try {
            oldDate = sf.parse(sf.format(c.getTime()));
        } catch (ParseException e) {
            e.printStackTrace();
        }
        System.out.println("当前时间是＝＝＝＝》" + oldDate.getHours());
        return oldDate.getHours() > 9;
    }

    public static void create() {

        Observable.create(new ObservableOnSubscribe<Object>() {
            @Override
            public void subscribe(ObservableEmitter<Object> e) throws Exception {
                e.onNext("哈哈");
            }
        })
                .subscribe(new Consumer<Object>() {
                    @Override
                    public void accept(Object o) throws Exception {
                        System.out.println(" the result is===>" + o);
                    }
                });

    }


}
