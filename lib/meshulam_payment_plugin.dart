
import 'meshulam_payment_plugin_platform_interface.dart';

class MeshulamPaymentPlugin {
  Future<String?> getPlatformVersion() {
    return MeshulamPaymentPluginPlatform.instance.getPlatformVersion();
  }

  Future<String?> createPaymentCall(Map<String, String> params) {
    return MeshulamPaymentPluginPlatform.instance.createPaymentCall(params);
  }
}
