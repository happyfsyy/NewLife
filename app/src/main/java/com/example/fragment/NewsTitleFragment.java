package com.example.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.adapter.BaseAdapter;
import com.example.bean.Day;
import com.example.bean.News;
import com.example.listener.OnItemClickListener;
import com.example.newlife.NewsContentActivity;
import com.example.newlife.R;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

public class NewsTitleFragment extends Fragment {
    private View mainLayout;
    private RecyclerView recyclerView;
    private List<News> list;
    private boolean isTwoPane;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mainLayout=inflater.inflate(R.layout.news_title_layout,container,false);
        recyclerView=mainLayout.findViewById(R.id.news_title_recycler_view);
        LinearLayoutManager manager=new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(manager);

        initList();
        NewsTitleAdapter adapter=new NewsTitleAdapter(list,R.layout.news_title_item);
        adapter.setOnItemClickListener(new OnItemClickListener() {
            @Override
            public void onItemClick(RecyclerView parent, View view, int position) {
                String title=list.get(position).getTitle();
                String content=list.get(position).getContent();
                if(isTwoPane){
                    NewsContentFragment fragment=(NewsContentFragment)getFragmentManager().findFragmentById(R.id.news_content_fragment);
                    fragment.setData(title,content);
                }else{
                    NewsContentActivity.startAct(getActivity(),title,content);
                }
            }
        });
        recyclerView.setAdapter(adapter);
        return mainLayout;
    }

    private void initList(){
        list=new ArrayList<>();
        for(int i=0;i<50;i++){
            News news=new News();
            news.setTitle("This is Tile"+i);
            news.setContent(getRandomLengthContent("This is Content"+i));
            list.add(news);
        }
    }
    private String getRandomLengthContent(String content){
        Random random=new Random();
        int nums=random.nextInt(20)+1;
        StringBuilder builder=new StringBuilder();
        for(int i=0;i<nums;i++){
            builder.append(content);
        }
        return builder.toString();
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        View contentLayout=getActivity().findViewById(R.id.news_content_layout);
        if(contentLayout==null){
            isTwoPane=false;
        }else{
            isTwoPane=true;
        }
    }

    class NewsTitleAdapter extends BaseAdapter<News>{
        class NewsTitleViewHolder extends BaseViewHolder{
            TextView titleText;

            NewsTitleViewHolder(@NonNull View itemView) {
                super(itemView);
                titleText=itemView.findViewById(R.id.news_title_text);
            }
        }
        NewsTitleAdapter(List<News> list, int itemResId) {
            super(list, itemResId);
        }

        @Override
        protected BaseViewHolder onCreate(View itemView) {
            return  new NewsTitleViewHolder(itemView);
        }

        @Override
        protected void onBind(RecyclerView.ViewHolder holder, int position) {
            ((NewsTitleViewHolder)holder).titleText.setText(mList.get(position).getTitle());
        }
    }

}

