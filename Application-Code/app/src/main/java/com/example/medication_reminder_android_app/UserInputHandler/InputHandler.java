package com.example.medication_reminder_android_app.UserInputHandler;

/*
This class handles userInterface input
Parent class for the specific input handlers

TODO: add input handler class to instantiate handlers
 */

//might end up as abstract class
public abstract class InputHandler {
    //member variables for inputs

    public InputHandler(){

    }

    private void parseInfo(){
        //TODO
        //method to parse XML from the UI
    }

    public void sendToDB(){
        //TODO
        //super class. send info to the SQLite DB
    }

}
