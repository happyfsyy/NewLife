package com.example.light.activity;

import android.os.Bundle;
import android.util.Log;

import com.example.newlife.R;

import org.reactivestreams.Subscriber;
import org.reactivestreams.Subscription;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;

public class RxJavaTestAct extends AppCompatActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test);
        Subscriber<String> subscriber=new Subscriber<String>(){
            @Override
            public void onSubscribe(Subscription s) {
                Log.e("Subscriber","onSubscribe()");
            }
            @Override
            public void onNext(String s) {
                Log.e("Subscriber","onNext");
            }
            @Override
            public void onError(Throwable t) {
                Log.e("Subscriber","onError");
            }
            @Override
            public void onComplete() {
                Log.e("Subscriber","onComplete");
            }
        };
        Observer<String> observer=new Observer<String>() {
            @Override
            public void onSubscribe(Disposable d) {
                Log.e("Observer","onSubscribe");
            }
            @Override
            public void onNext(String s) {
                Log.e("Observer","onNext");
            }
            @Override
            public void onError(Throwable e) {
                Log.e("Observer","OnError");
            }
            @Override
            public void onComplete() {
                Log.e("Observer","OnComplete");
            }
        };
        Observable<String> observable=Observable.create(new ObservableOnSubscribe<String>() {
            @Override
            public void subscribe(ObservableEmitter<String> emitter) throws Exception {
                //todo sth
            }
        });
    }
}
