import 'package:plugin_platform_interface/plugin_platform_interface.dart';

import 'meshulam_payment_plugin_method_channel.dart';

abstract class MeshulamPaymentPluginPlatform extends PlatformInterface {
  /// Constructs a MeshulamPaymentPluginPlatform.
  MeshulamPaymentPluginPlatform() : super(token: _token);

  static final Object _token = Object();

  static MeshulamPaymentPluginPlatform _instance = MethodChannelMeshulamPaymentPlugin();

  /// The default instance of [MeshulamPaymentPluginPlatform] to use.
  ///
  /// Defaults to [MethodChannelMeshulamPaymentPlugin].
  static MeshulamPaymentPluginPlatform get instance => _instance;

  /// Platform-specific implementations should set this with their own
  /// platform-specific class that extends [MeshulamPaymentPluginPlatform] when
  /// they register themselves.
  static set instance(MeshulamPaymentPluginPlatform instance) {
    PlatformInterface.verifyToken(instance, _token);
    _instance = instance;
  }

  Future<String?> getPlatformVersion() {
    throw UnimplementedError('platformVersion() has not been implemented.');
  }

  Future<String?> createPaymentCall(Map<String, String> params) {
    throw UnimplementedError('createPaymentCall() has not been implemented.');
  }
}
