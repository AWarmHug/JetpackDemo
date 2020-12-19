import 'package:flutter_module/generated/json/base/json_convert_content.dart';

import 'wan_response.dart';

class ListData<T extends JsonConvert<T>> {
  int curPage;
  List<T> datas;
  int offset;
  bool over;
  int pageCount;
  int size;
  int total;

  ListData(
      {this.curPage,
      this.datas,
      this.offset,
      this.over,
      this.pageCount,
      this.size,
      this.total});

  ListData.fromJson(Map<String, dynamic> json) {
    curPage = json['curPage'];
    if (json['datas'] != null) {
      datas = new List<T>();
      json['datas'].forEach((v) {
        datas.add(JsonConvert.fromJsonAsT<T>(v));
      });
    }
    offset = json['offset'];
    over = json['over'];
    pageCount = json['pageCount'];
    size = json['size'];
    total = json['total'];
  }

  Map<String, dynamic> toJson() {
    final Map<String, dynamic> data = new Map<String, dynamic>();
    data['curPage'] = this.curPage;
    if (this.datas != null) {
      data['datas'] = this.datas.map((v) => v.toJson()).toList();
    }
    data['offset'] = this.offset;
    data['over'] = this.over;
    data['pageCount'] = this.pageCount;
    data['size'] = this.size;
    data['total'] = this.total;
    return data;
  }
}
