import 'dart:convert';

import 'package:dio/dio.dart';
import 'package:flutter_module/data/list_data.dart';
import 'package:flutter_module/generated/json/base/json_convert_content.dart';

import 'data/article.dart';
import 'data/wan_response.dart';

const BASE_URL = "https://www.wanandroid.com/";

var dio = Dio(BaseOptions(baseUrl: BASE_URL));

Future<WanResponse<ListData<Article>>> getUserArticle() {
  return dio.get("user_article/list/0/json").then((value) {
    WanResponse<ListData<Article>> data =WanResponse<ListData<Article>>.fromJson(value.data);
    return data;
  });
}
