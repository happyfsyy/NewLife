package com.example.newlife;

import android.animation.Animator;
import android.animation.AnimatorInflater;
import android.animation.AnimatorListenerAdapter;
import android.animation.AnimatorSet;
import android.animation.FloatEvaluator;
import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.nfc.Tag;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class AnimatorTestAct extends AppCompatActivity {
    private static final String TAG = "AnimatorTestAct";

    private Button start;
    private TextView alphaView;
    private TextView rotateView;
    private TextView translateView;
    private TextView scaleView;
    private TextView combinationView;
    private TextView viewPropertyAnimatorTextView;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_animator_test);
        initViews();

        start.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                loadCombination();
                testViewPropertyAnimator();
            }
        });
    }
    private void initViews(){
        start=findViewById(R.id.animator_test_start);
        alphaView=findViewById(R.id.animator_test_alpha);
        rotateView=findViewById(R.id.animator_test_rotate);
        translateView=findViewById(R.id.animator_test_translate);
        scaleView=findViewById(R.id.animator_test_scale);
        combinationView=findViewById(R.id.animator_test_combination);
        viewPropertyAnimatorTextView=findViewById(R.id.animator_test_view_property);
    }

    private void ChangeNumValueAnimator(){
        ValueAnimator animator=ValueAnimator.ofFloat(0f,1f);
        animator.setDuration(300);
        animator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                float currentValue=(float)animation.getAnimatedValue();
                Log.e(TAG,"当前值是："+currentValue);
            }
        });
        animator.start();
    }
    private void changeAlpha(){
        ObjectAnimator animator=ObjectAnimator.ofFloat(alphaView,"alpha",1f,0f,1f);
        animator.setDuration(3000);
        animator.start();
    }

    private void changeRotation(){
        ObjectAnimator animator=ObjectAnimator.ofFloat(rotateView,"rotation",0,360f);
        animator.setDuration(3000);
        animator.start();
    }

    private void changeTranslationX(){
        float translationX=translateView.getTranslationX();
        Log.e(TAG,"当前的偏移位置："+translationX);
        ObjectAnimator animator=ObjectAnimator.ofFloat(translateView,"translationX",translationX,-100,translationX);
        animator.setDuration(3000);
        animator.start();
    }

    private void changeScale(){
        ObjectAnimator animator=ObjectAnimator.ofFloat(scaleView,"scaleY",1f,3f,1f);
        animator.setDuration(3000);
        animator.start();
    }

    private void startAnimatorSet(){
        ObjectAnimator translationAnim=ObjectAnimator.ofFloat(combinationView,"translationX",-500,0);
        ObjectAnimator rotateAnim=ObjectAnimator.ofFloat(combinationView,"rotation",0,360);
        ObjectAnimator fadeInOut=ObjectAnimator.ofFloat(combinationView,"alpha",1f,0f,1f);
        AnimatorSet animatorSet=new AnimatorSet();
        animatorSet.play(fadeInOut).with(rotateAnim).after(translationAnim);
        animatorSet.setDuration(5000);
        animatorSet.start();
    }

    private void loadCombination(){
        Animator animator=AnimatorInflater.loadAnimator(this,R.animator.combination);
        animator.setTarget(combinationView);
        animator.start();
    }

    private void testViewPropertyAnimator(){
        viewPropertyAnimatorTextView.animate().alpha(0f).x(-200).y(1500).setDuration(2000);
    }

    private void addListener(Animator animator){
        animator.addListener(new Animator.AnimatorListener() {
            /**
             * 动画开始的时候调用
             */
            @Override
            public void onAnimationStart(Animator animation) {
            }
            /**
             * 动画结束的时候调用
             */
            @Override
            public void onAnimationEnd(Animator animation) {
            }
            /**
             * 动画被取消的时候调用
             */
            @Override
            public void onAnimationCancel(Animator animation) {
            }
            /**
             * 动画重复执行的时候调用
             */
            @Override
            public void onAnimationRepeat(Animator animation) {
            }
        });
        animator.addListener(new AnimatorListenerAdapter() {
            @Override
            public void onAnimationEnd(Animator animation) {

            }
        });
    }

}
