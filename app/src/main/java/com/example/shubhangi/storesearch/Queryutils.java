package com.example.shubhangi.storesearch;
import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;

/**
 * Helper methods related to requesting and receiving earthquake data from USGS.
 */
public final class Queryutils {

    /** Sample JSON response for a USGS query */

    private Queryutils() {
    }

    /**
     * Return a list of {} objects that has been built up from
     * parsing a JSON response.
     */
    private static URL createUrl(String stringUrl) {
        URL url = null;
        try {
            url = new URL(stringUrl);
        } catch (MalformedURLException exception) {
            return null;
        }
        return url;
    }
    private static String makeHttpRequest(URL url) throws IOException {
        String jsonResponse = "";
        HttpURLConnection urlConnection = null;
        InputStream inputStream = null;
        try {
            urlConnection = (HttpURLConnection) url.openConnection();
            urlConnection.setRequestMethod("GET");
            urlConnection.setReadTimeout(10000 /* milliseconds */);
            urlConnection.setConnectTimeout(15000 /* milliseconds */);
            urlConnection.connect();
            inputStream = urlConnection.getInputStream();
            jsonResponse = readFromStream(inputStream);
        } catch (IOException e) {
        } finally {
            if (urlConnection != null) {
                urlConnection.disconnect();
            }
            if (inputStream != null) {
                // function must handle java.io.IOException here
                inputStream.close();
            }
        }
        Log.v("Shubhn",jsonResponse);
        return jsonResponse;
    }
    private static String readFromStream(InputStream inputStream) throws IOException {
        StringBuilder output = new StringBuilder();
        if (inputStream != null) {
            InputStreamReader inputStreamReader = new InputStreamReader(inputStream, Charset.forName("UTF-8"));
            BufferedReader reader = new BufferedReader(inputStreamReader);
            String line = reader.readLine();
            while (line != null) {
                output.append(line);
                line = reader.readLine();
            }
        }
        return output.toString();
    }
    public static List<Storelist> extractStore(String Grocery_json) throws JSONException {

        // Create an empty ArrayList that we can start adding earthquakes to
        List<Storelist> stores = new ArrayList<>();

        try {
            JSONObject basejsonresponse = new JSONObject(Grocery_json);
            JSONArray storesarray = basejsonresponse.getJSONArray("results");
            for (int i = 0; i < storesarray.length(); i++) {
                JSONObject currentstore = storesarray.getJSONObject(i);
                String str1 = currentstore.getString("name");
                String str2 = currentstore.getString("vicinity");
                String str = str1 + "\n" + str2;
                Storelist store=new Storelist(str);
                stores.add(store);
            }
        }
        catch (JSONException e){
        }
        // Return the list of earthquakes
        return stores;
    }
    public static List<Storelist> fetchdata(String requestUrl) {
        URL url = createUrl(requestUrl);
        String jsonResponse = null;
        try {
            jsonResponse = makeHttpRequest(url);
        } catch (IOException e) {
        }
        List<Storelist> strlst = null;
        try {
            strlst = extractStore(jsonResponse);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return strlst;
    }

}
