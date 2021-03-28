package com.example.medication_reminder_android_app;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.*;

public class InfoRecyclerAdapter extends RecyclerView.Adapter<InfoRecyclerAdapter.InfoViewHolder> implements Filterable {

    //private String[] mednames; //array of medication names pulled from the input handler
    private ArrayList<String> medSearchList; //medication name array list storing all medications
    private ArrayList<String> medDisplayList; //medication name array list containing only the medications being displayed on screen
    private Context context; //the current context
    private OnItemListener medItemListener; //click listener for medication cards in the RecyclerView

    /**
     * @author Robert Fahey
     * Constructor for the InfoRecyclerAdapter
     */
    public InfoRecyclerAdapter(Context cont, String[] names, OnItemListener list){
        context = cont;
        //mednames = names;
        medItemListener = list;

        medSearchList = new ArrayList<>();
        medDisplayList = new ArrayList<>();
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
        holder.medText.setText(medDisplayList.get(position));
    }

    public void setWords(ArrayList<String> words){
        medDisplayList = words;
        medSearchList = words;
        notifyDataSetChanged();
    }

    public String getNameString(int position){
        return medDisplayList.get(position);
    }


    /**
     * @author Robert Fahey
     * @return the number of medications being displayed
     */
    @Override
    public int getItemCount() {
        return medDisplayList.size();
    }

    /**
     * @author Robert Fahey
     * @return a medFilter object
     */
    @Override
    public Filter getFilter() {
        return medFilter;
    }

    private Filter medFilter = new Filter() {
        /**
         * @author Robert Fahey
         * @param constraint the text entered into the search bar
         * @return the results formatted as a FilterResults object
         */
        @Override
        protected FilterResults performFiltering(CharSequence constraint) {
            ArrayList<String> filtered = new ArrayList<>(); // a new array list for storing filter results

            if(constraint == null || constraint.length() == 0){
                filtered.addAll(medSearchList); //if the search bar is empty, display all medications
            } else {
                // if the search bar isn't empty ...
                String pattern = constraint.toString().toLowerCase().trim(); //get the text in the search bar, set it to lowercase

                /*for each string in the master medication array list,
                compare the string to the previously declared pattern. If the string contains the pattern,
                add the string to the filter results array*/
                for(String s : medSearchList){
                    if (s.toLowerCase().contains(pattern)){
                        filtered.add(s);
                    }
                }
            }

            //Convert the array list of results to a FilterResults object and return it
            FilterResults result = new FilterResults();
            result.values = filtered;
            return result;
        }

        /**
         * @author Robert Fahey
         * @param constraint the text entered into the search bar
         * @param results the results of the previous performFiltering call
         * Resets the contents of the RecyclerView to the filtered search results
         */
        @Override
        protected void publishResults(CharSequence constraint, FilterResults results) {
            medDisplayList.clear();
            medDisplayList.addAll((List) results.values);
            notifyDataSetChanged();
        }
    };

    /**
     * @author Robert Fahey
     * Acts as a container for the medication cards.
     * Instantiates TextView objects to be set to display the names and dosages
     */
    public class InfoViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{

        TextView medText;
        OnItemListener onitemlistener;

        public InfoViewHolder(@NonNull View itemView, OnItemListener listener) {
            super(itemView);
            medText = itemView.findViewById(R.id.med_name);
            onitemlistener = listener;

            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            onitemlistener.onItemClick(getAdapterPosition());
        }
    }

    /**
     * @author Robert Fahey
     * Interface for implementing a click listener for a med card in the RecyclerView
     */
    public interface OnItemListener {
        void onItemClick(int position);
    }
}
