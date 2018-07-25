package com.example.lily.animationpractice.view;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.example.lily.animationpractice.R;

/**
 * Created by ljq
 * on 2018/5/30.
 */

public class ViewItemActivity  extends AppCompatActivity{


    RecyclerView recyclerView;
    ViewItemAdapter adapter;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_viewitem);
        recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        adapter = new ViewItemAdapter(this);
        recyclerView.setAdapter(adapter);
    }
}
