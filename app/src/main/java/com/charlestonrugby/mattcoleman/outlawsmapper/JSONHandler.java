package com.charlestonrugby.mattcoleman.outlawsmapper;

import android.util.Log;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.concurrent.ExecutionException;

/**
 * Created by mattcoleman on 6/21/15.
 */
public class JSONHandler {
    static JSONObject jObj = null;
    static String json = "";

    public JSONObject getJSONFromUrl(String url) {
        try {
            HTTPHandler httpHandle = new HTTPHandler();
            json = httpHandle.execute(url).get();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }

        // try parse the string to a JSON object
        try {
            jObj = new JSONObject(json);
        } catch (JSONException e) {
            Log.e("JSON Parser", "Error parsing data " + e.toString());
        }
        return jObj;
    }
}
