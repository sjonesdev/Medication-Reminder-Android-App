package com.example.medication_reminder_android_app.UserInputHandler;

//This class handles the appointments
//Might need ot add an interface for adding info to Reminder table

import java.util.Map;

public class AppointmentInputHandler extends InputHandler {


    /**
     *
     *
     * @author Samuel Jones
     * @since 3-1-2021
     */
    public AppointmentInputHandler() {
        super();
    }


    /**
     * Reads data from an input map and sends it to be written to the internal database
     * @param info The map to read from
     *
     * @author Samuel Jones
     * @since 3-1-2021
     */
    void inputRequest(Map<String,String> info) {
        String locationName = info.get("name");
        String locationAddress = info.get("address");
        String apptType = info.get("type");
        String apptPurpose = info.get("purpose");
        String doctor = info.get("doctor");
        String tagsStr = info.get("tags");
        String[] tags = tagsStr.split(",");

        sendInput();
    }

}
