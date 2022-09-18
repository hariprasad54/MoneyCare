package com.example.moneycare.apicontroler;

import static com.example.moneycare.Constants.EMPTY_STR;
import static com.example.moneycare.Constants.HOSTNAME;

import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;

import javax.net.ssl.HttpsURLConnection;

public class GetRequest {

    public static String sendRequest(String command) throws IOException {
        URL url = new URL("http",HOSTNAME,8080,command);
        String response = "";
        HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();

        urlConnection.setRequestMethod("GET");
        urlConnection.setRequestProperty("content-type", "application/json");
        urlConnection.connect();
        int responseCode=urlConnection.getResponseCode();

        if (responseCode == HttpsURLConnection.HTTP_OK) {
            String line;
            BufferedReader br=new BufferedReader(new InputStreamReader(urlConnection.getInputStream()));
            while ((line=br.readLine()) != null) {
                response+=line;
            }
        }
        else {
            response = EMPTY_STR;

        }
        return response;
    }
}
