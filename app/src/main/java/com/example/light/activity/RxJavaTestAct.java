package com.example.light.activity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.example.newlife.R;
import com.example.utils.LogUtils;

import org.reactivestreams.Subscriber;
import org.reactivestreams.Subscription;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.Observer;
import io.reactivex.Scheduler;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

public class RxJavaTestAct extends AppCompatActivity {
    private Button test;
    private Observable<Integer> integerObservable;
    private Observer<Integer> integerObserver;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test);
        test=findViewById(R.id.test);

        initParams();

        test.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                integerObservable.subscribe(integerObserver);
//                Observable.create(new ObservableOnSubscribe<Integer>(){
//                    @Override
//                    public void subscribe(ObservableEmitter<Integer> emitter) throws Exception {
//                        LogUtils.e("Observable thread is "+Thread.currentThread().getName());
//                        emitter.onNext(1);
//                        emitter.onComplete();
//                    }
//                })
                integerObservable.subscribeOn(Schedulers.newThread())
                        .subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                        .doOnNext(new Consumer<Integer>() {
                            @Override
                            public void accept(Integer integer) throws Exception {
                                LogUtils.e("After observeon(MainThread),Current Thread is "+Thread.currentThread().getName());
                            }
                        })
                        .observeOn(Schedulers.io())
                        .subscribe(new Consumer<Integer>() {
                            @Override
                            public void accept(Integer integer) throws Exception {
                                LogUtils.e("After observeon(IO),Current Thread is"+Thread.currentThread().getName());
                            }
                        });
                //todo
//                integerObservable.subscribe()
            }
        });

    }
    private void initParams(){
        integerObservable=Observable.create(new ObservableOnSubscribe<Integer>() {
            @Override
            public void subscribe(ObservableEmitter<Integer> emitter) throws Exception {
                LogUtils.e("Observable thread is "+Thread.currentThread().getName());
                emitter.onNext(1);
                emitter.onNext(2);
                emitter.onComplete();
            }
        });
        integerObserver=new Observer<Integer>() {
            @Override
            public void onSubscribe(Disposable d) {
                Log.e("observer","onSubscribe");
            }
            @Override
            public void onNext(Integer integer) {
                Log.e("observer","onNext:value="+integer);

            }
            @Override
            public void onError(Throwable e) {
                Log.e("observer","onError:value="+e.getMessage());
            }
            @Override
            public void onComplete() {
                Log.e("observer","onComplete");
            }
        };

    }
}
