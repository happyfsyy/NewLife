package com.example.newlife;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Matrix;
import android.graphics.Typeface;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.text.Html;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.TextPaint;
import android.text.method.LinkMovementMethod;
import android.text.style.AbsoluteSizeSpan;
import android.text.style.BackgroundColorSpan;
import android.text.style.ClickableSpan;
import android.text.style.ForegroundColorSpan;
import android.text.style.ImageSpan;
import android.text.style.RelativeSizeSpan;
import android.text.style.StrikethroughSpan;
import android.text.style.StyleSpan;
import android.text.style.SubscriptSpan;
import android.text.style.SuperscriptSpan;
import android.text.style.TypefaceSpan;
import android.text.style.URLSpan;
import android.text.style.UnderlineSpan;
import android.util.DisplayMetrics;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.target.SimpleTarget;
import com.bumptech.glide.request.transition.Transition;

import java.util.regex.Matcher;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
/**
 * 参考链接：https://blog.csdn.net/snowdream86/article/details/6776629
 */
public class SpannableStringAct extends AppCompatActivity {
    private TextView textView;
    private SpannableString spannableString;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_spannable_string);
        textView=findViewById(R.id.spannable_string_text);
        spannableString=new SpannableString("字体测试字体大小一半两倍前景色背景色正常粗体斜体粗斜体下划线删除线x1x2电话邮件网站短信菜心地图X轴综合/bot");
        setSpannableString();
        Html.fromHtml("html code",new URLImageGetter(this,textView),null);
    }

    class URLImageGetter implements Html.ImageGetter{
        private Context context;
        private TextView textView;
        private int actX;
        private int actY;
        public URLImageGetter(Context context,TextView textView){
            this.context=context;
            this.textView=textView;
            DisplayMetrics metrics=context.getResources().getDisplayMetrics();
            actX=metrics.widthPixels-20;
            actY=metrics.heightPixels;
        }
        @Override
        public Drawable getDrawable(String source) {
            final URLDrawable urlDrawable=new URLDrawable();
            Glide.with(context).load(source).into(new SimpleTarget<Drawable>() {
                @Override
                public void onResourceReady(@NonNull Drawable resource, @Nullable Transition<? super Drawable> transition) {
                    BitmapDrawable bmd=(BitmapDrawable)resource;
                    Bitmap bitmap=bmd.getBitmap();
                    int x=bitmap.getWidth();
                    int y=bitmap.getHeight();
                    if(x>actX||y>actY){
                        Matrix matrix=new Matrix();
                        matrix.postScale((float)(actX*1.0/x),(float)(actX*1.0/x));
                        //长和宽放大缩小的比例
                        bitmap=Bitmap.createBitmap(bitmap,0,0,x,y,matrix,true);
                    }
                    urlDrawable.bitmap=bitmap;
                    urlDrawable.setBounds(0,0,bitmap.getWidth(),bitmap.getHeight());
                    textView.invalidate();
                    textView.setText(textView.getText());//解决图文重叠
                }
            });

            return urlDrawable;
        }
    }
    class URLDrawable extends BitmapDrawable{
        protected Bitmap bitmap;
        @Override
        public void draw(Canvas canvas) {
            if(bitmap!=null){
                canvas.drawBitmap(bitmap,0,0,getPaint());
            }
        }
    }
    private void setSpannableString(){
        spannableString.setSpan(new StyleSpan(Typeface.BOLD),0,1, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        spannableString.setSpan(new TypefaceSpan("serif"),2,4,Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);

        spannableString.setSpan(new AbsoluteSizeSpan(20,true),4,6,Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        spannableString.setSpan(new RelativeSizeSpan(2.0f),6,8,Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        spannableString.setSpan(new ForegroundColorSpan(Color.MAGENTA),12,15,Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        spannableString.setSpan(new BackgroundColorSpan(Color.CYAN),15,18,Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);

        spannableString.setSpan(new StyleSpan(Typeface.NORMAL),18,20,Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        spannableString.setSpan(new StyleSpan(Typeface.BOLD),20,22,Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        spannableString.setSpan(new StyleSpan(Typeface.ITALIC),22,24,Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        spannableString.setSpan(Typeface.BOLD_ITALIC,24,27,Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        spannableString.setSpan(new UnderlineSpan(),27,30,Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        spannableString.setSpan(new StrikethroughSpan(),30,33,Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        spannableString.setSpan(new SubscriptSpan(),34,35,Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        spannableString.setSpan(new SuperscriptSpan(),36,37,Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);

        spannableString.setSpan(new URLSpan("http://www.baidu.com"),41,43,Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        spannableString.setSpan(new URLSpan("geo:38.899533,-77.036476"),47,49,Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);

        Drawable drawable=getResources().getDrawable(R.drawable.nav_icon);
        drawable.setBounds(0,0,drawable.getIntrinsicWidth(),drawable.getIntrinsicHeight());
        spannableString.setSpan(new ImageSpan(drawable),53,57,Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        MyClickableSpan clickableSpan=new MyClickableSpan() {
            @Override
            public void onClick(@NonNull View widget) {
                Toast.makeText(SpannableStringAct.this, "你点击了啥", Toast.LENGTH_SHORT).show();
            }
        };
        spannableString.setSpan(clickableSpan,8,12,Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
//        spannableString.setSpan(new ForegroundColorSpan(Color.YELLOW),8,12,Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        textView.setText(spannableString);
        textView.setMovementMethod(LinkMovementMethod.getInstance());
    }
    abstract class MyClickableSpan extends ClickableSpan{
        MyClickableSpan() {
            super();
        }
        @Override
        public void updateDrawState(@NonNull TextPaint ds) {
            ds.setUnderlineText(false);
            ds.setColor(Color.BLUE);
        }
    }
}
