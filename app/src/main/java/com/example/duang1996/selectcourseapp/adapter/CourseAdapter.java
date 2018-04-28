package com.example.duang1996.selectcourseapp.adapter;


import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.TextView;

import com.example.duang1996.selectcourseapp.R;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;


/**
 * Created by duang1996 on 2018/4/28.
 */

public class CourseAdapter extends RecyclerView.Adapter<CourseAdapter.ViewHolder> {
    private List<Map<String, Object>> itemList;

    private OnItemClickListener click;
    private OnItemLongClickListener Lclick;


    static class ViewHolder extends RecyclerView.ViewHolder {
        TextView name;
        TextView teacher;
        TextView point;
        TextView type;
        TextView time;

        public ViewHolder(View view) {
            super(view);
            name = view.findViewById(R.id.name);
            teacher = view.findViewById(R.id.teacher);
            point = view.findViewById(R.id.point);
            type = view.findViewById(R.id.type);
            time = view.findViewById(R.id.time);
        }
    }

    public CourseAdapter(List<Map<String, Object>> list) {
        itemList = new ArrayList<>();
        itemList = list;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_select_course, parent, false);
        ViewHolder holder = new ViewHolder(view);

        return holder;
    }
    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {
        Map<String, Object>item = itemList.get(position);
        holder.name.setText(item.get("name").toString());
        holder.teacher.setText(item.get("teacher").toString());
        holder.point.setText(item.get("point").toString());
        holder.type.setText(item.get("type").toString());
        holder.time.setText(item.get("time").toString());

        if(click != null) {
            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    click.onItemClick(holder.getAdapterPosition());
                }
            });
        }
        if(Lclick != null) {
            holder.itemView.setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View v) {
                    Lclick.onItemLongClick(holder.getAdapterPosition());
                    return true;
                }
            });
        }
    }
    @Override
    public int getItemCount() {
        return itemList.size();
    }

    public interface OnItemClickListener{
        void onItemClick(int position);
    }
    public interface OnItemLongClickListener{
        void onItemLongClick(int position);
    }

    public void setOnItemClickListener(OnItemClickListener click) {
        this.click = click;
    }
    public void setOnItemLongClickListener(OnItemLongClickListener Lclick) {
        this.Lclick = Lclick;
    }

}