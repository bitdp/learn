package com.mvp.learningapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.mvp.learningapp.thread.ThreadActivity;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends BaseActivity {
    private RecyclerView mRecyclerView;
    private MainAdapter mainAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mRecyclerView = (RecyclerView) findViewById(R.id.recycle_view);
        List<String> data = getDataList();
        GridLayoutManager manager = new GridLayoutManager(MainActivity.this,4);
        mRecyclerView.setLayoutManager(manager);
        mRecyclerView.addItemDecoration(new DividerGridItemDecoration(MainActivity.this));
        mainAdapter = new MainAdapter(MainActivity.this,data);
        mRecyclerView.setAdapter(mainAdapter);
        mainAdapter.setOnItemClickListener(new MainAdapter.IRecyclerItemClickListener(){
            @Override
            public void onItemClick(int position) {
                switch (position){
                    case 0:
                        Intent threadIntent = new Intent(MainActivity.this, ThreadActivity.class);
                        startActivity(threadIntent);
                        break;
                }
            }
        });
    }

    private List<String> getDataList() {
        List<String> list = new ArrayList<>();
        list.add("线程池");
        return list;
    }
}
