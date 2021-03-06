package com.example.medication_reminder_android_app.UserInterface;

//This class handles sending information to the Input Handler

public class InfoInput {

    @XmlRootElement
    public class Medication {
        private String name;
        private String dosage;
        private String timeTaken;
        private String[] days;
        private Boolean recurring;
        private String[] tags;

        public Medication(String name, String dosage, String timeTaken, String[] days, Boolean recurring, String[] tags){
            this.name = name;
            this.dosage = dosage;
            this.timeTaken = timeTaken;
            this.days = days;
            this.recurring = recurring;
            this.tags = tags;
        }


    }


    public class Doctor {
        private String name;
        private int phone;
        private String address;

        public Doctor(String name, int phone, String address){
            this.name = name;
            this.phone = phone;
            this.address = address;
        }
    }

    public class Appointment {
        private String name;
        private String address;

        public Appointment(String name, String address){
            this.name = name;
            this.address = address;
        }
    }
}
 