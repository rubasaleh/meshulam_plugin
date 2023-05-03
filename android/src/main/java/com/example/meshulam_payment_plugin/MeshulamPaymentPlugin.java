package com.example.meshulam_payment_plugin;

import android.app.Activity;
import android.content.Context;
import androidx.annotation.NonNull;
import io.flutter.embedding.engine.plugins.FlutterPlugin;
import io.flutter.embedding.engine.plugins.activity.ActivityAware;
import io.flutter.embedding.engine.plugins.activity.ActivityPluginBinding;
import io.flutter.plugin.common.MethodCall;
import io.flutter.plugin.common.MethodChannel;
import io.flutter.plugin.common.MethodChannel.MethodCallHandler;
import io.flutter.plugin.common.MethodChannel.Result;
import io.flutter.plugin.common.PluginRegistry.Registrar;
import com.example.meshulam_payment_plugin.PaymentManager;
import android.os.Bundle;
import il.co.inmanage.meshulam_sdk.sdk.MeshulamSdk;
import il.co.inmanage.meshulam_sdk.sdk.SdkManager;
import java.net.HttpURLConnection;
import java.net.URL;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
import java.util.concurrent.ExecutionException;

public class MeshulamPaymentPlugin implements FlutterPlugin, MethodCallHandler, ActivityAware {
  private MethodChannel channel;
  private Context context;
  private Activity activity;
  private PaymentManager pm;

  @Override
  public void onAttachedToEngine(@NonNull FlutterPluginBinding flutterPluginBinding) {
    context = flutterPluginBinding.getApplicationContext();
    channel = new MethodChannel(flutterPluginBinding.getBinaryMessenger(), "meshulam_payment_plugin");
    channel.setMethodCallHandler(this);
  }

  @Override
  public void onMethodCall(@NonNull MethodCall call, @NonNull Result result) {
    if (call.method.equals("createPaymentCall")) {
      pm = new PaymentManager();
      SdkManager.OnPaymentResultListener resultListener = new SdkManager.OnPaymentResultListener() {
        @Override
        public void onGetPaymentData(Bundle bundle) {
        }

        @Override
        public void onPaymentSuccess(Bundle bundle) {
          result.success(true);
        }

        @Override
        public void onPaymentFailure(Bundle bundle) {
          Runnable httpCall = new Runnable() {
            @Override
            public void run() {
              try {
                String data = HttpRequestManager.callUrl(
                    "https://wosh-dev.herokuapp.com/api/meshulam/success?param=b03c2a62-0a94-42df-a539-ded9f039e929");
              } catch (ExecutionException e) {
                throw new RuntimeException(e);
              } catch (InterruptedException e) {
                throw new RuntimeException(e);
              }
            }
          };
          Thread newThread = new Thread(httpCall, "Http Call");
          newThread.run();
          result.success(false);
        }

        @Override
        public void onPaymentCanceled() {

        }
      };
      pm.createPaymentCall(activity, resultListener);
    } else {
      result.notImplemented();
    }
  }

  @Override
  public void onDetachedFromEngine(@NonNull FlutterPluginBinding binding) {
    channel.setMethodCallHandler(null);
  }

  @Override
  public void onDetachedFromActivity() {
  }

  @Override
  public void onReattachedToActivityForConfigChanges(ActivityPluginBinding binding) {
  }

  @Override
  public void onAttachedToActivity(ActivityPluginBinding binding) {
    activity = binding.getActivity();
  }

  @Override
  public void onDetachedFromActivityForConfigChanges() {
  }
}
