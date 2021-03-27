package com.example.medication_reminder_android_app.FDADatabaseHandler;

import android.util.Log;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

//This class is the main class for the FDA Database component

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.util.HashMap;
import java.util.Map;

public class DataBaseHandler {

    private final int DEFAULT_NUM_RESULTS;
    private static final int BRAND_NAME_INDEX = 0;
    private static final int GENERIC_NAME_INDEX = 1;
    private static final int SUBSTANCE_NAME_INDEX = 2;
    private static final int PURPOSE_INDEX = 3;
    private static final int WARNINGS_INDEX = 4;
    private Map<Integer,String[]> latestResults;


    /**
     *
     */
    public static enum SearchParameters{brandName(BRAND_NAME_INDEX), genericName(GENERIC_NAME_INDEX),
            substanceName(SUBSTANCE_NAME_INDEX), purpose(PURPOSE_INDEX), warnings(WARNINGS_INDEX);
        public final int value;
        private SearchParameters(int value) {
            this.value = value;
        }
    }


    /**
     *
     * @param defaultNumResults
     */
    public DataBaseHandler(int defaultNumResults) {
        DEFAULT_NUM_RESULTS = defaultNumResults;
    }


    /**
     *
     * @param parameter
     * @return
     * @throws IOException
     */
    public Map<Integer,String[]> searchOpenFDA(String parameter) throws IOException {
        Map<Integer,String[]> map = new HashMap<>();

        String sURL = "https://api.fda.gov/drug/label.json?search="
                +"(openfda.generic_name:\""+parameter+"\")"
                +"+(openfda.brand_name:\""+parameter+"\")"
                +"+(openfda.substance_name:\""+parameter+"\")"
                +"&limit=" + DEFAULT_NUM_RESULTS; //just a string

        // Connect to the URL using java's native library
        URL url = new URL(sURL);
        URLConnection request = url.openConnection();
        request.connect();

        // Convert to a JSON object to print data
        JsonElement root = JsonParser.parseReader(new InputStreamReader((InputStream) request.getContent())); //Convert the input stream to a json element
        JsonObject rootObject = root.getAsJsonObject(); //May be an array, may be an object.
        JsonArray results = rootObject.get("results").getAsJsonArray();
        int numRes = results.size();
        Map<Integer,String[]> resMap = new HashMap<>();
        String brand; //brand_name
        String generic; //generic_name
        String substance; //active_ingredient
        String purpose;
        String warnings;

        for(int i = 0; i < numRes; i++) {
            String[] cur = new String[5];
            JsonObject curObj = results.get(i).getAsJsonObject();
            JsonObject curOpenFDA = curObj.get("openfda").getAsJsonObject();
            brand = curOpenFDA.get("brand_name").getAsString();
            generic = curOpenFDA.get("generic_name").getAsString();
            substance = curOpenFDA.get("substance_name").getAsString();
            purpose = curObj.get("purpose").getAsString();
            warnings = curObj.get("warnings").getAsString();
            cur[BRAND_NAME_INDEX] = brand;
            cur[GENERIC_NAME_INDEX] = generic;
            cur[SUBSTANCE_NAME_INDEX] = substance;
            cur[PURPOSE_INDEX] = purpose;
            cur[WARNINGS_INDEX] = warnings;
            resMap.put(i, cur);
        }
        latestResults = resMap;
        return resMap;
    }


    /**
     *
     * @param parameter
     * @param index
     * @return
     */
    public Map<Integer,String> searchOpenFDAAndGetResultsAtIndex(String parameter, SearchParameters index) {
        try {
            Map<Integer,String[]> map = searchOpenFDA(parameter);
            Map<Integer,String> res = new HashMap<>();
            for(int i = 0; i < map.size(); i++) {
                res.put(i, map.get(i)[index.value]);
            }
            return res;
        } catch(IOException e) {
            Log.e("OpenFDA", "Failure to query OpenFDA");
            return null;
        }
    }


    /**
     *
     * @param key
     * @return
     */
    public String[] getFullResultByMapKeyFromLatestNamesSearch(int key) {
        return latestResults.get(key);
    }

}
