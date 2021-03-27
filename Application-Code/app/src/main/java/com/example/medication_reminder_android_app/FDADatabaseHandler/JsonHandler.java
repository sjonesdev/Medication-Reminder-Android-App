package com.example.medication_reminder_android_app.FDADatabaseHandler;

/*
This class uses the FDA database interface.
THe class will take the JSON file and parse it.
The parsed information will be sent to other classes in this package.
 */

import java.util.HashMap;
import java.util.Map;

public class JsonHandler {
    //member variables will be information saved from parsing, possibly
    //name, type, etc

    public JsonHandler(){
        parseInfo();
    }

    public Map<String, String> getInfo(){
        //TODO
        //getter method for member variables
        //method for the UI to get information
        return new HashMap<String, String>();
    }

    private void parseInfo(){
        //TODO
        //get info from FDA database
        //parse info and put into member variables
    }

    private DrugLink makeDrugLink(String name, String url) {
        return new DrugLink(name, url);
    }

    public class DrugLink {
        private String name;
        private String url;

        public DrugLink(String name, String url) {
            this.name = name;
            this.url = url;
        }

        public String getName() {
            return this.name;
        }

        public void setName(String newName) {
            this.name = newName;
        }

        public String getUrl() {
            return this.url;
        }

        public String[] toArray() {
            return new String[]{this.name, this.url};
        }
    }

}
