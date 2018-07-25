package com.example.lily;

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
 * on 2018/6/4.
 */

public class MainAdapter  extends RecyclerView.Adapter<MainAdapter.viewHolder>{

    private Context context;
    private List<String> list;
    private OnItemClickListener listener;

    public MainAdapter(Context context,OnItemClickListener listener) {
        this.context = context;
        this.listener = listener;
        list = new ArrayList<>();
    }

    public void addList(List<String> list){
        this.list = list;
        notifyDataSetChanged();
    }

    @Override
    public viewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_main,parent,false);
        return new viewHolder(view);
    }

    @Override
    public void onBindViewHolder(viewHolder holder, final int position) {
        holder.tv.setText(list.get(position));
        holder.tv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listener.onItemClick(position);
            }
        });
    }



    @Override
    public int getItemCount() {
        return list.size();
    }

    class viewHolder extends RecyclerView.ViewHolder{
        TextView tv;

        public viewHolder(View itemView) {
            super(itemView);
            tv = itemView.findViewById(R.id.tv_main_item);
        }
    }

    public interface OnItemClickListener{
        void onItemClick(int pos);
    }
}
