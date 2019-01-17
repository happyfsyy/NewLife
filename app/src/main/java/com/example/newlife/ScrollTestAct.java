package com.example.newlife;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class ScrollTestAct extends AppCompatActivity {
    private Button testButton;
    private TextView textView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_scroll_test);
        testButton=findViewById(R.id.scroll_test_button);
        textView=findViewById(R.id.scroll_text_view);
        testButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                textView.scrollBy(-20,-20);
                ((View)textView.getParent()).scrollBy(-20,-20);
            }
        });

    }
}
