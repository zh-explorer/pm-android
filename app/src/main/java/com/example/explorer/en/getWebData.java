package com.example.explorer.en;


import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * Created by explorer on 15-11-24.
 */
public class getWebData {

    public static final int SHOW_RESPONSE = 0;

    public static void sendHttpRequest(final String address, final HttpCallbackListenter listenter) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                HttpURLConnection connection = null;
                try {
                    URL url = new URL(address);
                    connection = (HttpURLConnection) url.openConnection();
                    connection.setRequestMethod("GET");
                    connection.setConnectTimeout(8000);
                    connection.setReadTimeout(8000);
                    //connection.setDoInput(true);
                    //connection.setDoOutput(true);


                    InputStream in = connection.getInputStream();
                    BufferedReader reader = new BufferedReader(new InputStreamReader(in));
                    StringBuilder response = new StringBuilder();
                    String line;

                    while ((line = reader.readLine()) != null) {
                        response.append(line);
                    }

                    if (listenter != null) {
                        listenter.onFinish(response.toString());
                    }
                } catch (Exception e) {
                    if (listenter != null) {
                        listenter.onError(e);
                    }
                } finally {
                    if (connection != null) {
                        connection.disconnect();
                    }
                }
            }
        }).start();
    }
}

