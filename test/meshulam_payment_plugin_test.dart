import 'package:flutter_test/flutter_test.dart';
import 'package:meshulam_payment_plugin/meshulam_payment_plugin.dart';
import 'package:meshulam_payment_plugin/meshulam_payment_plugin_platform_interface.dart';
import 'package:meshulam_payment_plugin/meshulam_payment_plugin_method_channel.dart';
import 'package:plugin_platform_interface/plugin_platform_interface.dart';

class MockMeshulamPaymentPluginPlatform
    with MockPlatformInterfaceMixin
    implements MeshulamPaymentPluginPlatform {

  @override
  Future<String?> getPlatformVersion() => Future.value('42');
  
  @override
  Future<String?> createPaymentCall() {
    // TODO: implement createPaymentCall
    throw UnimplementedError();
  }
}

void main() {
  final MeshulamPaymentPluginPlatform initialPlatform = MeshulamPaymentPluginPlatform.instance;

  test('$MethodChannelMeshulamPaymentPlugin is the default instance', () {
    expect(initialPlatform, isInstanceOf<MethodChannelMeshulamPaymentPlugin>());
  });

  test('getPlatformVersion', () async {
    MeshulamPaymentPlugin meshulamPaymentPlugin = MeshulamPaymentPlugin();
    MockMeshulamPaymentPluginPlatform fakePlatform = MockMeshulamPaymentPluginPlatform();
    MeshulamPaymentPluginPlatform.instance = fakePlatform;

    expect(await meshulamPaymentPlugin.getPlatformVersion(), '42');
  });
}
