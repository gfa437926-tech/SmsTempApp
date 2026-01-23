package com.example.smstemp;

import android.content.*;
import android.os.Bundle;
import android.telephony.SmsMessage;

public class SmsReceiver extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
        Bundle bundle = intent.getExtras();
        if (bundle == null) return;

        Object[] pdus = (Object[]) bundle.get("pdus");
        if (pdus == null) return;

        for (Object pdu : pdus) {
            SmsMessage sms = SmsMessage.createFromPdu((byte[]) pdu);
            String msg = sms.getMessageBody().trim();

            try {
                double temp = Double.parseDouble(msg);
                SupabaseHelper.sendTemperature(temp);
            } catch (Exception ignored) {}
        }
    }
}
