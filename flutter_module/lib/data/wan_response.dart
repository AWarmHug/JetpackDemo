import 'package:flutter_module/data/article.dart';
import 'package:flutter_module/generated/json/base/json_convert_content.dart';

import 'list_data.dart';

class JsonEx {
  JsonEx.fromJson(Map<String, dynamic> json);

  Map<String, dynamic> toJson() {
    return {};
  }
}

class WanResponse<T> {
  T data;
  int errorCode;
  String errorMsg;

  WanResponse({this.data, this.errorCode, this.errorMsg});

  WanResponse.fromJson(Map<String, dynamic> json) {
    data = json['data'] != null ? JsonConvert.fromJsonAsT(json['data']) : null;
    if (data == null) {
      var type = T.toString();
      if (type == "ListData<Article>") {
        data = json["data"] != null
            ? new ListData<Article>.fromJson(json['data']) as T
            : null;
      }
    }
    errorCode = json['errorCode'];
    errorMsg = json['errorMsg'];
  }

  Map<String, dynamic> toJson() {
    final Map<String, dynamic> data = new Map<String, dynamic>();
    if (this.data != null) {
      data['data'] = this.data;
    }
    data['errorCode'] = this.errorCode;
    data['errorMsg'] = this.errorMsg;
    return data;
  }
}
