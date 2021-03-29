package com.example.medication_reminder_android_app.UserInputHandler;

import android.text.TextUtils;

import com.example.medication_reminder_android_app.SQLiteDB.MainViewModel;
import com.example.medication_reminder_android_app.SQLiteDB.MedicationEntity;
import com.example.medication_reminder_android_app.SQLiteDB.ReminderEntity;

import java.lang.reflect.Array;
import java.text.DateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.Map;

import io.reactivex.annotations.NonNull;
import io.reactivex.observers.DisposableSingleObserver;
import io.reactivex.schedulers.Schedulers;

/**
 * This class handles requests involving medications in the internal database
 *
 * @author Samuel Jones
 * @since 3-1-2021
 */
public class MedicationInputHandler extends InputHandler {

    private final int MAX_NUM_ACK = 30;

    private MainViewModel mainViewModel;

    /**
     * Creates a MedicationInputHandler
     */
    public MedicationInputHandler(MainViewModel mainViewModel) {
        this.mainViewModel = mainViewModel;
    }


    /**
     * Reads data from an input map and sends it to be written to the internal database
     * @param info The map to read from
     * @return The reminder ID associated with the medication inserted
     */
    @Override
    public void inputRequest(Map<String,String> info) {
        String name = info.get("name"); //user inputted
        String dosage = info.get("dosage"); //units included
        String startDate = info.get("startDate"); //YYYY-MM-DD HH:MM
        String endDate = info.get("endDate"); //"
        String interval = info.get("interval"); //comma separated list of hour values
        String warnings = info.get("warnings");
        String activeIngredient = info.get("activeIngredient");
        String generalPurpose = info.get("purpose");
        String userPurpose = info.get("userPurpose");
        String tags = info.get("tags"); //comma separated
        boolean recurring = Boolean.parseBoolean(info.get("recurring"));

        mainViewModel.insertMedAndReminder(name, dosage, recurring, startDate, endDate, interval,
                warnings, activeIngredient, tags);
    }


    /**
     * Requests the deletion of a medication and associated reminder from the internal database.
     * @param medName The name of the medication to be deleted
     */
    @Override
    void deleteRequest(String medName) {
        mainViewModel.deleteMedication(medName);
    }


    /**
     * Requests to delete all medications and associated reminders from internal database
     */
    @Override
    void deleteAllRequest() {
        mainViewModel.deleteAllMedications();
    }


    /**
     * Acknowledges a notification, updating the table to reflect when the next notification should
     * happen, and adding a timestamp to the medication if the notification was acknowledged
     * (medication was taken)
     * @param reminderID ID of the reminder to be updated
     * @param dismissed True if notification was dismissed, false if acknowledged
     */
    @Override
    public void acknowledgeNotificationRequest(int reminderID, boolean dismissed) {
        mainViewModel.getReminderById(reminderID).subscribeOn(Schedulers.io()).subscribe(new DisposableSingleObserver<ReminderEntity>() {
            @Override
            public void onSuccess(@NonNull ReminderEntity reminderEntity) {
                acknowledgeHelper(reminderEntity, dismissed);
            }

            @Override
            public void onError(@NonNull Throwable e) {

            }

        });
    }


    /**
     * Helper method for acknowledgeNotificationRequest used to ensure the method logic does not
     * execute until the database requests are processed.
     * @param r
     * @param dismissed
     */
    private void acknowledgeHelper(ReminderEntity r, Boolean dismissed) {
        mainViewModel.getMedById(r.getMedApptId()).subscribeOn(Schedulers.io()).subscribe(new DisposableSingleObserver<MedicationEntity>() {
            @Override
            public void onSuccess(@NonNull MedicationEntity medicationEntity) {
                acknowledgeHelper2(medicationEntity, r, dismissed);
            }

            @Override
            public void onError(@NonNull Throwable e) {

            }

        });
    }


    /**
     * Helper method for acknowledgeNotificationRequest used to ensure the method logic does not
     * execute until the database requests are processed. Contains the main logic of the method.
     * @param med The MedicationEntity obtained in the first method
     * @param r The ReminderEntity obtained in the first helper method
     * @param dismissed The dismissed flag for the notification
     */
    private void acknowledgeHelper2(MedicationEntity med, ReminderEntity r, Boolean dismissed) {
        int intervalIndex = r.getTimeIntervalIndex();
        String[] interval = med.getTimeRule().split(",");
        double HOURS_TO_MILLIS = 60 * 60 * 1000;
        long millisToAdd = (long) (Double.parseDouble(interval[intervalIndex]) * HOURS_TO_MILLIS);
        String lastReminderDateTime = r.getDate() + " " + r.getTime();
        String[] dateTime;
        String date = "";
        String time = "";
        try {
            Date d = DateFormat.getInstance().parse(lastReminderDateTime);
            d.setTime(d.getTime() + millisToAdd);
            dateTime = getSQLDateFormatFromDate(d);
            date = dateTime[0];
            time = dateTime[1];
        } catch(java.text.ParseException e) {} //TODO - handle exception
        mainViewModel.updateReminderDateAndTime(r, date, time, intervalIndex+1);

        if(!dismissed) {
            Date now = new Date();
            dateTime = getSQLDateFormatFromDate(now);
            String dateTimeStr = dateTime[0] + " " + dateTime[1];
            String ackStr = med.getAcknowledgements();
            String[] ack = ackStr.split(",");
            ArrayList<String> ackList = new ArrayList<String>(Arrays.asList(ack));
            if(ackList.size() >= MAX_NUM_ACK) {
                ackList.remove(0);
            }
            ackList.add(ackList.size()-1, dateTimeStr);
            String newAckStr = TextUtils.join(",", (String[]) ackList.toArray());
            mainViewModel.updateAcknowledgements(med, newAckStr);
        }

        //return date + " " + time; //YYYY-MM-DD HH:MM
    }


    /**
     * Gets a date in a SQL-like format from a Java date object
     * @param date A java date object
     * @return String array of the form {date, time} with date of the format YYYY-MM-DD and time of
     * the format HH:MM
     */
    private String[] getSQLDateFormatFromDate(Date date) {
        String dStr = date.toString(); // dow mon dd hh:mm:ss zzz yyyy
        String month = getMonthNumberFromAbbreviation(dStr.substring(4, 7));
        String d = dStr.substring(24) + "-" + month + "-" + dStr.substring(8, 10);
        String t = dStr.substring(11, 16);
        return new String[]{d, t};
    }


    /**
     * Gets the 2 digit representation of a month from a 3 character capitalized string of the
     * month's abbreviation (e.g., Jan, Feb, etc)
     * @param month The month abbreviation
     * @return The digit representation of the month
     */
    private String getMonthNumberFromAbbreviation(String month) {
        switch(month) {
            case "Jan":
                month = "01";
                break;
            case "Feb":
                month = "02";
                break;
            case "Mar":
                month = "03";
                break;
            case "Apr":
                month = "04";
                break;
            case "May":
                month = "05";
                break;
            case "Jun":
                month = "06";
                break;
            case "Jul":
                month = "07";
                break;
            case "Aug":
                month = "08";
                break;
            case "Sep":
                month = "09";
                break;
            case "Oct":
                month = "10";
                break;
            case "Nov":
                month = "11";
                break;
            case "Dec":
                month = "12";
                break;
            default:
                break;
        }
        return month;
    }

}