package com.example.medication_reminder_android_app.UserInputHandler;

import com.example.medication_reminder_android_app.SQLiteDB.MainViewModel;

import java.security.InvalidParameterException;
import java.util.Map;

/**
 * Used to contain the input handlers and send data to them to process.
 *
 * @author Samuel Jones
 * @since 3-1-2021
 */
public class InputWrapper {

    MainViewModel mainViewModel;
    MedicationInputHandler medicationInputHandler;
    DoctorInputHandler doctorInputHandler;
    AppointmentInputHandler appointmentInputHandler;

    public enum InputType{Medication, Doctor, Appointment}


    /**
     * Makes a new InputWrapper
     *
     * @author Samuel Jones
     * @since 3-1-2021
     */
    public InputWrapper(MainViewModel mainViewModel) {
        medicationInputHandler = new MedicationInputHandler(mainViewModel);
        doctorInputHandler = new DoctorInputHandler();
        appointmentInputHandler = new AppointmentInputHandler();
    }


    /**
     * Takes in an input map and sends it to the corresponding input handler
     * @param type The type of input
     * @param input The input map
     * @throws InvalidParameterException If type is invalid or input map is null
     *
     * @author Samuel Jones
     * @since 3-1-2021
     */
    public void processInput(InputType type, Map<String,String> input) throws InvalidParameterException {
        if(input == null) {
            throw new InvalidParameterException("Invalid input map");
        }
        switch (type) {
            case Medication:
                medicationInputHandler.inputRequest(input);
                break;
            case Doctor:
                //doctorInputHandler.inputRequest(input); TODO
                break;
            case Appointment:
                //appointmentInputHandler.inputRequest(input); TODO
                break;
            default:
                throw new InvalidParameterException("Invalid input type");
        }
    }


    /**
     * Requests the deletion of an entry from the internal database with the given type and name
     * @param type The type of entry
     * @param name The name of the entry
     * @throws InvalidParameterException If type is invalid or name string is null
     */
    public void processDeleteRequest(InputType type, String name) throws InvalidParameterException {
        if(name == null) {
            throw new InvalidParameterException("Invalid name String");
        }
        switch (type) {
            case Medication:
                medicationInputHandler.deleteRequest(name);
                break;
            case Doctor:
                //doctorInputHandler.inputRequest(input); TODO
                break;
            case Appointment:
                //appointmentInputHandler.inputRequest(input); TODO
                break;
            default:
                throw new InvalidParameterException("Invalid type");
        }
    }


    public void processAcknowledgement(InputType type, String name) {
        if(name == null) {
            throw new InvalidParameterException("Invalid name String");
        }
        switch (type) {
            case Medication:
                medicationInputHandler.acknowledgeNotification(name);
                break;
            case Doctor:
                //doctorInputHandler.inputRequest(input); TODO
                break;
            case Appointment:
                //appointmentInputHandler.inputRequest(input); TODO
                break;
            default:
                throw new InvalidParameterException("Invalid type");
        }
    }


    public void clearTable(InputType type) {
        switch (type) {
            case Medication:
                medicationInputHandler.deleteAll();
                break;
            case Doctor:
                //doctorInputHandler.inputRequest(input); TODO
                break;
            case Appointment:
                //appointmentInputHandler.inputRequest(input); TODO
                break;
            default:
                throw new InvalidParameterException("Invalid type");
        }
    }

}
