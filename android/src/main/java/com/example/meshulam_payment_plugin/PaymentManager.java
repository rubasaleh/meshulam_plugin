package com.example.meshulam_payment_plugin;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;

import il.co.inmanage.meshulam_sdk.data.CreatePaymentData;
import il.co.inmanage.meshulam_sdk.sdk.MeshulamSdk;
import il.co.inmanage.meshulam_sdk.sdk.SdkManager;
import io.flutter.plugin.common.MethodChannel.Result;

public class PaymentManager {
    public PaymentManager() {

    }

    public void createPaymentCall(Activity currentActivity, SdkManager.OnPaymentResultListener resultListener) {
        MeshulamSdk.getInstance(currentActivity).init();
        CreatePaymentData paymentDataObject = new CreatePaymentData.Builder()
                .setPageCode("52cbbd366421")
                .setUserId("356b9a13afe7f7fb")
                .setEmail("rubsaleh1@gmail.com")
                .setPhone("0532231523")
                .setFullName("Ruba Saleh")
                .setSum("1")
                .create();

        MeshulamSdk.getInstance(currentActivity).createPaymentProcess(paymentDataObject, resultListener);
    }
}
