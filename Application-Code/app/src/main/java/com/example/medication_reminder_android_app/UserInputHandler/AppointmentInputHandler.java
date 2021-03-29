package com.example.medication_reminder_android_app.UserInputHandler;

//This class handles the appointments
//Might need ot add an interface for adding info to Reminder table


import com.example.medication_reminder_android_app.SQLiteDB.MainViewModel;

import java.util.Map;

class AppointmentInputHandler extends InputHandler {

    private MainViewModel mainViewModel;
    private CalenderEventCreator calenderEventCreator;

    /**
     *
     *
     * @author Samuel Jones
     * @since 3-1-2021
     */
    AppointmentInputHandler() {
        this.mainViewModel = mainViewModel;
        calenderEventCreator = new CalenderEventCreator();
    }


    /**
     * Reads data from an input map and sends it to be written to the internal database
     * @param info The map to read from
     *
     * @author Samuel Jones
     * @since 3-1-2021
     * @return
     */
    @Override
    void inputRequest(Map<String, String> info) {
        String locationName = info.get("locationName");
        String locationAddress = info.get("address");
        String apptName = info.get("name");
        String apptPurpose = info.get("purpose");
        String doctorsStr = info.get("doctors");
        String[] doctors = doctorsStr.split(",");
        String date = info.get("date"); // format: YYYY-MM-DD
        String time = info.get("time"); // HH:MM
        String tagsStr = info.get("tags");
        String[] tags = tagsStr.split(",");
        boolean createCalendarEvent = Boolean.valueOf(info.get("createCalendarEvent"));
        if(createCalendarEvent) {
            String durationStr = info.get("apptDuration");
            int duration = Integer.parseInt(durationStr);
            calenderEventCreator.createCalendarEvent(apptName, locationName+", "+locationName,
                    apptPurpose, doctors, date, time, duration); // name, location, description, people involved, date, time
        }

        //mainViewModel.insertAppointment();
    }

    @Override
    void deleteRequest(String name) {

    }

    @Override
    void acknowledgeNotificationRequest(int reminderID, boolean dismissed) {

    }

    @Override
    void deleteAllRequest() {

    }

}
