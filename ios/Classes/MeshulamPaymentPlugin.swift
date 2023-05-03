import Flutter
import UIKit

public class MeshulamPaymentPlugin: NSObject, FlutterPlugin {
  public static func register(with registrar: FlutterPluginRegistrar) {
    let channel = FlutterMethodChannel(name: "meshulam_payment_plugin", binaryMessenger: registrar.messenger())
    let instance = MeshulamPaymentPlugin()
    registrar.addMethodCallDelegate(instance, channel: channel)
  }

  public func handle(_ call: FlutterMethodCall, result: @escaping FlutterResult) {
    if call.method == "createPaymentCall" {
      
    }
    result("iOS " + UIDevice.current.systemVersion)
  }
}
