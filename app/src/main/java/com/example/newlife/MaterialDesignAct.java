package com.example.newlife;

import android.os.Bundle;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.widget.Toast;

import com.example.adapter.FruitAdapter;
import com.example.bean.Fruit;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.navigation.NavigationView;
import com.google.android.material.snackbar.Snackbar;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

public class MaterialDesignAct extends AppCompatActivity {
    private DrawerLayout drawerLayout;
    private  Fruit[] fruits={new Fruit("Apple",R.drawable.apple),new Fruit("Banana",R.drawable.banana),
            new Fruit("Orange",R.drawable.orange),new Fruit("Watermelon",R.drawable.watermelon),
            new Fruit("Pear",R.drawable.pear),new Fruit("Grape",R.drawable.grape),
            new Fruit("Pineapple",R.drawable.pineapple),new Fruit("Strawberry",R.drawable.strawberry),
            new Fruit("Cherry",R.drawable.cherry),new Fruit("Mango",R.drawable.mango)};
    private List<Fruit> fruitList=new ArrayList<>();
    private SwipeRefreshLayout swipeRefreshLayout;
    private FruitAdapter adapter;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_material_design_main);
        Toolbar toolbar=findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        drawerLayout=findViewById(R.id.drawer_layout);
        NavigationView navigationView=findViewById(R.id.navigation_view);
        //虽然调用getSupportActionBar()方法得到ActionBar的实例，但是这个ActionBar的具体实现是由ToolBar来完成的。
        ActionBar actionBar=getSupportActionBar();
        //ToolBar最左侧的按钮叫作HomeAsUp按钮，默认的图标是一个返回的箭头，含义是返回上一个活动。
        //HomeAsUp的id永远都是android.R.id.home。
        if(actionBar!=null){
            actionBar.setDisplayHomeAsUpEnabled(true);
            actionBar.setHomeAsUpIndicator(R.drawable.ic_menu);
        }
        //将Call这个Item设置为默认选中。
//        navigationView.setCheckedItem(R.id.nav_call);
        //设置菜单项选中事件的监听器。
        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                drawerLayout.closeDrawer(GravityCompat.START);
                return true;
            }
        });
        FloatingActionButton fab=findViewById(R.id.floating_button);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Snackbar.make(v,"Data deleted",Snackbar.LENGTH_SHORT).setAction("Undo", new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Toast.makeText(MaterialDesignAct.this,"Data restored",Toast.LENGTH_SHORT).show();
                    }
                }).show();
            }
        });

        initList();
        RecyclerView recyclerView=findViewById(R.id.material_recycler_view);
        recyclerView.setLayoutManager(new GridLayoutManager(this,2));
        adapter=new FruitAdapter(fruitList);
        recyclerView.setAdapter(adapter);

        swipeRefreshLayout=findViewById(R.id.material_swipe_refresh);
        swipeRefreshLayout.setColorSchemeResources(R.color.colorPrimary);
        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                refreshList();
            }
        });

    }

    private void initList(){
        fruitList.clear();
        for(int i=0;i<50;i++){
            Random random=new Random();
            int index=random.nextInt(fruits.length);
            fruitList.add(fruits[index]);
        }
    }

    private void refreshList(){
        new Thread(new Runnable() {
            @Override
            public void run() {
                try{
                    Thread.sleep(2000);
                }catch (InterruptedException e){
                    e.printStackTrace();
                }
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        initList();
//                        adapter.notifyDataSetChanged();
                        adapter.notifyItemRangeChanged(0,50);
                        swipeRefreshLayout.setRefreshing(false);
                    }
                });
            }
        }).start();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.toolbar,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case android.R.id.home:
                drawerLayout.openDrawer(GravityCompat.START);
                break;
            case R.id.toolbar_back_up:
                Toast.makeText(this, "clicked backup", Toast.LENGTH_SHORT).show();
                break;
            case R.id.toolbar_delete:
                Toast.makeText(this,"clicked delete",Toast.LENGTH_SHORT).show();
                break;
            case R.id.toolbar_settings:
                Toast.makeText(this, "clicked settings", Toast.LENGTH_SHORT).show();
                break;
            default:
                break;
        }
        return true;
    }
}
