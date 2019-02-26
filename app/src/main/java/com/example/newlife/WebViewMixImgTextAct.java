package com.example.newlife;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.webkit.WebView;
import android.widget.Toast;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

/**
 * 参考链接：https://blog.csdn.net/u013133387/article/details/86526155
 * https://blog.csdn.net/xiaoxin_android/article/details/51194770
 */
public class WebViewMixImgTextAct extends AppCompatActivity {
    private WebView webView;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_webview_mix_img_text);
        webView=findViewById(R.id.img_text_webview);
        String str = "\"<p>据多家美媒消息，五年前的今天，也就是2012年12月6日，湖" +
                "人名宿科比-布莱恩特迎来个人生涯一个非常大的里程碑!他成为NBA历史上" +
                "第5位总得分达到30000分的球员，也成了历史最年轻的30000分先生。</p>" +
                "<p><img src=\"http://p4.qhimg.com/t0143c3caa0f210471d.jpg?size=960" +
                "x960\"\" class=\"mCS_img_loaded\"></p><p>在湖人以103-87战胜黄蜂的比赛" +
                "中，科比出战33分钟，17投10中，得到29分6篮板4助攻。其中，第二节比赛他用" +
                "一记抛投让自己的职业生涯总得分达到30000分。</p><p>科比在这一天是34岁104" +
                "天，超越了张伯伦(35岁179天)成为NBA历史上最年轻的30000分先生。</p><p>" +
                "<img src=\"http://p6.qhimg.com/t018adb2d64db452679.jpg?size=1024x576\"" +
                " class=\"mCS_img_loaded\"></p><p>詹姆斯目前32岁，如无意外的话，他在本赛" +
                "季(生涯第15个赛季)就能超越科比，成为NBA历史最年轻的30000分先生。</p><p>" +
                "<img src=\"http://p2.qhimg.com/t0111fe70b44cb3e393.jpg?size=1024x770\"" +
                " class=\"mCS_img_loaded\"></p><p><img src=\"http://p0.qhimg.com/t011f7" +
                "a164e5cd0d6f1.jpg?size=858x572\" class=\"mCS_img_loaded\"></p><p>返回搜" +
                "狐，查看更多</p><p>责任编辑:</p>\"";
        Document parse= Jsoup.parse(str);
        Elements imgs=parse.getElementsByTag("img");
        if(!imgs.isEmpty()){
            for(Element e:imgs){
                imgs.attr("width","100%");
                imgs.attr("height","auto");
            }
        }
        String content=parse.toString();
        webView.loadDataWithBaseURL(null,content,"text/html","utf-8",null);
        initListener();
    }
    private View.OnTouchListener onTouchListener=new View.OnTouchListener() {
        @Override
        public boolean onTouch(View v, MotionEvent event) {
            float x=0,y=0;
            switch (event.getAction()){
                case MotionEvent.ACTION_DOWN:
                    x=(int)event.getRawX();
                    y=(int)event.getRawY();
                    break;
                case MotionEvent.ACTION_UP:
                    if((event.getEventTime()-event.getDownTime())<100){
                        if(x-event.getX()<5&&y-event.getY()<5){
                            WebView.HitTestResult hitTestResult=((WebView)v).getHitTestResult();
                            if(hitTestResult.getType()==WebView.HitTestResult.IMAGE_TYPE
                                ||hitTestResult.getType()==WebView.HitTestResult.IMAGE_ANCHOR_TYPE
                                ||hitTestResult.getType()==WebView.HitTestResult.SRC_IMAGE_ANCHOR_TYPE){
                                Toast.makeText(WebViewMixImgTextAct.this, "保存这个图片"+hitTestResult.getExtra(), Toast.LENGTH_SHORT).show();
                            }
                        }
                    }
                    break;
                default:
                    break;
            }
            return false;
        }
    };
    private void initListener(){
        webView.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                WebView.HitTestResult hitTestResult=((WebView)v).getHitTestResult();
                if(hitTestResult.getType()==WebView.HitTestResult.IMAGE_TYPE
                    ||hitTestResult.getType()==WebView.HitTestResult.IMAGE_ANCHOR_TYPE
                    ||hitTestResult.getType()==WebView.HitTestResult.SRC_IMAGE_ANCHOR_TYPE){
                    Toast.makeText(WebViewMixImgTextAct.this, "图片地址"+hitTestResult.getExtra(), Toast.LENGTH_SHORT).show();
                    Uri uri=Uri.parse(hitTestResult.getExtra());
                    Intent intent=new Intent(Intent.ACTION_VIEW,uri);
                    startActivity(intent);
                }
                return true;
            }
        });
        webView.setOnTouchListener(onTouchListener);
    }
}
