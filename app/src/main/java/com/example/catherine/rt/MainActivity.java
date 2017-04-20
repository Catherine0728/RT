package com.example.catherine.rt;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.jakewharton.rxbinding2.widget.RxTextView;


import butterknife.BindView;
import butterknife.ButterKnife;
import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.ObservableSource;
import io.reactivex.ObservableTransformer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Function3;
import io.reactivex.schedulers.Schedulers;
import io.reactivex.subscribers.ResourceSubscriber;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.TimeZone;

public class MainActivity extends AppCompatActivity {

    @BindView(R.id.btn)
    Button btn;

    @BindView(R.id.textView)
    TextView textView;
    @BindView(R.id.editText)
    EditText editText;
    @BindView(R.id.editText2)
    EditText editText2;
    @BindView(R.id.editText3)
    EditText editText3;


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
    }

    private void setListener() {
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                concat();
                boolean is = aboveSevenOclock();
                System.out.println(is);
            }
        });
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


}
