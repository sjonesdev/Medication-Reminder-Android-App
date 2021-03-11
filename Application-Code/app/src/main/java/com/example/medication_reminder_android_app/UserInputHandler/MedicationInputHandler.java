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
        String name = info.get("name");
        String dosage = info.get("dosage");
        String startDate = info.get("startDate"); //YYYY-MM-DD HH:MM
        String endDate = info.get("endDate");
        String interval = info.get("interval");
        //String[] interval = intervalStr.split(","); //in hours
        //int numIntervals = interval.length;
        String warnings = info.get("warnings");
        String activeIngredient = info.get("activeIngredient");
        String generalPurpose = info.get("purpose");
        String userPurpose = info.get("userPurpose");
        String tagsStr = info.get("tags");
        //String[] tags = tagsStr.split(",");
        int reminderId = 0; //TODO

        boolean recurring = true;

        mainViewModel.insertMedication(name, dosage, recurring, interval, reminderId, "",
                warnings, activeIngredient, tagsStr);
    }

    void deleteRequest(String medName) {
        mainViewModel.deleteMedication(medName);
    }

    void acknowledgeNotification(Date time) {

    }

    void acknowledgeNotification() {
        Date time = new Date();
    }
}
