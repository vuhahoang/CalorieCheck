package com.example.caloriecheck.CustomApdapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.caloriecheck.Model.Diary;
import com.example.caloriecheck.Activitiy.Notes;
import com.example.caloriecheck.R;

import java.util.ArrayList;

public class NoteAdapter extends RecyclerView.Adapter<NoteAdapter.ViewHolder> {
    ArrayList<Diary> diaries;
    Context context;



    public NoteAdapter(ArrayList<Diary> diaries, Context context) {
        setHasStableIds(true);
        this.diaries = diaries;
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull  ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_diary_day,parent,false);
        return new NoteAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull  NoteAdapter.ViewHolder holder, int position) {
       Diary diary = diaries.get(position);
        holder.day.setText(diary.getDay() + "/" + diary.getMonth());
        holder.notes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(context,Notes.class);
                i.putExtra("day",diary.getDay());
                i.putExtra("month",diary.getMonth());
                i.putExtra("dow",diary.getDow());
                i.putExtra("content",diary.getContent());
                i.putExtra("check",true);
                context.startActivity(i);
            }
        });

    }

    @Override
    public int getItemCount() {
        return diaries.size();
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public int getItemViewType(int position) {
        return position;
    }

    @Override
    public void onAttachedToRecyclerView(@NonNull  RecyclerView recyclerView) {
        super.onAttachedToRecyclerView(recyclerView);
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView day;
        CardView notes;
        public ViewHolder(@NonNull  View itemView) {
            super(itemView);
            day = itemView.findViewById(R.id.tvdayandmonth);
            notes = itemView.findViewById(R.id.layout_day_notes);
        }
    }
}
