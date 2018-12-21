package com.example.newlife;

import android.os.Bundle;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.viewpager.widget.PagerAdapter;
import androidx.viewpager.widget.ViewPager;

public class ViewPagerTestAct extends BaseActivity{

    private List<View> mViews;
    private LinearLayout dotViewGroup;
    private List<View> mDots;

    private static final String TAG = "---------------";

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.viewpager_main);

        ViewPager viewPager=findViewById(R.id.viewPager);
        dotViewGroup=findViewById(R.id.dot_viewGroup);

        initViews();
        MyPagerAdapter adapter=new MyPagerAdapter(mViews);
        viewPager.setAdapter(adapter);

        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
                Log.e(TAG,"----->>>onPageScrolled()-----");
            }

            @Override
            public void onPageSelected(int position) {
                Log.e(TAG,"----->>>onPageSelected()-----");
                clear();
                mDots.get(position).setBackgroundResource(R.drawable.dot_focused);
            }

            @Override
            public void onPageScrollStateChanged(int state) {
                Log.e(TAG,"----->>>onPageScrollStateChanged()-----");
            }
        });
    }

    private void clear(){
        for (int i=0;i<mDots.size();i++){
            mDots.get(i).setBackgroundResource(R.drawable.dot_normal);
        }
    }

    private float getDensity(){
        DisplayMetrics metrics=getResources().getDisplayMetrics();
        float density=metrics.density;
        return density;
    }

    private void initViews(){
        mViews=new ArrayList<>();
        mDots=new ArrayList<>();

        int[] resId={R.drawable.background1,R.drawable.background2,R.drawable.background3,R.drawable.background4};
        float density=getDensity();
        for(int i=0;i<resId.length;i++){
            View tempView=new View(this);
            tempView.setBackground(getResources().getDrawable(resId[i]));
            mViews.add(tempView);

            View dotView=new View(this);
            dotView.setBackgroundResource(R.drawable.dot_normal);
            LinearLayout.LayoutParams layoutParams=new LinearLayout.LayoutParams((int)(10*density),(int)(10*density));
            layoutParams.leftMargin=5;
            layoutParams.rightMargin=5;
//            dotView.setLayoutParams(layoutParams);
            dotViewGroup.addView(dotView,layoutParams);
            mDots.add(dotView);

        }


    }

    class MyPagerAdapter extends PagerAdapter{
        private List<View> mList;
        public MyPagerAdapter(List<View> list){
            Log.e(TAG,"----->>>MyPagerAdapter()-----");
            mList=list;
        }
        @Override
        public int getCount() {
            Log.e(TAG,"----->>>getCount()-----");
            return mList.size();
        }

        @Override
        public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
            Log.e(TAG,"----->>>isViewFromObject()-----");
            return view==object;
        }

        @Override
        public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
            Log.e(TAG,"----->>>destroyItem()-----");
            container.removeView(mList.get(position));
        }

        @NonNull
        @Override
        public Object instantiateItem(@NonNull ViewGroup container, int position) {
            Log.e(TAG,"----->>>instantiateItem()-----");
            container.addView(mList.get(position));
            return  mList.get(position);
        }
    }




}
