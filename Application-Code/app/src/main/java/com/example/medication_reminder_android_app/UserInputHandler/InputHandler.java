package com.example.medication_reminder_android_app.UserInputHandler;

import java.util.Date;
import java.util.Map;

/**
 * Base class for all input handlers.
 *
 * @author Samuel Jones
 * @since 3-1-2021
 */
public abstract class InputHandler {
    //member variables for inputs

    public InputHandler(){

    }

    abstract void inputRequest(Map<String,String> info);

    abstract void deleteRequest(String name);

    abstract void acknowledgeNotification(String name, Date time);

    abstract void acknowledgeNotification(String name);

    abstract void deleteAll();

}
