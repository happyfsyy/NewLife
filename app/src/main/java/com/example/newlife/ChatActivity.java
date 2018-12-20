package com.example.newlife;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.adapter.ChatAdapter;
import com.example.bean.Message;
import com.example.listener.OnItemClickListener;

import java.util.ArrayList;
import java.util.List;

public class ChatActivity extends BaseActivity {
    private RecyclerView.LayoutManager mLayoutManager;
    private List<Message> mList;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.chat_main);
        final RecyclerView recyclerView=findViewById(R.id.chat_recycler_view);
        mLayoutManager=new LinearLayoutManager(this);
        recyclerView.setLayoutManager(mLayoutManager);

        initList();
        final ChatAdapter adapter=new ChatAdapter(mList,R.layout.chat_item);
        adapter.setOnItemClickListener(new OnItemClickListener() {
            @Override
            public void onItemClick(RecyclerView parent, View view, int position) {
                Toast.makeText(ChatActivity.this,mList.get(position).getContent(),Toast.LENGTH_SHORT).show();
            }
        });
        recyclerView.setAdapter(adapter);

        Button send=findViewById(R.id.chat_send_button);
        final EditText contentEdit=findViewById(R.id.chat_edit_text);
        send.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(contentEdit.getText()!=null&&contentEdit.getText().toString()!=""){
                    Message message=new Message(Message.SEND_TYPE,contentEdit.getText().toString());
                    mList.add(message);
                    adapter.notifyItemInserted(mList.size()-1);
                    recyclerView.scrollToPosition(mList.size()-1);
                    contentEdit.setText("");
                }
            }
        });
    }
    private void initList(){
        mList=new ArrayList<>();
        Message message1=new Message(Message.RECEIVE_TYPE,"hello");
        Message message2=new Message(Message.SEND_TYPE,"hi你妹");
        Message message3=new Message(Message.RECEIVE_TYPE,"why so rude");
        mList.add(message1);mList.add(message2);mList.add(message3);
    }
}
