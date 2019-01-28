package com.example.newlife;

import android.graphics.Color;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.Adapter;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import com.example.utils.LogUtils;

import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

/**
 * 参考链接：https://blog.csdn.net/lmj623565791/article/details/41531475
 */
public class DrawerLayoutTestAct extends AppCompatActivity {
    private DrawerLayout drawerLayout;
    private ListView listView;
    private Button openRightButton;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_drawer_layout);
        listView = findViewById(R.id.drawer_layout_listview);
        drawerLayout=findViewById(R.id.drawer_layout);
        openRightButton=findViewById(R.id.drawer_open_right_button);

        initListView();
        initOpenRightButton();

        drawerLayout.setDrawerLockMode(DrawerLayout.LOCK_MODE_LOCKED_CLOSED,GravityCompat.END);
        drawerLayout.addDrawerListener(new DrawerLayout.DrawerListener() {
            @Override
            public void onDrawerSlide(@NonNull View drawerView, float slideOffset) {
                View content=drawerLayout.getChildAt(0);
                int viewId=drawerView.getId();
                float leftScale=0.7f+0.3f*slideOffset;
                float rightScale=1-slideOffset*0.3f;
                if(viewId==R.id.drawer_layout_left_fragment){
                   drawerView.animate().scaleX(leftScale).scaleY(leftScale)
                           .alpha(leftScale).setDuration(0);
                   content.animate().scaleX(rightScale).scaleY(rightScale).
                           translationX(drawerView.getWidth()*slideOffset).setDuration(0);
                }else if(viewId==R.id.drawer_layout_right_fragment){
                    content.animate().scaleX(rightScale).scaleY(rightScale).
                            translationX(-drawerView.getWidth()*slideOffset).setDuration(0);
                }
            }
            @Override
            public void onDrawerOpened(@NonNull View drawerView) {
            }
            @Override
            public void onDrawerClosed(@NonNull View drawerView) {
                LogUtils.e("Drawer关闭了");
                drawerLayout.setDrawerLockMode(DrawerLayout.LOCK_MODE_LOCKED_CLOSED,GravityCompat.END);
            }
            @Override
            public void onDrawerStateChanged(int newState) {
                if(newState==DrawerLayout.STATE_IDLE){
                    LogUtils.e("空闲状态，我可以做很多事了");
                }
            }
        });
    }

    private void initListView(){
        ArrayAdapter<Integer> arrayAdapter=new ArrayAdapter<>(this,android.R.layout.simple_list_item_1,
                Arrays.asList(0,1,2,3,4,5,6,7,8,9,10,11,12,13,14,15,16,17,18,19,20));
        listView.setAdapter(arrayAdapter);
    }
    private void initOpenRightButton(){
        openRightButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                drawerLayout.openDrawer(GravityCompat.END);
                drawerLayout.setDrawerLockMode(DrawerLayout.LOCK_MODE_UNLOCKED,GravityCompat.END);
            }
        });
    }
}
