package com.example.newlife;

import android.content.Context;
import android.os.Bundle;
import android.text.TextUtils;
import android.widget.EditText;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class FileWriteReadAct extends AppCompatActivity {
    private EditText editText;
    private static String FILE_NAME="file_write_read";

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_file_write_read);
        editText=findViewById(R.id.file_edit_text);
        String restoreData=readFromFile(FILE_NAME);
        if(!TextUtils.isEmpty(restoreData)){
            editText.setText(restoreData);
            editText.setSelection(restoreData.length());
            Toast.makeText(this, "Restoring succeed", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        String text=editText.getText().toString();
        saveDataToFile(text);
    }

    private void saveDataToFile(String text){
        FileOutputStream out;
        BufferedWriter writer=null;
        try{
            out=openFileOutput(FILE_NAME,Context.MODE_PRIVATE);
            writer=new BufferedWriter(new OutputStreamWriter(out));
            writer.write(text);
        }catch (IOException e){
            e.printStackTrace();
        }finally {
            try{
                if(writer!=null){
                    writer.close();
                }
            }catch (IOException e){
                e.printStackTrace();
            }
        }
    }

    private String readFromFile(String file_name){
        FileInputStream input;
        BufferedReader reader=null;
        StringBuilder content=new StringBuilder();
        try{
            input=openFileInput(file_name);
            reader=new BufferedReader(new InputStreamReader(input));
            String line="";
            while((line=reader.readLine())!=null){
                content.append(line);
            }
        }catch (IOException e){
            e.printStackTrace();
        }finally {
            try{
                if(reader!=null){
                    reader.close();
                }
            }catch (IOException e){
                e.printStackTrace();
            }
        }
        return content.toString();
    }
}
