package com.example.meshulam_payment_plugin;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import java.util.Map;

import il.co.inmanage.meshulam_sdk.data.CreatePaymentData;
import il.co.inmanage.meshulam_sdk.sdk.MeshulamSdk;
import il.co.inmanage.meshulam_sdk.sdk.SdkManager;
import io.flutter.plugin.common.MethodChannel.Result;

public class PaymentManager {
    public PaymentManager() {

    }

    public void createPaymentCall(Activity currentActivity, Map<String, String> params,
            SdkManager.OnPaymentResultListener resultListener) {

        MeshulamSdk.getInstance(currentActivity).init();
        CreatePaymentData paymentDataObject = new CreatePaymentData.Builder()
                .setPageCode(params.get("pageCode"))
                .setUserId(params.get("userId"))
                .setPhone(params.get("phone"))
                .setFullName(params.get("name"))
                .setSum(params.get("sum"))
                .create();
        MeshulamSdk.getInstance(currentActivity).createPaymentProcess(paymentDataObject, resultListener);

    }
}
