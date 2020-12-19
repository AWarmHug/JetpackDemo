import 'dart:ui';

import 'package:flutter/material.dart';
import 'package:flutter_module/mine.dart';
import 'package:flutter_module/square.dart';

void main() => runApp(_widgetForRoute(window.defaultRouteName));

Widget _widgetForRoute(String route) {
  print('route = $route');
  switch (route) {
    case "square":
      return SquareApp();
    case 'mine':
      return MineApp();
    // case 'route2':
    //   return _route2();
    default:
      return SquareApp();
  }
}

