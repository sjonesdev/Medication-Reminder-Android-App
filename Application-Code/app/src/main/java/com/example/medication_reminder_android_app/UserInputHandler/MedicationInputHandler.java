package com.example.medication_reminder_android_app.UserInputHandler;

import com.example.medication_reminder_android_app.SQLiteDB.MainViewModel;

import java.util.Date;
import java.util.Map;

/**
 *
 *
 * @author Samuel Jones
 * @since 3-1-2021
 */
public class MedicationInputHandler extends InputHandler {

    private MainViewModel mainViewModel;

    /**
     *
     *
     * @author Samuel Jones
     * @since 3-1-2021
     */
    public MedicationInputHandler(MainViewModel mainViewModel) {
        super();
        this.mainViewModel = mainViewModel;
    }


    /**
     * Reads data from an input map and sends it to be written to the internal database
     * @param info The map to read from
     *
     * @author Samuel Jones
     * @since 3-1-2021
     */
    void inputRequest(Map<String,String> info) {
        String name = info.get("name"); //user inputted
        String dosage = info.get("dosage"); //units included
        String startDate = info.get("startDate"); //YYYY-MM-DD HH:MM
        String endDate = info.get("endDate"); //"
        String interval = info.get("interval"); //comma separated array of hour values
        String warnings = info.get("warnings");
        String activeIngredient = info.get("activeIngredient");
        String generalPurpose = info.get("purpose");
        String userPurpose = info.get("userPurpose");
        String tags = info.get("tags"); //comma separated
        int reminderId = 0; //TODO

        boolean recurring = Boolean.parseBoolean(info.get("recurring"));

        mainViewModel.insertMedication(name, dosage, recurring, startDate, interval,
                warnings, activeIngredient, tags); //tell hayley to add endDate

        //String medicationName, String inputDosage, boolean ifRecurring, String firstDate,
        //String inputTimeRule, String inputWarnings, String inputIngredients, String inputTags
    }

    void deleteRequest(String medName) {
        mainViewModel.deleteMedication(medName);
    }

    void acknowledgeNotification(String name, Date time) {

    }

    void acknowledgeNotification(String name) {
        Date time = new Date();
    }

    void deleteAll() {
        mainViewModel.deleteAllMedications();
    }
}
