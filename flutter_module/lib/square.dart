import 'dart:ui';

import 'package:flutter/material.dart';
import 'data/article.dart';
import 'data/list_data.dart';
import 'data/wan_response.dart';
import 'repository.dart';

class SquareApp extends StatelessWidget {
  // This widget is the root of your application.
  @override
  Widget build(BuildContext context) {
    return MaterialApp(
      title: '广场',
      theme: ThemeData(
        primarySwatch: Colors.grey,
      ),
      home: MyHomePage(title: '广场'),
    );
  }
}

class MyHomePage extends StatefulWidget {
  MyHomePage({Key key, this.title}) : super(key: key);

  final String title;

  @override
  _MyHomePageState createState() => _MyHomePageState();
}

class _MyHomePageState extends State<MyHomePage> {
  WanResponse<ListData<Article>> data;

  @override
  Widget build(BuildContext context) {
    getUserArticle().then((value) {
      print(value);
      setState(() {
        this.data = value;
      });
    });

    return Scaffold(
      appBar: AppBar(
        title: Text(widget.title),
        backgroundColor: Colors.white,
        toolbarHeight: 48,
      ),
      body: ListView.builder(
        itemBuilder: (BuildContext context, int index) =>
            new ArticleItem(data.data.datas[index]),
        itemCount: data.data.datas.length,
      ),
    );
  }
}

class ArticleItem extends StatelessWidget {

  Article article;

  ArticleItem(this.article);

  @override
  Widget build(BuildContext context) {
    return Column(
      children: [
        Text(article.title),
        Text(article.title),
      ],
    );
  }
}
