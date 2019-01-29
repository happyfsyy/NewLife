package com.example.newlife;

import android.graphics.Color;
import android.os.Bundle;
import android.view.Gravity;
import android.view.MenuItem;
import android.view.View;
import android.widget.Adapter;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import com.example.utils.LogUtils;
import com.google.android.material.navigation.NavigationView;

import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

/**
 * 参考链接：https://blog.csdn.net/lmj623565791/article/details/41531475
 */
public class DrawerLayoutTestAct extends AppCompatActivity {
    private DrawerLayout drawerLayout;
    private ListView listView;
    private Button openRightButton;
    private NavigationView navigationView;
    private Toolbar toolbar;
    
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_drawer_layout);
        listView = findViewById(R.id.drawer_layout_listview);
        drawerLayout=findViewById(R.id.drawer_layout);
        navigationView=findViewById(R.id.drawer_layout_navigation_view);
        navigationView.setCheckedItem(R.id.navigation_test_call);
        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                drawerLayout.closeDrawer(GravityCompat.START);
                return true;
            }
        });

        toolbar=findViewById(R.id.drawer_layout_toolbar);
        setSupportActionBar(toolbar);
//        ActionBar actionBar=getSupportActionBar();
//        actionBar.setDisplayHomeAsUpEnabled(true);
//        actionBar.setHomeAsUpIndicator(R.drawable.ic_menu);
        toolbar.setNavigationIcon(R.drawable.ic_menu);
//        toolbar.setLogo(R.mipmap.ic_launcher);
//        toolbar.setTitle("");
//        toolbar.setSubtitle("副标题");
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(drawerLayout.isDrawerOpen(GravityCompat.START)){
                    drawerLayout.closeDrawer(GravityCompat.START);
                }else{
                    drawerLayout.openDrawer(GravityCompat.START);
                }
            }
        });
        
        final Button message=findViewById(R.id.drawer_layout_toolbar_message);
        message.setPressed(true);
        message.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(DrawerLayoutTestAct.this, "message come in", Toast.LENGTH_SHORT).show();
            }
        });

        initListView();
//        initOpenRightButton();
        drawerLayout.setDrawerLockMode(DrawerLayout.LOCK_MODE_LOCKED_CLOSED,GravityCompat.END);
        drawerLayout.addDrawerListener(new DrawerLayout.DrawerListener() {
            @Override
            public void onDrawerSlide(@NonNull View drawerView, float slideOffset) {
                View content=drawerLayout.getChildAt(0);
                int viewId=drawerView.getId();
                float leftScale=0.7f+0.3f*slideOffset;
                float rightScale=1-slideOffset*0.3f;
//                if(viewId==R.id.drawer_layout_navigation_view){
//                   drawerView.animate().scaleX(leftScale).scaleY(leftScale)
//                           .alpha(leftScale).setDuration(0);
//                   content.animate().scaleX(rightScale).scaleY(rightScale).
//                           translationX(drawerView.getWidth()*slideOffset).setDuration(0);
//                }else if(viewId==R.id.drawer_layout_right_fragment){
//                    content.animate().scaleX(rightScale).scaleY(rightScale).
//                            translationX(-drawerView.getWidth()*slideOffset).setDuration(0);
//                }
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
//        openRightButton=findViewById(R.id.drawer_open_right_button);
        openRightButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                drawerLayout.openDrawer(GravityCompat.END);
                drawerLayout.setDrawerLockMode(DrawerLayout.LOCK_MODE_UNLOCKED,GravityCompat.END);
            }
        });
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        return super.onOptionsItemSelected(item);
    }
}
