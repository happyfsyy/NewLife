package com.example.newlife;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import com.example.utils.LogUtils;

public class DispatchAct extends AppCompatActivity {

    private static final String TAG = "DispatchAct";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dispatch);

        InterceptLayout myLayout=findViewById(R.id.dispatch_layout);
        myLayout.setOnTouchListener(new View.OnTouchListener() {
            /**执行myLayout的dispatchTouchEvent()方法：
             * 结合郭神的博客看，如果onTouch()返回false，MyLayout并不是Button，因为clickable=false，那么在View的onTouchEvent()中，
             * 所以肯定直接返回false，整个dispatchTouchEvent()方法就会返回false，就不会触发下一个action。
             * 因为当dispatchTouchEvent在进行事件分发的时候，只有前一个action返回true，才会触发后一个action。
             *
             * @return 返回true的话，就可以出现action_down和action_up，如果返回false，只有action_down
             */
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                switch (event.getAction()){
                    case MotionEvent.ACTION_DOWN:
                        LogUtils.e(TAG,"MyLayout: ACTION_DOWN");
                        break;
                    case MotionEvent.ACTION_MOVE:
                        LogUtils.e(TAG,"MyLayout: ACTION_MOVE");
                        break;
                    case MotionEvent.ACTION_UP:
                        LogUtils.e(TAG,"MyLayout: ACTION_UP");
                        break;
                    default:
                        break;
                }
                return true;
            }
        });

        Button button1=findViewById(R.id.dispatch_button);
        button1.setOnTouchListener(new View.OnTouchListener() {
            //onTouch()返回true，就认为这个事件被onTouch消费掉了。
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                switch (event.getAction()){
                    case MotionEvent.ACTION_DOWN:
                        LogUtils.e(TAG,"button1: ACTION_DOWN");
                        break;
                    case MotionEvent.ACTION_MOVE:
                        LogUtils.e(TAG,"button1: ACTION_MOVE");
                        break;
                    case MotionEvent.ACTION_UP:
                        LogUtils.e(TAG,"button1: ACTION_UP");
                        break;
                    default:
                        break;
                }
                return false;
            }
        });
        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                LogUtils.e(TAG,"button1: onClick");
            }
        });

        ImageView imageView=findViewById(R.id.dispatch_image);

        imageView.setOnTouchListener(new View.OnTouchListener() {
            /**
             *
             * @return 返回false，不仅会执行imageview.onTouch，同时因为imageView的dispatchTouchEvent()为false，所以，不会直接return。
             * 会继续向下执行，执行myLayout的onTouch。返回true，就不会继续向下执行，同时代表事件被消费了。
             */
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                switch (event.getAction()){
                    case MotionEvent.ACTION_DOWN:
                        LogUtils.e(TAG,"image: ACTION_DOWN");
                        break;
                    case MotionEvent.ACTION_MOVE:
                        LogUtils.e(TAG,"image: ACTION_MOVE");
                        break;
                    case MotionEvent.ACTION_UP:
                        LogUtils.e(TAG,"image: ACTION_UP");
                        break;
                    default:
                        break;
                }
                return true;
            }
        });
    }
}
