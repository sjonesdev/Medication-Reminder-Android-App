package com.example.medication_reminder_android_app;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;

import androidx.appcompat.app.AppCompatDialogFragment;
import androidx.lifecycle.ViewModelProvider;

import com.example.medication_reminder_android_app.SQLiteDB.MainViewModel;

public class DeletionDialog extends AppCompatDialogFragment {

    private MainViewModel mainView; //the MainViewModel used to call deletion requests

    /**
     * @author Robert & Lucas
     * Creates a dialog that asks the user if they are sure they want to delete their information
     */
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {

        //Instantiate a MainViewModel to handle deletion requests
        mainView = new ViewModelProvider(this).get(MainViewModel.class);

        /*Instantiate an AlertDialog Builder to create the dialog,
        and a LayoutInflater to display the dialog*/
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        LayoutInflater inflater = getActivity().getLayoutInflater();

        /*Create a view in which the dialog will be built,
        using the deletion_popup layout as the contents of the dialog*/
        View view = inflater.inflate(R.layout.deletion_popup, null);

        //Build the actual dialog, setting the title and the onClick listeners for the "yes" and "no" buttons
        builder.setView(view)
                .setTitle("WARNING!")
                .setNegativeButton("No", new DialogInterface.OnClickListener() {
                    /**
                     * @Author Lucas Colegrove
                     * Closes the dialog when the "no" button is clicked
                     */
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                })
                .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    /**
                     * @author Lucas Colegrove
                     * Deletes all messages and reminders and closes the dialog when the "yes" button is clicked
                     */
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        mainView.deleteAllMedications();
                        mainView.deleteAllReminders();
                    }
                });

        //Return the newly created dialog
        return builder.create();
    }
}
