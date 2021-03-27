package com.example.medication_reminder_android_app;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class InfoRecyclerAdapter extends RecyclerView.Adapter<InfoRecyclerAdapter.InfoViewHolder> {

    private String[] mednames; //array of medication names for display
    private String[] meddosages; //array of medication dosages for display
    private Context context; //the current context
    private OnItemListener medItemListener; //click listener for medication cards in the RecyclerView

    /**
     * @author Robert Fahey
     * Constructor for the InfoRecyclerAdapter
     */
    public InfoRecyclerAdapter(Context cont, String[] names, String[] dosages, OnItemListener list){
        context = cont;
        mednames = names;
        meddosages = dosages;
        medItemListener = list;
    }

    /**
     * @author Robert Fahey
     * Creates a ViewHolder, with each card based on the "med_row" layout
     */
    @NonNull
    @Override
    public InfoViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.med_row, parent, false);
        return new InfoViewHolder(view, medItemListener);
    }

    /**
     * @author Robert Fahey
     * Sets the TextViews for the cards in the InfoViewHolder to display the appropriate information
     */
    @Override
    public void onBindViewHolder(@NonNull InfoViewHolder holder, int position) {
        holder.medText.setText(mednames[position]);
        holder.dosageText.setText(meddosages[position]);
    }

    /**
     * @author Robert Fahey
     * @return the number of medications being displayed
     */
    @Override
    public int getItemCount() {
        return mednames.length;
    }

    /**
     * @author Robert Fahey
     * Acts as a container for the medication cards.
     * Instantiates TextView objects to be set to display the names and dosages
     */
    public class InfoViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{

        TextView medText;
        TextView dosageText;
        OnItemListener onitemlistener;

        public InfoViewHolder(@NonNull View itemView, OnItemListener listener) {
            super(itemView);
            medText = itemView.findViewById(R.id.med_name);
            dosageText = itemView.findViewById(R.id.med_dosage);
            onitemlistener = listener;

            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            onitemlistener.onItemClick(getAdapterPosition());
        }
    }


    public interface OnItemListener {
        void onItemClick(int position);
    }
}
