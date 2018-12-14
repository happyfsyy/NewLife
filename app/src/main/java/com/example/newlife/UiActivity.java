package com.example.newlife;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.util.AttributeSet;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import java.util.jar.Attributes;

public class UiActivity extends BaseActivity {

    private TextView textView1;
    private Button button1;
    private EditText editText1;
    private ImageView imageView1;
    private ProgressBar progressBar1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ui);

        textView1=findViewById(R.id.ui_textView1);
        button1=findViewById(R.id.ui_button1);
        editText1=findViewById(R.id.ui_editText1);
        imageView1=findViewById(R.id.ui_imageView1);
        progressBar1=findViewById(R.id.ui_progressBar1);

        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                Toast.makeText(UiActivity.this,editText1.getText().toString(),Toast.LENGTH_SHORT).show();
//                imageView1.setImageResource(R.drawable.b08);

//                if(progressBar1.getVisibility()==View.GONE){
//                    progressBar1.setVisibility(View.VISIBLE);
//                }else{
//                    progressBar1.setVisibility(View.GONE);
//                }
//                int progress=progressBar1.getProgress();
//                progress=progress+10;
//                progressBar1.setProgress(progress);

                AlertDialog.Builder builder=new AlertDialog.Builder(UiActivity.this);
                builder.setTitle("hello");
                builder.setMessage("Hello2");
                builder.setCancelable(false);
                builder.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Toast.makeText(UiActivity.this, "You clicked Ok", Toast.LENGTH_SHORT).show();
                    }
                });
                builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Toast.makeText(UiActivity.this, "You clicked Cancel", Toast.LENGTH_SHORT).show();
                    }
                });
                builder.show();
            }
        });
    }
}
