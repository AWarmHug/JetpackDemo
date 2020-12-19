import 'package:flutter_module/generated/json/base/json_convert_content.dart';

class Article with JsonConvert<Article> {
	String apkLink;
	int audit;
	String author;
	bool canEdit;
	int chapterId;
	String chapterName;
	bool collect;
	int courseId;
	String desc;
	String descMd;
	String envelopePic;
	bool fresh;
	int id;
	String link;
	String niceDate;
	String niceShareDate;
	String origin;
	String prefix;
	String projectLink;
	int publishTime;
	int realSuperChapterId;
	int selfVisible;
	int shareDate;
	String shareUser;
	int superChapterId;
	String superChapterName;
	List<Article1Tag> tags;
	String title;
	int type;
	int userId;
	int visible;
	int zan;
}

class Article1Tag with JsonConvert<Article1Tag> {
	String name;
	String url;
}
