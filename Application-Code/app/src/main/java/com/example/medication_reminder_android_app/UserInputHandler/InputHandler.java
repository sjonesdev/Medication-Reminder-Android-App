package com.example.medication_reminder_android_app.UserInputHandler;

import java.util.Map;

/**
 *
 *
 * @author Samuel Jones
 * @since 3-1-2021
 */
public abstract class InputHandler {
    //member variables for inputs

    public InputHandler(){

    }

    abstract void inputRequest(Map<String,String> info);

    protected void sendInput() {

    }

    public void sendToDB(){
        //TODO
        //super class. send info to the SQLite DB
    }

}
