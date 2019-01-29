package com.example.newlife;

import android.os.Bundle;
import android.view.MenuItem;
import android.widget.Toast;

import com.google.android.material.navigation.NavigationView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class NavigationViewTestAct extends AppCompatActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_navigation_view);
        NavigationView navigationView=findViewById(R.id.navigationview_test);
        navigationView.setCheckedItem(R.id.navigation_test_call);
        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()){
                    case R.id.navigation_test_call:
                        Toast.makeText(NavigationViewTestAct.this, "call", Toast.LENGTH_SHORT).show();
                        break;
                    case R.id.navigation_test_friends:
                        Toast.makeText(NavigationViewTestAct.this, "friends", Toast.LENGTH_SHORT).show();
                        break;
                    default:
                }
                return true;
            }
        });
    }
}
