package com.example.newlife;

import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.example.annotation.Apple;
import com.example.annotation.FruitInfoUtil;
import com.example.utils.HookClickListenerUtils;
import com.example.utils.LogUtils;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.Random;
import java.util.concurrent.CopyOnWriteArrayList;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class JavaTestAct extends AppCompatActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_test);
        Button test=findViewById(R.id.test_button);
        test.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                LogUtils.e("测试第一个按钮");
//                FruitInfoUtil.getFruitInfo(Apple.class);
                testIterator();
            }
        });
        Button test2=findViewById(R.id.test_button2);
        test2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                LogUtils.e("测试第二个按钮");
            }
        });

        Box<String> stringBox=new Box<>("aa");
        Box<Integer> integerBox=new Box<>(123);
        if(stringBox.getClass().equals(integerBox.getClass())){
            LogUtils.e("这俩的类型相同");
        }
        Box doubleBox=new Box(55.55);
        Box booleanBox=new Box(false);
        LogUtils.e(stringBox.getData());
        LogUtils.e(integerBox.getData()+"");
        LogUtils.e(doubleBox.getData().toString());
        LogUtils.e(booleanBox.getData().toString());

    }

    @Override
    public void onAttachedToWindow() {
        super.onAttachedToWindow();
        ViewGroup decorView=(ViewGroup)getWindow().getDecorView();
        HookClickListenerUtils.getInstance().hookDecorViewClick(decorView);
    }
    private void testIterator(){
        List<String> list1=new ArrayList<>();
        list1.add("list1");
        list1.add("list2");
        list1.add("list3");
        for(Iterator<String> iterator=list1.iterator();iterator.hasNext();){
            String temp=iterator.next();
            if(temp.equals("list2")){
                iterator.remove();
            }
        }
        for(int i=0;i<list1.size();i++){
            LogUtils.e(list1.get(i));
        }
    }
    private void testList(){
        List<String> list=new ArrayList<>();
        List<String> synList= Collections.synchronizedList(list);

        List<String> list2=new CopyOnWriteArrayList<>();
    }
}
class Box<T>{
    private T data;
    public Box(T data){
        this.data=data;
    }
    public T getData(){
        return data;
    }
}
interface Generator<T>{
    public T next();
}
class PenGenerator<T> implements Generator<T>{
    @Override
    public T next() {
        return null;
    }
}
class FruitGenerator implements Generator<String>{
    String[] fruits=new String[]{"apple","banana","pear"};
    @Override
    public String next() {
        Random random=new Random();
        return fruits[random.nextInt(3)];
    }
}
class GenericTest{
    //这是一个泛型类
    public static class Generic<T>{
        private T key;
        public Generic(T key){
            this.key=key;
        }
        //这只是类中一个普通的成员方法，不过他的返回值是在声明泛型类已经声明过的泛型。
        //所以在这个方法中才可以继续使用T这个泛型。
        public T getKey(){
            return key;
        }
        //在泛型类中声明了一个泛型方法，使用泛型E，这种泛型E可以为任意类型。可以与T相同，也可以不同
        public <E> void show(E t){
            LogUtils.e(t.toString());
        }
        //在泛型类中声明了一个泛型方法，使用泛型T，注意这个T是一种全新的类型，可以与泛型类中类中声明的T不是同一种类型
        public <T> void show2(T t){
            LogUtils.e(t.toString());
        }

    }

    /**
     * 这才是一个真正的泛型方法。
     * 首先在public与返回值之间<T>必不可少，这表明这是一个泛型方法，
     * 并且声明了一个泛型T，这个T可以出现在这个泛型方法内部的任意位置。
     * 泛型的数量也可以为任意多个：
     * 如：public <T,K> K showKeyName(Generic<T> container){}
     *
     */
    public <T> T showKeyName(Generic<T> container){
        System.out.println("container key:"+container.getKey());
        T test=container.getKey();
        return  test;
    }
    //这也不是一个泛型方法，这是一个普通的方法，只是使用了Generic<Number>这个泛型类做形参而已
    public void showKeyValue1(Generic<String> obj){
        LogUtils.e(obj.getKey());
    }
    //这也不是一个泛型方法，这是一个普通的方法，只不过使用了泛型通配符？
    //同时这也印证了泛型通配符章节所描述的？是一种类型实参，可以看做为Number等所有类的父类
    public void showKeyValue2(Generic<?> obj){
        LogUtils.e(obj.getKey()+"");
    }
    public void showKeyValue(Generic<? extends Number> obj){
        LogUtils.e(obj.getKey()+"");
    }
    public <T extends Number> T showKeyName2(Generic<T> container){
        T test=container.getKey();
        return  test;
    }
    public static void main(String[] args){
        GenericTest test=new GenericTest();
        Generic<Float> generic1=new Generic<>(2.2f);
        test.showKeyValue(generic1);
    }

}