package com.example.newlife;

import android.os.Bundle;
import android.widget.ImageView;

import com.example.adapter.MyPagerAdapter;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.Nullable;
import androidx.viewpager.widget.ViewPager;

public class CycleViewPagerAct extends BaseActivity{
    private int[] imgIds=new int[]{R.drawable.img4,R.drawable.img1,R.drawable.img2,R.drawable.img3,R.drawable.img4,R.drawable.img1};
    private List<ImageView> imgs;
    private boolean isChanged=false;
    private int currentIndex;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.viewpager_main);

        final ViewPager viewPager=findViewById(R.id.viewPager);
        initImgs();;
        MyPagerAdapter adapter=new MyPagerAdapter(imgs);
        viewPager.setAdapter(adapter);
        viewPager.setCurrentItem(1);
        currentIndex=1;
        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                if(position==imgIds.length-1){
                    currentIndex=1;
                }else if(position==1){
                    currentIndex=imgIds.length-1;
                }else{
                    currentIndex=position;
                }
            }

            @Override
            public void onPageScrollStateChanged(int state) {
                if(state==ViewPager.SCROLL_STATE_IDLE){
                    viewPager.setCurrentItem(currentIndex,false);
                }
            }
        });
    }

    private void initImgs(){
        imgs=new ArrayList<>();
        for(int i=0;i<imgIds.length;i++){
            ImageView imgView=new ImageView(this);
            imgView.setScaleType(ImageView.ScaleType.CENTER_CROP);
            imgView.setBackgroundResource(imgIds[i]);
            imgs.add(imgView);
        }

    }

}
