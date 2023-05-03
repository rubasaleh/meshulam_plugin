import 'package:flutter/services.dart';
import 'package:flutter_test/flutter_test.dart';
import 'package:meshulam_payment_plugin/meshulam_payment_plugin_method_channel.dart';

void main() {
  MethodChannelMeshulamPaymentPlugin platform = MethodChannelMeshulamPaymentPlugin();
  const MethodChannel channel = MethodChannel('meshulam_payment_plugin');

  TestWidgetsFlutterBinding.ensureInitialized();

  setUp(() {
    channel.setMockMethodCallHandler((MethodCall methodCall) async {
      return '42';
    });
  });

  tearDown(() {
    channel.setMockMethodCallHandler(null);
  });

  test('getPlatformVersion', () async {
    expect(await platform.getPlatformVersion(), '42');
  });
}
