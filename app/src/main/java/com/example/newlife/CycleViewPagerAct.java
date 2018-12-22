package com.example.newlife;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.example.adapter.MyPagerAdapter;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.Nullable;
import androidx.viewpager.widget.ViewPager;

/**
 * 实现了循环滑动的功能，左右切换，配合dot一起。
 * AtuoScroll自动切换再加进去，特别别扭，而且setCurrentItem，有明显的切换痕迹，并不是自动切换。
 */
public class CycleViewPagerAct extends BaseActivity{
    private int[] imgIds=new int[]{R.drawable.img1,R.drawable.img2,R.drawable.img3,R.drawable.img4};
    private List<ImageView> imgs=new ArrayList<>();
    private ViewPager viewPager;
    private LinearLayout dotViewGroup;
    private List<View> dots=new ArrayList<>();
    private int currentIndex;
    private boolean isChanging=false;
    private boolean isContinue;
    private Handler mHandler=new Handler(){
        @Override
        public void handleMessage(Message msg) {
            int nextPos=currentIndex+1;
            if(nextPos==imgs.size()-1){
                currentIndex=1;
            }else{
                currentIndex=nextPos;
            }
            clearDotState();
            dots.get(currentIndex-1).setBackgroundResource(R.drawable.dot_focused);
            viewPager.setCurrentItem(currentIndex);
        }
    };

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.viewpager_main);

        viewPager=findViewById(R.id.viewPager);
        dotViewGroup=findViewById(R.id.dot_viewGroup);

        initImgs();
        initDots();
        setAdapter();
        currentIndex=1;

        isContinue=true;

        autoScroll();
    }

    private void autoScroll(){
        new Thread(new Runnable() {
            @Override
            public void run() {
                while(true){
                    if(isContinue){
                        try{
                            Thread.sleep(2000);
                        }catch (Exception e){
                            e.printStackTrace();
                        }
                        mHandler.sendEmptyMessage(-1);
                    }
                }
            }
        }).start();
    }


    private void initImgs(){
        addImg(imgIds.length-1);
        for(int i=0;i<imgIds.length;i++){
            addImg(i);
        }
        addImg(0);
    }

    private void initDots(){
        for(int i=0;i<imgIds.length;i++){
            View dot=new View(this);
            if(i!=0){
                dot.setBackgroundResource(R.drawable.dot_normal);
            }else{
                dot.setBackgroundResource(R.drawable.dot_focused);
            }
            LinearLayout.LayoutParams params=new LinearLayout.LayoutParams(30,30);
            params.setMargins(10,0,10,0);
            dotViewGroup.addView(dot,params);
            dots.add(dot);
        }
    }
    private void clearDotState(){
        for(int i=0;i<dots.size();i++){
            dots.get(i).setBackgroundResource(R.drawable.dot_normal);
        }
    }

    private void addImg(int index){
        ImageView imgView=new ImageView(this);
        imgView.setScaleType(ImageView.ScaleType.CENTER_CROP);
        imgView.setBackgroundResource(imgIds[index]);
        imgs.add(imgView);
    }

    private void setAdapter(){
        MyPagerAdapter adapter=new MyPagerAdapter(imgs);
        viewPager.setAdapter(adapter);
        viewPager.setCurrentItem(1);
        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                isChanging=true;
                if(position==imgs.size()-1){
                    currentIndex=1;
                }else if(position==0){
                    currentIndex=imgs.size()-2;
                }else{
                    currentIndex=position;
                }
                clearDotState();
                dots.get(currentIndex-1).setBackgroundResource(R.drawable.dot_focused);
            }

            @Override
            public void onPageScrollStateChanged(int state) {
                if(state==ViewPager.SCROLL_STATE_IDLE){
                    if(isChanging){
                        isChanging=false;
                        viewPager.setCurrentItem(currentIndex,false);
                    }
                }
            }
        });
        viewPager.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                switch (event.getAction()){
                    case MotionEvent.ACTION_DOWN:
                    case MotionEvent.ACTION_MOVE:
                        isContinue=false;
                        break;
                    case MotionEvent.ACTION_UP:
                        isContinue=true;
                        break;
                    default:
                        isContinue=false;
                        break;
                }
                return false;
            }
        });
    }

}
