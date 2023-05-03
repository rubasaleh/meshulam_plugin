import 'package:flutter/material.dart';
import 'dart:async';

import 'package:flutter/services.dart';
import 'package:meshulam_payment_plugin/meshulam_payment_plugin.dart';

void main() {
  runApp(const MyApp());
}

class MyApp extends StatefulWidget {
  const MyApp({super.key});

  @override
  State<MyApp> createState() => _MyAppState();
}

class _MyAppState extends State<MyApp> {
  String _platformVersion = 'Unknown';
  final _meshulamPaymentPlugin = MeshulamPaymentPlugin();

  @override
  void initState() {
    super.initState();
    initPlatformState();
  }

  Future<void> initPlatformState() async {
    String paymentMsg;
    try {
      paymentMsg =
          await _meshulamPaymentPlugin.createPaymentCall() ?? 'Unknown platform version';
    } on PlatformException {
      paymentMsg = 'Failed to get platform version.';
    }

    if (!mounted) return;

    setState(() {
      _platformVersion = paymentMsg;
    });
  }

  @override
  Widget build(BuildContext context) {
    return MaterialApp(
      home: Scaffold(
        appBar: AppBar(
          title: const Text('Plugin example app'),
        ),
        body: Center(
          child: Text('Running on: $_platformVersion\n'),
        ),
      ),
    );
  }
}