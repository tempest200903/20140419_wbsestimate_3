# 20140608-wbsestimate-4 #

- [[2014-04-19-110855-wbsestimate-3]] の続き。
- [2014-06-08 日 14:37] begin
- [2014-06-08 日 16:23] end
- [2014-06-08 日 17:39] begin
- [2014-06-08 日 18:50] end
- [2014-06-08 日 21:19] begin
- [2014-06-08 日 23:01] end

## links ##

- comefrom: [[2014-04-19-110855-wbsestimate-3]]

## 復習 ##

- F:\goat-pc-data\ecworkspace\20140419_wbsestimate_3

## 要件定義 ##

- Redmine に記録するか？ 準備時間かかるので、今回はスキップする。
- ER 図
- Project -> Version -> Phase -> Task
- Project CRUD
- Version CRUD
- Phase CRUD
- Task CRUD

## プロジェクト一覧画面 ##

- ProjectList
    - F:\goat-pc-data\ecworkspace\20140419_wbsestimate_3\src\main\java\com\github\tempest200903\ProjectList.java
    - [X] Create
    - [X] Read
    - [ ] Update
    - [X] Delete
- F:\goat-pc-data\ecworkspace\20140607-mongo-java-orm\pom.xml
- F:\goat-pc-data\ecworkspace\20140419_wbsestimate_3\pom.xml
- F:\goat-pc-data\ecworkspace\Wicket-tutorial-examples\ListViewExample
- F:\goat-pc-data\ecworkspace\20140607-Wicket-ListViewExample
- class RefreshingView

## ページ遷移して遷移先ページに情報を渡す ##

- http://blogs.yahoo.co.jp/jun_kogata/313025.html
- setResponsePage(new NextPage(StartPage.this));
- WebPage のサブクラスのコンストラクタで情報を渡す。
- setResponsePage(WebPage) で遷移する。

## 課題1 ##

- F:\goat-pc-data\ecworkspace\20140419_wbsestimate_3\src\main\java\com\github\tempest200903\ProjectPage.java
    - com.github.tempest200903.ProjectPage.ProjectPage(...).new Button() {...}.onSubmit()
    - TODO 変更後の　projectModel　オブジェクトをデータベースに保存する。
    - MongoDao.updateObject(collection, id, o); のようなデータベース手続きを View である WebPage のあちこちに書くのか？ 面倒だしスマートでない。
    - View でやるのは Model を更新して save() を呼ぶだけにしたい。

