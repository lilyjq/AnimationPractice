package com.example.lily.animationpractice.view;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.lily.animationpractice.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by ljq
 * on 2018/5/30.
 */

public class ViewItemAdapter extends RecyclerView.Adapter<ViewItemAdapter.ViewHolder>{

    private List<String> list;
    private Context context;

    public ViewItemAdapter(Context context){
        this.context = context;
        list = new ArrayList<>();
        for(int i=0;i<24;i++){
            list.add("TestData---->"+i+"<----");
        }

    }



    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(context).inflate(R.layout.item_viewitem,parent,false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.tv.setText(list.get(position));

    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder{

        TextView tv;
        public ViewHolder(View itemView) {
            super(itemView);
            tv = itemView.findViewById(R.id.tv_item);
        }
    }
}
