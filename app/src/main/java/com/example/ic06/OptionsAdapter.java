package com.example.ic06;

import android.content.Context;
import android.graphics.Color;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class OptionsAdapter extends RecyclerView.Adapter<OptionsAdapter.MyViewHolder> {
    Questions questions;
    public static InteractWithTriviaActivity interact;
    Context ctx;
    TriviaActivity obj=new TriviaActivity();
    int rowindex=-1;


    public OptionsAdapter(Questions questions,Context TriviaActivity) {
        this.questions = questions;
        this.ctx=TriviaActivity;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LinearLayout rv_layout = (LinearLayout) LayoutInflater.from(parent.getContext()).inflate(R.layout.recycler_item_view, parent, false);

        MyViewHolder viewHolder = new MyViewHolder(rv_layout);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, final int position) {
        interact=(InteractWithTriviaActivity)ctx;
        holder.tv_item.setText(questions.getChoices()[position]);
        holder.tv_item.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                rowindex=position;
                notifyDataSetChanged();
                interact.searchItem(position,questions);
            }
        });
        if(rowindex==position){
            holder.row_linearlayout.setBackgroundColor(Color.parseColor("#567845"));
            holder.tv_item.setTextColor(Color.parseColor("#ffffff"));
        }
        else
        {
            holder.row_linearlayout.setBackgroundColor(Color.parseColor("#ffffff"));
            holder.tv_item.setTextColor(Color.parseColor("#000000"));
        }
    }

    @Override
    public int getItemCount() {
        return questions.getChoices().length;
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        TextView tv_item;
        Questions questions;
        LinearLayout row_linearlayout;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            tv_item=itemView.findViewById(R.id.list_item);
            row_linearlayout=itemView.findViewById(R.id.layout_main);

        }
    }
    public interface InteractWithTriviaActivity{
        void searchItem(int position,Questions question);
    }

}
