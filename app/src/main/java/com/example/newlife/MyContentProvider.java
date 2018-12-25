package com.example.newlife;

import android.content.ContentProvider;
import android.content.ContentUris;
import android.content.ContentValues;
import android.content.UriMatcher;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import android.util.Log;

import java.security.KeyStore;

import javax.xml.transform.URIResolver;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

public class MyContentProvider extends ContentProvider {
    private MyDataBaseHelper dbHelper;
    private static UriMatcher uriMatcher;
    public static final String AUTHORITY="com.example.newlife.provider";
    public static final int BOOK_DIR=1;
    public static final int BOOK_ITEM=2;
    public static final int CATEGORY_DIR=3;
    public static final int CATEGORY_ITEM=4;

    static {
        uriMatcher=new UriMatcher(UriMatcher.NO_MATCH);
        uriMatcher.addURI(AUTHORITY,"book",BOOK_DIR);
        uriMatcher.addURI(AUTHORITY,"book/#",BOOK_ITEM);
        uriMatcher.addURI(AUTHORITY,"category",CATEGORY_DIR);
        uriMatcher.addURI(AUTHORITY,"category/#",CATEGORY_ITEM);
    }
    @Override
    public boolean onCreate() {
        dbHelper=new MyDataBaseHelper(getContext(),"BookStore.db",null,2);
        return true;
    }

    @Nullable
    @Override
    public Cursor query(@NonNull Uri uri, @Nullable String[] projection, @Nullable String selection, @Nullable String[] selectionArgs, @Nullable String sortOrder) {
        SQLiteDatabase db=dbHelper.getReadableDatabase();
        Cursor cursor=null;
        Log.e("MyContentProvider",">>>>>>>>>>"+uri.getClass().toString());
        switch (uriMatcher.match(uri)){
            case BOOK_DIR:
                cursor=db.query("Book",projection,selection,selectionArgs,null,null,sortOrder);
                break;
            case BOOK_ITEM:
                String bookId=uri.getPathSegments().get(1);
                cursor=db.query("Book",projection,"id=?",new String[]{bookId},null,null,sortOrder);
                break;
            case CATEGORY_DIR:
                cursor=db.query("Category",projection,selection,selectionArgs,null,null,sortOrder);
                break;
            case CATEGORY_ITEM:
                String category_id=uri.getPathSegments().get(1);
                cursor=db.query("Category",projection,"id=?",new String[]{},null,null,sortOrder);
                break;
             default:
                 break;
        }
        return cursor;
    }



    @Nullable
    @Override
    public Uri insert(@NonNull Uri uri, @Nullable ContentValues values) {
        SQLiteDatabase db=dbHelper.getWritableDatabase();
        Uri returnUri=null;
        switch (uriMatcher.match(uri)){
            case BOOK_DIR:
            case BOOK_ITEM:
                long newBookId=db.insert("Book",null,values);
                returnUri=Uri.parse("content://"+AUTHORITY+"/book/"+newBookId);
                break;
            case CATEGORY_DIR:
            case CATEGORY_ITEM:
                long newCategoryId=db.insert("Category",null,values);
                returnUri=Uri.parse("content://"+AUTHORITY+"/category/"+newCategoryId);
                break;
            default:
                break;
        }
        return returnUri;
    }

    @Override
    public int delete(@NonNull Uri uri, @Nullable String selection, @Nullable String[] selectionArgs) {
        SQLiteDatabase db=dbHelper.getWritableDatabase();
        int deletedRows=0;
        switch (uriMatcher.match(uri)){
            case BOOK_DIR:
                deletedRows=db.delete("Book",selection,selectionArgs);
                break;
            case BOOK_ITEM:
                String bookId=uri.getPathSegments().get(1);
                deletedRows=db.delete("Book","id=?",new String[]{bookId});
                break;
            case CATEGORY_DIR:
                deletedRows=db.delete("Category",selection,selectionArgs);
                break;
            case CATEGORY_ITEM:
                String category_id=uri.getPathSegments().get(1);
                deletedRows=db.delete("Category","id=?",new String[]{category_id});
                break;
             default:
                 break;
        }
        return deletedRows;
    }

    @Override
    public int update(@NonNull Uri uri, @Nullable ContentValues values, @Nullable String selection, @Nullable String[] selectionArgs) {
        SQLiteDatabase db=dbHelper.getWritableDatabase();
        int updatedRows=0;
        switch (uriMatcher.match(uri)){
            case BOOK_DIR:
                updatedRows=db.update("Book",values,selection,selectionArgs);
                break;
            case BOOK_ITEM:
                String bookId=uri.getPathSegments().get(1);
                updatedRows=db.update("Book",values,"id=?",new String[]{bookId});
                break;
            case CATEGORY_DIR:
                updatedRows=db.update("Category",values,selection,selectionArgs);
                break;
            case CATEGORY_ITEM:
                String category_id=uri.getPathSegments().get(1);
                updatedRows=db.update("Category",values,"id=?",new String[]{});
                break;
            default:
                break;
        }
        return updatedRows;
    }

    @Nullable
    @Override
    public String getType(@NonNull Uri uri) {
        switch (uriMatcher.match(uri)){
            case BOOK_DIR:
                return "vnd.android.cursor.dir/vnd.com.example.newlife.provider.book";
            case BOOK_ITEM:
                return "vnd.android.cursor.item/vnd.com.example.newlife.provider.book";
            case CATEGORY_DIR:
                return "vnd.android.cursor.dir/vnd.com.example.newlife.provider.category";
            case CATEGORY_ITEM:
                return "vnd.android.cursor.item/vnd.com.example.newlife.provider.category";
        }
        return null;
    }
}
