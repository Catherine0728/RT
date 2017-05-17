package com.example.catherine.rt;

import android.Manifest;
import android.os.Bundle;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.jakewharton.rxbinding2.widget.RxTextView;
import com.tbruyelle.rxpermissions2.Permission;
import com.tbruyelle.rxpermissions2.RxPermissions;


import butterknife.BindView;
import butterknife.ButterKnife;
import io.reactivex.BackpressureStrategy;
import io.reactivex.Flowable;
import io.reactivex.FlowableEmitter;
import io.reactivex.FlowableOnSubscribe;
import io.reactivex.Maybe;
import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.ObservableSource;
import io.reactivex.ObservableTransformer;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Function3;
import io.reactivex.functions.Predicate;
import io.reactivex.schedulers.Schedulers;
import io.reactivex.subscribers.ResourceSubscriber;

import java.io.File;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.TimeZone;
import java.util.concurrent.TimeUnit;

public class MainActivity extends AppCompatActivity {

    @BindView(R.id.btn)
    Button btn;

    @BindView(R.id.btn_pause)
    Button btnPause;
    @BindView(R.id.textView)
    TextView textView;
    @BindView(R.id.editText)
    EditText editText;
    @BindView(R.id.editText2)
    EditText editText2;
    @BindView(R.id.editText3)
    EditText editText3;
    private Flowable interval;
    private Flowable timer;
    private Disposable disposable;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        setListener();
        combineLatest();
        first();
//        just();
//        create();
        interval = interval();
        timer = timer();
        testObserver();
    }

    private void rxPermissons(){
        RxPermissions rxPermissions=new RxPermissions(MainActivity.this);
        rxPermissions.requestEach(
                Manifest.permission.ACCESS_FINE_LOCATION,
                Manifest.permission.WRITE_EXTERNAL_STORAGE,
                Manifest.permission.READ_CALENDAR,
                Manifest.permission.READ_CALL_LOG,
                Manifest.permission.READ_CONTACTS,
                Manifest.permission.READ_PHONE_STATE,
                Manifest.permission.READ_SMS,
                Manifest.permission.RECORD_AUDIO,
                Manifest.permission.CAMERA,
                Manifest.permission.CALL_PHONE,
                Manifest.permission.SEND_SMS)
                .subscribe(permission -> {
                    if (permission.granted) {
                        // 用户已经同意该权限
                        System.out.println(permission.name + " is granted.");
                    } else if (permission.shouldShowRequestPermissionRationale) {
                        // 用户拒绝了该权限，没有选中『不再询问』（Never ask again）,那么下次再次启动时，还会提示请求权限的对话框
                        System.out.println( permission.name + " is denied. More info should be provided.");
                    } else {
                        // 用户拒绝了该权限，并且选中『不再询问』
                        System.out.println(permission.name + " is denied.");
                    }
                });

    }

    private void setListener() {
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                concat();
//                boolean is = aboveSevenOclock();
//                System.out.println(System.currentTimeMillis());
////                disposable = interval.subscribe(o -> {
////                    System.out.println(System.currentTimeMillis() + "interval==>" + o);
////                });
//
//                disposable = timer.subscribe(new Consumer() {
//                    @Override
//                    public void accept(Object o) throws Exception {
//                        System.out.println(System.currentTimeMillis() + "timer==>" + o);
//
//                    }
//                });
                rxPermissons();

//                isExistFile();

            }
        });
        btnPause.setOnClickListener(view -> {
            System.out.println("我要取消订阅咯哦");
            disposable.dispose();

        });
    }

    /**
     * timer 用于创建Observable，延迟发送一次
     * <p>
     * timer 和 interval的异同：
     * 同：都是返回一个observable，都是延迟发送
     * 异：timer是延迟发送一次，而interval是timer的加强版，他是一个不断周期发送
     */
    private Flowable<Long> timer() {
        return Flowable.timer(3, TimeUnit.SECONDS);

    }

    /**
     * interval:用于创建Observable，跟TimerTask类似，用于周期性发送
     * <p>
     * 三个参数，
     * 第一个参数是什么时候开始
     * 第二个参数是两个消息发送之间的间隔时间
     * 第三个参数是时间单位
     * <p>
     * 两个参数
     * 第一个参数是消息的间隔时间
     * 第二个参数是时间单位
     */

    private Flowable<Long> interval() {
//       return Flowable.interval(2,7,TimeUnit.SECONDS);
        return Flowable.interval(3, TimeUnit.SECONDS);
    }


    private void concat() {
        Observable.concat(
                getDataFromLocal(),
                getDataFromNet(),
                getDataFromLocal(),
                getDataFromLocal()
        )
                .compose(ioToMain())
                .subscribe(new Consumer() {
                    @Override
                    public void accept(Object o) throws Exception {
                        System.out.println(o);
                    }
                });
    }

    private <T> ObservableTransformer<T, T> ioToMain() {
        return new ObservableTransformer<T, T>() {
            @Override
            public ObservableSource<T> apply(Observable<T> observable) {
                return observable.subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread());
            }
        };
    }

    private Observable getDataFromLocal() {
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

    private Observable getDataFromNet() {
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

    private void just() {
        Observable.just("sdfnsa").subscribe(new Consumer<String>() {
            @Override
            public void accept(String s) throws Exception {
                System.out.println(s);
            }
        }, new Consumer<Throwable>() {
            @Override
            public void accept(Throwable throwable) throws Exception {

            }
        });
    }

    private void create() {
        Observable.create(new ObservableOnSubscribe<Object>() {
            @Override
            public void subscribe(ObservableEmitter<Object> e) throws Exception {
                System.out.println("create");
                e.onNext("create next");
            }
        }).subscribe(new Consumer<Object>() {
            @Override
            public void accept(Object o) throws Exception {
                System.out.println("print===>" + o);
            }
        });

        ResourceSubscriber<String> subscriber = new ResourceSubscriber<String>() {
            @Override
            protected void onStart() {
                System.out.println("onstart");
                request(Long.MAX_VALUE);
            }

            @Override
            public void onNext(String integer) {
                System.out.println(integer);
            }

            @Override
            public void onError(Throwable t) {
                System.out.println(t);
            }

            @Override
            public void onComplete() {
                System.out.println("complete");
            }
        };
        subscriber.onNext("next");
    }

    /**
     * 合并最近N个点
     */
    private void combineLatest() {
        Observable<CharSequence> edit = RxTextView.textChanges(editText).skip(1);
        Observable<CharSequence> editOne = RxTextView.textChanges(editText2).skip(1);
        Observable<CharSequence> editTwo = RxTextView.textChanges(editText3).skip(1);
        Observable<Boolean> isNotEmpty = Observable.combineLatest(edit, editOne, editTwo,
                new Function3<CharSequence, CharSequence, CharSequence, Boolean>() {
                    @Override
                    public Boolean apply(CharSequence charSequence, CharSequence charSequence2, CharSequence charSequence3) throws Exception {
                        boolean isEditEmpty = charSequence != null && !charSequence.equals("");
                        boolean isEditOneEmpty = charSequence2 != null && !charSequence2.equals("");
                        boolean isEditTwoEmpty = charSequence3 != null && !charSequence3.equals("");
                        return isEditEmpty && isEditOneEmpty && isEditTwoEmpty;
                    }
                });
        Consumer<Boolean> consumer = new Consumer<Boolean>() {
            @Override
            public void accept(Boolean aBoolean) throws Exception {
                if (aBoolean) {
                    btn.setVisibility(View.VISIBLE);
                }
            }
        };
        isNotEmpty.subscribe(consumer);
    }

    /**
     * first操作符是把源Observable产生的结果的第一个提交给订阅者，first操作符可以使用elementAt(0)和take(1)替代
     */
    private void first() {
        final String[] str = {"1", "2", "3"};
        Observable.create(new ObservableOnSubscribe<String[]>() {
            @Override
            public void subscribe(ObservableEmitter<String[]> e) throws Exception {
                e.onNext(str);
            }
        })
                .first(str)
                .subscribe(new Consumer<String[]>() {
                    @Override
                    public void accept(String[] strings) throws Exception {
                        for (String str : strings) {
                            System.out.println(str);
                        }
                    }
                });

    }

    /**
     * 是否超过了下午七点，如果是，那么就只判断是否完成自主学习的任务
     */
    public static boolean aboveSevenOclock() {
        SimpleDateFormat sf = new SimpleDateFormat("HH:mm:ss");
        Calendar c = Calendar.getInstance();
        c.setTimeZone(TimeZone.getTimeZone("GMT+8:00"));

        Date oldDate = null;
        try {
            oldDate = sf.parse(sf.format(c.getTime()));
        } catch (ParseException e) {
            e.printStackTrace();
        }
        System.out.println("当前时间是＝＝＝＝》" + oldDate.getHours());
        return oldDate.getHours() > 18;
    }

    /**
     * observer 和 observable
     */
    private void testObserver() {
        Maybe<List<Integer>> ob = Observable.just(1, 2, 3, 4)
                .toList()
                .filter(o -> {
                    for (int oo : o) {
                        return oo < 4;
                    }
                    return false;
                });
        Observer<Integer> observer = new Observer<Integer>() {
            private Disposable disposable;

            @Override
            public void onSubscribe(Disposable d) {
                disposable = d;
            }

            @Override
            public void onNext(Integer value) {
                if (value > 3) {   // >3 时为异常数据，解除订阅
                    disposable.dispose();
                }
            }

            @Override
            public void onError(Throwable e) {

            }

            @Override
            public void onComplete() {


            }
        };
        ob.subscribe(new Consumer<List<Integer>>() {
            @Override
            public void accept(List<Integer> integers) throws Exception {
                System.out.println("testObserver" + integers);
            }
        });
    }

    /**
     * 背压
     * 后者是1.X的方式
     */
    private void backPressure() {
        Flowable.create(new FlowableOnSubscribe<Object>() {
            @Override
            public void subscribe(FlowableEmitter<Object> e) throws Exception {

            }
        }, BackpressureStrategy.ERROR)
                .subscribeOn(Schedulers.computation())
                .observeOn(Schedulers.newThread());

        Flowable.range(2, 4)
                .onBackpressureDrop()
                .subscribe(integer -> {

                });
    }

    private void isExistFile() {
        Observable.just(Environment.getExternalStorageDirectory() + "/xxxx.apk")
                .filter(s -> {
                    System.out.println(s);
                    return new File(s).exists();
                })
                .subscribe(s -> System.out.println("哈啊哈，我存在也＝＝＝＝"+s));
    }

}


