package com.example.newlife;

import android.app.Activity;
import android.os.Bundle;
import android.widget.TextView;

import com.example.fragment.TabFragment;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.viewpager.widget.ViewPager;

public class ViewPagerFragAct extends FragmentActivity {
    private ViewPager mViewPager;
    private TextView tab0;
    private TextView tab1;
    private TextView tab2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.viewpager_frag_main);

        initViews();
        setAdapter();

    }
    private void initViews(){
        mViewPager=findViewById(R.id.viewPager);
        tab0=findViewById(R.id.tab0);
        tab1=findViewById(R.id.tab1);
        tab2=findViewById(R.id.tab2);
    }
    private void setAdapter(){
        mViewPager.setAdapter(new MyPagerAdapter(getSupportFragmentManager()));

        mViewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                clearColor();
                if(position==0){
                    tab0.setTextColor(getResources().getColor(R.color.selected_color));
                }else if(position==1){
                    tab1.setTextColor(getResources().getColor(R.color.selected_color));
                }else{
                    tab2.setTextColor(getResources().getColor(R.color.selected_color));
                }
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
    }

    private void clearColor(){
        tab0.setTextColor(getResources().getColor(R.color.normal_color));
        tab1.setTextColor(getResources().getColor(R.color.normal_color));
        tab1.setTextColor(getResources().getColor(R.color.normal_color));
    }

    class MyPagerAdapter extends FragmentPagerAdapter{
        public MyPagerAdapter(@NonNull FragmentManager fm) {
            super(fm);
        }

        @NonNull
        @Override
        public Fragment getItem(int position) {
            return TabFragment.newInstance(position);
        }

        @Override
        public int getCount() {
            return 3;
        }
    }
}
