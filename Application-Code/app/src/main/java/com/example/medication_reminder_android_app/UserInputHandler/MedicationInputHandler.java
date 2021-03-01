package com.example.medication_reminder_android_app.UserInputHandler;

import java.util.Map;

/**
 *
 *
 * @author Samuel Jones
 * @since 3-1-2021
 */
public class MedicationInputHandler extends InputHandler {


    /**
     *
     *
     * @author Samuel Jones
     * @since 3-1-2021
     */
    public MedicationInputHandler() {
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
        String dosage = info.get("dosage");
        String startDate = info.get("startDate");
        String endDate = info.get("endDate");
        String intervalStr = info.get("interval");
        String[] interval = intervalStr.split(",");
        String warnings = info.get("warnings");
        String activeIngredient = info.get("activeIngredient");
        String generalPurpose = info.get("purpose");
        String userPurpose = info.get("userPurpose");
        String tagsStr = info.get("tags");
        String[] tags = tagsStr.split(",");

        sendInput();
    }
}
