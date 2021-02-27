package com.example.medication_reminder_android_app.UserInputHandler;

/*
This class handles userInterface input
Parent class for the specific input handlers
 */

import java.util.Map;

//might end up as abstract class
public abstract class InputHandler {
    //member variables for inputs

    public InputHandler(){

    }

    public abstract void inputRequest(Map<String,String> info);

    protected void sendInput() {

    }

    public void sendToDB(){
        //TODO
        //super class. send info to the SQLite DB
    }

}
