package com.example.meshulam_payment_plugin;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class HttpRequestManager {
    private static String onFailureUrlName = "https://wosh-dev.herokuapp.com/meshulam/appOnPaymentFailure";
    private static String onSuccessUrlName = "https://wosh-dev.herokuapp.com/meshulam/appOnPaymentSuccess";

    public static String onFailureHttpRequest(Map<String, String> bodyParams)
            throws ExecutionException, InterruptedException {
        return callUrl(onFailureUrlName, bodyParams);
    }

    public static String onSuccessHttpRequest(Map<String, String> bodyParams)
            throws ExecutionException, InterruptedException {
        return callUrl(onSuccessUrlName, bodyParams);
    }

    private static String callUrl(String url, Map<String, String> bodyParams)
            throws ExecutionException, InterruptedException {
        ExecutorService executorService = Executors.newSingleThreadExecutor();
        Future<String> future = executorService.submit(new Callable<String>() {
            @Override
            public String call() throws Exception {
                return getDataFromWeb(url, bodyParams);
            }
        });

        executorService.shutdown();
        return future.get();
    }

    private static String getDataFromWeb(String strUrl, Map<String, String> bodyParams)
            throws IOException, JSONException {

        URL url = new URL(strUrl);
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        connection.setRequestMethod("POST");
        connection.setRequestProperty("Content-Type", "application/json");
        JSONObject jsonBody = new JSONObject();
        for (Map.Entry<String, String> entry : bodyParams.entrySet()) {
            jsonBody.put(entry.getKey(), entry.getValue());
        }
        connection.setDoOutput(true);
        OutputStream outputStream = connection.getOutputStream();
        BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(outputStream, "UTF-8"));
        writer.write(jsonBody.toString());
        writer.flush();
        writer.close();
        outputStream.close();

        try {
            if (connection.getResponseCode() != HttpURLConnection.HTTP_OK) {
                return null;
            }
            String line;
            BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            StringBuffer response = new StringBuffer();
            while ((line = in.readLine()) != null) {
                response.append(line);
            }
            in.close();
            return response.toString();
        } finally {
            connection.disconnect();
        }
    }
}