package com.example.medication_reminder_android_app;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class InfoRecyclerAdapter extends RecyclerView.Adapter<InfoRecyclerAdapter.InfoViewHolder> {

    String[] mednames;
    Context context;

    public InfoRecyclerAdapter(Context cont, String[] names){
        context = cont;
        mednames = names;
    }

    @NonNull
    @Override
    public InfoViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.med_row, parent, false);
        return new InfoViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull InfoViewHolder holder, int position) {
        holder.medText.setText(mednames[position]);
    }

    @Override
    public int getItemCount() {
        return mednames.length;
    }

    public class InfoViewHolder extends RecyclerView.ViewHolder{

        TextView medText;

        public InfoViewHolder(@NonNull View itemView) {
            super(itemView);
            medText = itemView.findViewById(R.id.med_name);
        }
    }
}
