package com.example.lily.animationpractice;

import android.content.Intent;
import android.graphics.Canvas;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.example.lily.MainAdapter;
import com.example.lily.animationpractice.property.BezierActivity;
import com.example.lily.animationpractice.property.LoadingPage;
import com.example.lily.animationpractice.property.MyDesignActivity;
import com.example.lily.animationpractice.property.PropertyAnimationActivity;
import com.example.lily.animationpractice.frame.FrameAnimationActivity;
import com.example.lily.animationpractice.property.ValueAnimationActivity;
import com.example.lily.animationpractice.user_defined.CircleActivity;
import com.example.lily.animationpractice.view.ViewAnimationActivity;
import com.example.lily.animationpractice.view.ViewItemActivity;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements MainAdapter.OnItemClickListener {


    RecyclerView recyclerView;
    MainAdapter adapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        adapter = new MainAdapter(this,this);
        recyclerView.setAdapter(adapter);
        initdata();

    }

     private void initdata(){
         List<String> list = new ArrayList<>();
         list.add("View动画");
         list.add("ViewGroupItem动画");
         list.add("帧动画");
         list.add("属性动画");
         list.add("抛物线");
         list.add("自定义圆形");
         list.add("自定义图加动画");
         list.add("loading");
         list.add("Bezeier");
         adapter.addList(list);
     }

    @Override
    public void onItemClick(int pos) {
         Intent intent = new Intent();
        Class<?> cls = ViewItemActivity.class;
         switch (pos){
             case 0 :
                 cls = ViewAnimationActivity.class;
                 break;
             case 1 :
                 cls = ViewItemActivity.class;
                 break;
             case 2 :
                 cls = FrameAnimationActivity.class;
                 break;
             case 3 :
                 cls = PropertyAnimationActivity.class;
                 break;
             case 4 :
                 cls = ValueAnimationActivity.class;
                 break;
             case 5:
                 cls = CircleActivity.class;
                 break;
             case 6 :
                 cls = MyDesignActivity.class;
                 break;
             case 7 :
                 cls = LoadingPage.class;
                 break;
                 case 8:
                     cls = BezierActivity.class;

                 break;
                 default:
                     break;
         }
         intent.setClass(this,cls);
         startActivity(intent);

    }
}
