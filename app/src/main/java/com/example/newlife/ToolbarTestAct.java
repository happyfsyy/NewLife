package com.example.newlife;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.RadioGroup;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

public class ToolbarTestAct extends AppCompatActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_toolbar_test);
        Toolbar toolbar=findViewById(R.id.toolbar_test);
        toolbar.setTitle("");
        setSupportActionBar(toolbar);
//        ActionBar actionBar=getSupportActionBar();
//        actionBar.setDisplayShowTitleEnabled(false);

        RadioGroup radioGroup=findViewById(R.id.toolbar_test_radio_group);
        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                switch (checkedId){
                    case R.id.toolbar_test_radio_message:
                        Toast.makeText(ToolbarTestAct.this,"Message",Toast.LENGTH_SHORT).show();
                        break;
                    case R.id.toolbar_test_radio_phone:
                        Toast.makeText(ToolbarTestAct.this,"Phone",Toast.LENGTH_SHORT).show();
                        break;
                }
            }
        });

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        return super.onCreateOptionsMenu(menu);
//        getMenuInflater().inflate(R.menu.toolbar_test,menu);
//        return true;
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case android.R.id.home:
                finish();
            case R.id.toolbar_test_backup:
                Toast.makeText(this, "back_up", Toast.LENGTH_SHORT).show();
                break;
            case R.id.toolbar_test_delete:
                Toast.makeText(this, "delete", Toast.LENGTH_SHORT).show();
                break;
            case R.id.toolbar_test_settings:
                Toast.makeText(this, "settings", Toast.LENGTH_SHORT).show();
                break;
        }
        return true;
    }
}
