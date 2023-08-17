package com.coki.soapclient;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;

public class SoapClient {
    public static String sendSoapRequest(String wsEndpoint, String soapRequest, String soapAction) throws IOException {
        HttpURLConnection httpConn = null;
        try {
            URL url = new URL(wsEndpoint);
            httpConn = (HttpURLConnection) url.openConnection();
            httpConn.setRequestMethod("POST");
            httpConn.setRequestProperty("Content-Type", "text/xml; charset=utf-8");
            httpConn.setRequestProperty("SOAPAction", soapAction);
            httpConn.setDoOutput(true);

            try (OutputStream out = httpConn.getOutputStream()) {
                byte[] bytes = soapRequest.getBytes(StandardCharsets.UTF_8);
                out.write(bytes);
            }

            int responseCode = httpConn.getResponseCode();
            if (responseCode == HttpURLConnection.HTTP_OK) {
                try (BufferedReader in = new BufferedReader(new InputStreamReader(httpConn.getInputStream()))) {
                    StringBuilder response = new StringBuilder();
                    String line;
                    while ((line = in.readLine()) != null) {
                        response.append(line);
                    }
                    return response.toString();
                }
            } else {
                throw new RuntimeException("HTTP request failed with response code: " + responseCode);
            }
        } finally {
            if (httpConn != null) {
                httpConn.disconnect();
            }
        }
    }

}
