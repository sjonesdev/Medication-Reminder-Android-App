package com.example.medication_reminder_android_app.UserInputHandler;

import java.security.InvalidParameterException;
import java.util.Map;

/**
 * Used to contain the input handlers and send data to them to process.
 *
 * @author Samuel Jones
 * @since 3-1-2021
 */
public class InputWrapper {

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
    public InputWrapper() {
        medicationInputHandler = new MedicationInputHandler();
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

}
