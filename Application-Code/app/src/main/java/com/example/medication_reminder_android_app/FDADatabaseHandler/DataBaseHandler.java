package com.example.medication_reminder_android_app.FDADatabaseHandler;

import com.example.medication_reminder_android_app.FDADatabaseHandler.JsonHandler;

//This class is the main class for the FDA Database component

import org.json.JSONObject;

import java.util.Map;

public class DataBaseHandler {

    private final int DEFAULT_NUM_RESULTS;
    private final JsonHandler jsonHandler;

    public enum SearchParameters{genericName, brandName, substanceName}

    public DataBaseHandler(int defaultNumResults) {
        DEFAULT_NUM_RESULTS = defaultNumResults;
        jsonHandler = new JsonHandler();
    }

    public String[][] getSearchResults(String searchTerm, SearchParameters[] params, int maxNumResults) {
        String[][] results = new String[maxNumResults][2];
        String name = "";
        String url = ""; //initiailize with first url based on search params
        int i = 0;
        do {
            //connect to url
            //get json stuff
            //name = json['name'];
            //url = json['next-url'];
            results[i] = new String[]{name, url};
            i++;
        } while(i < maxNumResults /* && next url is good*/);
        return results/*subarray from 0 to index+1 (subarray doesn't include index of end*/;
    }

    public Map<String, String> getDataFromURL(String url) {
        return jsonHandler.getInfo();
    }

}
