package com.example.medication_reminder_android_app;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class NotifRecyclerAdapter extends RecyclerView.Adapter<NotifRecyclerAdapter.NotifViewHolder>{

    String[] notifTitles;
    String[] notifContents;
    Context context;


    public NotifRecyclerAdapter(Context cont, String[] titles, String[] contents){
        context = cont;
        notifTitles = titles;
        notifContents = contents;
    }

    @NonNull
    @Override
    public NotifViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.notif_row, parent, false);
        return new NotifViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull NotifViewHolder holder, int position) {
        holder.titleText.setText(notifTitles[position]);
        holder.contentText.setText(notifContents[position]);
    }

    @Override
    public int getItemCount() {
        return notifTitles.length;
    }

    public class NotifViewHolder extends RecyclerView.ViewHolder{

        TextView titleText;
        TextView contentText;

        public NotifViewHolder(@NonNull View itemView) {
            super(itemView);
            titleText = itemView.findViewById(R.id.notif_title);
            contentText = itemView.findViewById(R.id.notif_contents);
        }
    }
}
