package com.example.medication_reminder_android_app.UserInputHandler;

//This class handles the doctor information


import java.util.Map;

public class DoctorInputHandler extends InputHandler {
    //member variables: name etc

    /**
     *
     *
     * @author Samuel Jones
     * @since 3-1-2021
     */
    public DoctorInputHandler() {
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
        String name = info.get("name");
        String type = info.get("type");
        String locationsStr = info.get("locations");
        String[] locations = locationsStr.split(";");
        String phoneNum = info.get("phoneNum");
        String altPhoneNum = info.get("altPhoneNum");
        String email = info.get("email");
        String tagsStr = info.get("tags");
        String[] tags = tagsStr.split(",");

        sendInput();
    }

}
