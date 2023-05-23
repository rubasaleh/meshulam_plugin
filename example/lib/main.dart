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
    Map<String, String> params = {
      "reservationId" : "1230009",
      "pageCode" : "52cbbd366421",
      "userId" : "356b9a13afe7f7fb",
      "email" : "rubsaleh1@gmail.com",
      "phone" : "0532231523",
      "name" : "Ruba Saleh",
      "sum" : "1"
    };
    try {
      paymentMsg =
          await _meshulamPaymentPlugin.createPaymentCall(params) ?? 'Unknown platform version';
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
