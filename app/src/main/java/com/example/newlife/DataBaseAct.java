package com.example.newlife;

import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.bean.BookSerializable;
import com.example.bean.CategoryParcelable;

import java.net.FileNameMap;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class DataBaseAct extends AppCompatActivity {
    private MyDataBaseHelper dbHelper;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_data_base);

        dbHelper=new MyDataBaseHelper(DataBaseAct.this,"BookStore.db",null,2);

        Button create=findViewById(R.id.create_database);
        create.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dbHelper.getWritableDatabase();
            }
        });

        Button add=findViewById(R.id.add_data);
        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SQLiteDatabase db=dbHelper.getWritableDatabase();
//                ContentValues values=new ContentValues();
//                values.put("author","ale");
//                values.put("price",18.8);
//                values.put("pages",200);
//                values.put("name","thinking in java");
//                db.insert("Book",null,values);
//                values.clear();
//                values.put("author","ale2");
//                values.put("price",19.9);
//                values.put("pages",250);
//                values.put("name","thinking in java2");
//                db.insert("Book",null,values);
//                values.clear();
//                values.put("author","ale3");
//                values.put("price",29.9);
//                values.put("pages",450);
//                values.put("name","thinking in java3");
//                db.insert("Book",null,values);
                ContentValues values=new ContentValues();
                values.put("category_name","hello1");
                values.put("category_code",111);
                db.insert("Category",null,values);
                Toast.makeText(DataBaseAct.this,"add succeed",Toast.LENGTH_SHORT).show();
            }
        });

        Button update=findViewById(R.id.update_data);
        update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SQLiteDatabase db=dbHelper.getWritableDatabase();
                ContentValues values=new ContentValues();
                values.put("price","22.9");
                db.update("Book",values,"author=?",new String[]{"ale2"});
                Toast.makeText(DataBaseAct.this,"update succeed",Toast.LENGTH_SHORT).show();
            }
        });

        Button delete=findViewById(R.id.delete_data);
        delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SQLiteDatabase db=dbHelper.getWritableDatabase();
                db.delete("Book","pages>?",new String[]{"300"});
                Toast.makeText(DataBaseAct.this,"delete succeed",Toast.LENGTH_SHORT).show();
            }
        });

        Button query=findViewById(R.id.query_data);
        query.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SQLiteDatabase db=dbHelper.getWritableDatabase();
                Cursor cursor=db.query("Book",new String[]{"name,price"},"pages<?",new String[]{"8000"},null,null,"price",null);
                if(cursor.moveToFirst()){
                    do{
                        String name=cursor.getString(cursor.getColumnIndex("name"));
                        float price=cursor.getFloat(cursor.getColumnIndex("price"));
                        Log.e("DataBaseAct",">>>>>>>"+name+">>>>>>"+price+">>>>>>");
                    }while(cursor.moveToNext());
                }
                cursor.close();

            }
        });

        Button replace=findViewById(R.id.replace_data);
        replace.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SQLiteDatabase db=dbHelper.getWritableDatabase();
                db.beginTransaction();
                try{
                    db.delete("Book",null,null);
//                    if(true) {
//                        throw new NullPointerException();
//                    }
                    ContentValues values=new ContentValues();
                    values.put("author","ale8");
                    values.put("price",88.8);
                    values.put("pages",1000);
                    values.put("name","thinking in java8");
                    db.insert("Book",null,values);
                    db.setTransactionSuccessful();
                }catch (Exception e){
                    e.printStackTrace();
                }finally {
                    db.endTransaction();
                }
            }
        });

        Button transfer=findViewById(R.id.transfer_data);
        transfer.setOnClickListener(new View.OnClickListener() {
            /**
             * 只是为了测试传输序列化数据
             * @param v
             */
            @Override
            public void onClick(View v) {
                SQLiteDatabase db=dbHelper.getReadableDatabase();
                Cursor cursor=db.query("Book",null,null,null,null,null,null);
                if(cursor.moveToNext()){
                    BookSerializable bookSerializable=new BookSerializable();
                    String name=cursor.getString(cursor.getColumnIndex("name"));
                    float prices=cursor.getFloat(cursor.getColumnIndex("price"));
                    bookSerializable.setName(name);
                    bookSerializable.setPrice(prices);
                    Intent intent=new Intent(DataBaseAct.this,ReceiveDataAct.class);
                    Bundle bundle=new Bundle();
                    bundle.putSerializable("book",bookSerializable);
                    intent.putExtras(bundle);
                    startActivity(intent);
                }
            }
        });

        Button transfer2=findViewById(R.id.transfer_data2);
        transfer2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SQLiteDatabase db=dbHelper.getReadableDatabase();
                Cursor cursor=db.query("Category",null,null,null,null,null,null);
                if(cursor.moveToNext()){
                    String name=cursor.getString(cursor.getColumnIndex("category_name"));
                    int code=cursor.getInt(cursor.getColumnIndex("category_code"));
                    CategoryParcelable categoryParcelable=new CategoryParcelable();
                    categoryParcelable.setCategoryName(name);
                    categoryParcelable.setCategoryCode(code);
                    Intent intent=new Intent(DataBaseAct.this,ReceiveDataAct.class);
                    intent.putExtra("category",categoryParcelable);
                    startActivity(intent);
                }
            }
        });
    }
}
