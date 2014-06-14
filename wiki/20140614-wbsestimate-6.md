# 20140614-wbsestimate-6 #

- [[20140608-wbsestimate-4]] の続き。
- [2014-06-14 土 22:00] begin

## links ##

- comefrom: [[2014-04-19-110855-wbsestimate-3]]

## wichet NotSerializableException ##

- Caused by: java.io.NotSerializableException: com.googlecode.mjorm.MongoDaoImpl
- ProjectPage コンストラクタ引数である final MongoDao dao を、インナークラスから参照すると、
  インナークラスのフィールドに dao を代入することになる。このフィールドには transient を
  つけられないので、 NotSerializableException になる。
- ProjectPage に transient フィールドを用意して、いったんそこに代入する。
  private transient MongoDao dao_;
  ProjectPage(final MongoDao dao) { dao_ = dao };
  インナークラスからは transient フィールドである dao_ を参照すればよい。

## MongoDB ##

- MongoDB でデータベースから得たオブジェクトを変更して保存したい。どうすればいいのか？

### mongo-java-orm ###

- https://code.google.com/p/mongo-java-orm/
- 明示的に説明が書いていないので試行錯誤した。
- dao_.updateObject(projectCollection, id, projectModel); ではだめ。
- dao_.savePartialObject(projectCollection, id, "name", projectName, true); は成功した。
- mongo-java-orm はドキュメント、サンプルコードが少なすぎる。
- update するためにわざわざ "name" といった風にフィールド名を指定するのは面倒。
- かといって、 MondoDB Java Driver を直接書くのは手間かかりすぎる。
- savePartialObject をモデルクラスに閉じ込められないか？

### morphia ###

- http://dayafterneet.blogspot.jp/2012/02/mongodbjavamorphia.html
- BasicDAOを継承するDAOを作成するだけで、findやsaveなど各種操作をこのDAO経由で行なうことができます。

    ```
    > BlogEntry myBlogEntry = blogEntryDAO.get(blogEntryId);
    > // update it
    > myBlogEntry.setTitle("My Blog Entry");
    > blogEntryDAO.save(myBlogEntry);
    ```

- save メソッドを呼ぶだけ。
- https://code.google.com/p/morphia/
    - morphia - A type-safe java library for MongoDB - Google Project Hosting
- https://github.com/mongodb/morphia/wiki
    - Home · mongodb/morphia Wiki
    
