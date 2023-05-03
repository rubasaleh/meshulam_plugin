import 'package:flutter/foundation.dart';
import 'package:flutter/services.dart';

import 'meshulam_payment_plugin_platform_interface.dart';

/// An implementation of [MeshulamPaymentPluginPlatform] that uses method channels.
class MethodChannelMeshulamPaymentPlugin extends MeshulamPaymentPluginPlatform {
  /// The method channel used to interact with the native platform.
  @visibleForTesting
  final methodChannel = const MethodChannel('meshulam_payment_plugin');

  @override
  Future<String?> getPlatformVersion() async {
    final version = await methodChannel.invokeMethod<String>('getPlatformVersion');
    return version;
  }

  @override
  Future<String?> createPaymentCall() async {
    final version = await methodChannel.invokeMethod<String>('createPaymentCall');
    return version;
  }
}
