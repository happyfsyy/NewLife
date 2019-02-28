package com.example.newlife;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.example.utils.LogUtils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class RegexTestAct extends AppCompatActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test);
        Button button=findViewById(R.id.test);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                match();
                matchQQMail();
            }
        });
    }
    private void match(){
        String pathStr="<img src=\""+"hello"+"\"/>";
        String patternStr2="[/\\/.]*";
        LogUtils.e(pathStr);
        String patternStr="<img src=\"([/\\w\\W.]*)\"\\s*/>";
        Pattern pattern=Pattern.compile(patternStr2);
        Matcher matcher=pattern.matcher(pathStr);
        while(matcher.find()){
            String path=matcher.group(1);
            LogUtils.e(path);
        }
    }
    private void matchQQMail(){
        String patternStr="[1-9]\\d{7,10}@qq\\.com";
        String s="877105914@qq.com";
        LogUtils.e("matchQQMail",String.valueOf(s.matches(patternStr)));
    }
}
