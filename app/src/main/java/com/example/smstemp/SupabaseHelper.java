package com.example.smstemp;

import okhttp3.*;
import java.time.LocalDate;
import java.time.LocalTime;

public class SupabaseHelper {
    static String SUPABASE_URL = "https://vktcvkwqszehgmhqjrkb.supabase.co";
    static String API_KEY = "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJpc3MiOiJzdXBhYmFzZSIsInJlZiI6InZrdGN2a3dxc3plaGdtaHFqcmtiIiwicm9sZSI6ImFub24iLCJpYXQiOjE3NjIxNzE3NTEsImV4cCI6MjA3Nzc0Nzc1MX0.KTX1c8BAJpy1Jp3-IDXFRqb4Jo7SLabiwICBtn_mR5o";
    public static void sendTemperature(double temp) {
        try {
            String json = "{"
                    + "\"city\":\"Damascus\","
                    + "\"temperature\":" + temp + ","
                    + "\"sms_date\":\"" + LocalDate.now() + "\","
                    + "\"sms_time\":\"" + LocalTime.now().withNano(0) + "\""
                    + "}";

            Request request = new Request.Builder()
                    .url(SUPABASE_URL)
                    .addHeader("apikey", API_KEY)
                    .addHeader("Authorization", "Bearer " + API_KEY)
                    .addHeader("Content-Type", "application/json")
                    .post(RequestBody.create(json, MediaType.parse("application/json")))
                    .build();

            new OkHttpClient().newCall(request).execute();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
