package com.example.newlife;

import android.os.Bundle;
import android.util.DisplayMetrics;
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

            }

            @Override
            public void onPageSelected(int position) {
                clear();
                mDots.get(position).setBackgroundResource(R.drawable.dot_focused);
            }

            @Override
            public void onPageScrollStateChanged(int state) {

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
            mList=list;
        }
        @Override
        public int getCount() {
            return mList.size();
        }

        @Override
        public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
            return view==object;
        }

        @Override
        public void destroyItem(@NonNull View container, int position, @NonNull Object object) {
//            super.destroyItem(container, position, object);
            ((ViewGroup)container).removeView(mList.get(position));
        }

        @NonNull
        @Override
        public Object instantiateItem(@NonNull View container, int position) {
            ((ViewGroup)container).addView(mList.get(position),0);
            return mList.get(position);
        }
    }




}
