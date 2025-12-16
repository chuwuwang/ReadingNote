import 'package:flutter_test/flutter_test.dart';
import 'package:minimal_flutter/main.dart';

void main() {
  test('greet returns greeting', () {
    expect(greet('World'), 'Hello, World!');
  });
}
