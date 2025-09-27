package com.example.mysmsapp;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.telephony.SmsMessage;
import android.util.Log;

public class SmsReceiver extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
        Bundle bundle = intent.getExtras();
        if (bundle != null) {
            Object[] pdus = (Object[]) bundle.get("pdus");
            if (pdus != null) {
                for (Object pdu : pdus) {
                    SmsMessage message = SmsMessage.createFromPdu((byte[]) pdu);
                    String sender = message.getDisplayOriginatingAddress();
                    String body = message.getMessageBody();
                    Log.d("MySmsApp", "SMS from: " + sender + " Body: " + body);

                    if (MainActivity.smsText != null) {
                        MainActivity.smsText.setText("SMS from: " + sender + "\n" + body);
                    }
                }
            }
        }
    }
}
