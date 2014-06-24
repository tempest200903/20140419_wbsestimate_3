# 20140621-wbsestimate-7 #

## links ##

- comefrom: [[20140614-wbsestimate-6]]

## 旧リポジトリ ##

- F:\goat-pc-data\ecworkspace\20140419_wbsestimate_3

## MJORM (mongo-java-orm) を導入する直前まで巻き戻し ##

- MJORM (mongo-java-orm) を導入する直前まで巻き戻して、別リポジトリで管理する。
- https://github.com/tempest200903/20140419_wbsestimate_3
- github で log (履歴)を見るにはどうすればいい？
- 17 commits をクリックすると https://github.com/tempest200903/20140419_wbsestimate_3/commits/master に遷移する。履歴。
- F:\goat-pc-data\screenshot\2014\screenshot-g-000105.jpg
- F:\goat-pc-data\screenshot\2014\screenshot-g-000106.jpg
- いちいち "Browse code" をクリックしないとファイル名や add/modify/delete を見られない。不便？
- grep "import com.googlecode.mjorm" これが初めて登場する commit を探したい。
- git log -p で diff も出力する。
- git log -p | grep -e commit -e "import com.googlecode.mjorm"

    > commit c558bf045ac70bc9ab67294cc94a224ceea0286a
    > +import com.googlecode.mjorm.annotations.Entity;
    > +import com.googlecode.mjorm.annotations.Id;
    > +import com.googlecode.mjorm.annotations.Property;
    > commit 10fe80799bded770f6cd2a9cbd8f72acf11bb5ce

- 10fe80799bded770f6cd2a9cbd8f72acf11bb5ce
    - 未登場
- c558bf045ac70bc9ab67294cc94a224ceea0286a
    - 初登場。

## 新リポジトリ ##

- bash
- cd F:\goat-pc-data\ecworkspace\20140621-wbsestimate-7-temp
- git clone https://github.com/tempest200903/20140419_wbsestimate_3
- mv 20140419_wbsestimate_3 20140621-wbsestimate-7
- cd .. 
- mv 20140621-wbsestimate-7-temp/20140621-wbsestimate-7 ./
- cd 20140621-wbsestimate-7
- git checkout 10fe80799bded770f6cd2a9cbd8f72acf11bb5ce
- git log | less
    - commit 10fe80799bded770f6cd2a9cbd8f72acf11bb5ce
- cf. http://git-scm.com/book/ja/Git-%E3%81%AE%E3%83%96%E3%83%A9%E3%83%B3%E3%83%81%E6%A9%9F%E8%83%BD-%E3%83%96%E3%83%A9%E3%83%B3%E3%83%81%E3%81%A8%E3%81%AF
    - Git - ブランチとは
- git branch morphia_first
- git checkout morphia_first
- git status
    - On branch morphia_first
- echo "use morphia instead of mongo-java-orm" >> README.md
- git add . ; git commit -m "use morphia instead of mongo-java-orm" .
- git push
    - まだリモートリポジトリには反映されていない。
- 上記はローカルリポジトリにブランチを作っただけ。
- リモートリポジトリにブランチを作りたい。どうやればいい？
- http://git-scm.com/book/ja/Git-%E3%81%AE%E3%83%96%E3%83%A9%E3%83%B3%E3%83%81%E6%A9%9F%E8%83%BD-%E3%83%96%E3%83%A9%E3%83%B3%E3%83%81%E3%81%A8%E3%81%AF
    - つまり git push (remote) (branch) を実行します。
- git push morphia_first morphia_first

    > $ git push morphia_first morphia_first
    > fatal: 'morphia_first' does not appear to be a git repository
    > fatal: Could not read from remote repository.
    > 
    > Please make sure you have the correct access rights
    > and the repository exists.

- browse-url https://github.com/tempest200903/20140419_wbsestimate_3
    - branch をクリックする。
    - ブランチ名を入力する。 Create branch をクリックする。
    - file:F:\goat-pc-data\screenshot\2014\screenshot-g-000107.jpg

        > $ git fetch
        > From https://github.com/tempest200903/20140419_wbsestimate_3
        >  * [new branch]      morphia_first -> origin/morphia_first
        > 
        > $ git push morphia_first morphia_first
        > fatal: 'morphia_first' does not appear to be a git repository
        > fatal: Could not read from remote repository.
        > 
        > Please make sure you have the correct access rights
        > and the repository exists.

- トップページで create branch したが、誤り。
- commit 10fe80799b から分岐したい。
- master branch から commit 10fe80799b を選択する。
- tree: 10fe80799b をクリック。
- ブランチ名を入力する。 Create branch をクリックする。
- file:F:\goat-pc-data\screenshot\2014\screenshot-g-000109.jpg
- git fetch
- git push origin/morphia_first morphia_first

    > $ git fetch
    > From https://github.com/tempest200903/20140419_wbsestimate_3
    >  + a4f2797...10fe807 morphia_first -> origin/morphia_first  (forced update)
    > 
    > $ git push origin/morphia_first morphia_first
    > fatal: 'origin/morphia_first' does not appear to be a git repository
    > fatal: Could not read from remote repository.
    > 
    > Please make sure you have the correct access rights
    > and the repository exists.

- git push

    > $ git push
    > To https://github.com/tempest200903/20140419_wbsestimate_3
    >    10fe807..d81fa3c  morphia_first -> morphia_first

- https://github.com/tempest200903/20140419_wbsestimate_3/tree/morphia_first
- file:F:\goat-pc-data\screenshot\2014\screenshot-g-000110.jpg
- リモートブランチに commit 成功した。
- ちなみに、ここで "Compare & pull request" をクリックすると、 master と branch を比較する diff を見ながら pull request を作成する画面に遷移する。
- https://github.com/tempest200903/20140419_wbsestimate_3/tree/morphia_first
- 今回は pull request しない。

## Morphia を導入する ##

- 復習 [[20140615-Morphia]]
    - http://dayafterneet.blogspot.jp/2012/02/mongodbjavamorphia.html
- https://github.com/tempest200903/20140615-Morphia/blob/f6e25fa953c15e11e746af5f2f9f6539544d50b0/pom.xml
- F:\goat-pc-data\ecworkspace\20140621-wbsestimate-7\pom.xml
- mvn validate
- mvn eclipse:eclipse
- Eclipse; Import; Import Projects; F:\goat-pc-data\ecworkspace\20140621-wbsestimate-7
- 試しに Open Type してみる。
    - com.mongodb.Mongo
    - org.bson.types.ObjectId
    - com.google.code.morphia.Morphia
- 3つとも ok.
- git add . ; git commit -m "pom.xml; add dependency mongo-java-driver, morphia; add repository http://morphia.googlecode.com/svn/mavenrepo" .
- git push
- https://github.com/tempest200903/20140419_wbsestimate_3/commit/231aa64913455f45ddaafc42a3c922f2d410af1d
- https://github.com/tempest200903/20140419_wbsestimate_3/blob/f653ef0c47f7c0e4b4818cf376a0d316af9c7f14/run.xml
    - jetty:run をショートカット Alt+Pause で起動できるようにした。
- git add . ; git commit -m "jetty:run をショートカットで起動できるようにした。" . ; git push
- https://github.com/tempest200903/20140419_wbsestimate_3/commit/f653ef0c47f7c0e4b4818cf376a0d316af9c7f14
- 動作確認。 jetty:run したあと、 browse-url http://localhost:8080/?0
- file:F:\goat-pc-data\screenshot\2014\screenshot-g-000112.jpg
- Project List に add という文字列のアンカーが1個置いてあるだけ。アンカーをクリックしても変化しない。

## リファクタリング ProjectList を TopPage に変更 ##

- https://github.com/tempest200903/20140419_wbsestimate_3/commit/2628574ef275dd3c277ec5147467b9b2f56c8d83

## TopPage で Add Project をクリックしたら Project を1個追加する ##

- [2014-06-21 土 21:53] commit https://github.com/tempest200903/20140419_wbsestimate_3/commit/be6556053dc146c7194f9557fcba38476b15bd20

## 「TopPage で Add Project をクリックしたら Project を1個追加する」をテストする ##

- cf. [[2014-04-19-110855-wbsestimate-3]]
- 「TopPage で Add Project をクリックしたら Project を1個追加する」をテストする。 TopPageTest.java を追加。
- [2014-06-21 土 21:58] commit https://github.com/tempest200903/20140419_wbsestimate_3/commit/29b7d1154efb0753db33a475220e4d9a28a42d34

## source format ##

- [2014-06-21 土 22:03] commit https://github.com/tempest200903/20140419_wbsestimate_3/commit/a6c24aa69a19ffab229b4b5d854068d833d0f203

## topPageClickCreateProject() を追加。 TopPage で Create Project をクリックする。 ##

- [2014-06-21 土 22:10] commit https://github.com/tempest200903/20140419_wbsestimate_3/commit/94af3a0f5eeae2800f013e0ab4d230599f3f72a2

## java.util.logging でログ出力する。 ##

- [2014-06-21 土 22:20] commit https://github.com/tempest200903/20140419_wbsestimate_3/commit/399263a11a3c983e7b5f6a21b85e2e0991f7030f

## clickLink した後、 projectModelList に要素が1個増えていることを assert する。 ##

- [2014-06-21 土 22:27] commit https://github.com/tempest200903/20140419_wbsestimate_3/commit/593b48df63fd5c67205aed8ec0f033cad5b9c4ce

## ProjectModel にフィールド name を追加する。 ##

- [2014-06-21 土 22:29] commit https://github.com/tempest200903/20140419_wbsestimate_3/commit/798e24ab827967b9367cf41cb98599d4138e9190

## ProjectModel を Morphia で永続化可能にする。 ##

- cf. [[20140615-Morphia]]
- http://dayafterneet.blogspot.jp/2012/02/mongodbjavamorphia.html
- フィールド int id は必要だろうか？ 今のところ思いつかない。必要性が見えたら入れる。
- export する際に、変化しない識別子があると都合がいい。 name は変化する。 id は変化しない。
- もし id を入れるなら、自分で new したときの id と、データベースから復元したときの id が重複しないような措置が要る。
- [2014-06-21 土 23:02] commit https://github.com/tempest200903/20140419_wbsestimate_3/commit/9544b13d88030e1be4a68788f4df54c9c0bc9597

## TopPageTest projectModelList を永続化していることをテストする。 ##

- [2014-06-21 土 23:18] commit https://github.com/tempest200903/20140419_wbsestimate_3/commit/eda37657b90f5772091acc0e220349f99ee933d2
- git push
- 厳密に永続化テストするには、一度 JavaVM プロセスを終了して、再度プログラムを起動してから、復元できているかを検査するべき。 JUnit でこれを行うのはやや手間がかかるので、今回は簡略検査だけにする。

## TopPageTest new する時に projectModelList を表示する。 ##

- file:F:\goat-pc-data\screenshot\2014\screenshot-g-000113.jpg
- 初回アクセス時に projectModelList を表示する。
- 課題
    - create project をクリックしても表示が変化しない。内部では増えている。
    - reload しても表示が変化しない。
    - mongodb オブジェクトと morphia オブジェクトの初期化は class TopPage ではなく、 class WicketApplication で行うべき。
- [2014-06-22 日 18:53] commit https://github.com/tempest200903/20140419_wbsestimate_3/commit/899f700a74c0f4282bf9277e54435348d036d80c

## WicketApplication で mongodb オブジェクトと morphia オブジェクトを初期化する。 ##

- WicketApplication インスタンスを得るにはどうすればいいのか？
    - 多分、これ。 org.apache.wicket.protocol.http.WebApplication.get()
- [2014-06-22 日 20:42] commit https://github.com/tempest200903/20140419_wbsestimate_3/commit/57dfcc6573c476a0d001897f1232652feaa21437

## create project をクリックしたとき表示を更新する。 ##

- com.github.tempest200903.TopPage.TopPage(...).new Link() {...}.onClick()
    - projectModelList.add(projectModel) と同時に、
      wicketProjectModelList.add(model) も行う。
- [2014-06-22 日 20:55] commit https://github.com/tempest200903/20140419_wbsestimate_3/commit/14668b92cd4210d48dd02a18cf1e9cd9357440c2

## dependency 追加 org.apache.commons commons-collections4 ##

- F:\goat-pc-data\ecworkspace\20140621-wbsestimate-7\pom.xml
    - dependency 追加
        - http://commons.apache.org/proper/commons-collections/project-summary.html
        - GroupId	org.apache.commons
        - ArtifactId	commons-collections4

- [2014-06-22 日 23:25] commit https://github.com/tempest200903/20140419_wbsestimate_3/commit/9de58046f0a4a8dea64f6f3ef17eed5db3fdf4a9

## delete project をクリックしたとき projectModelList から除去して表示を更新する。 ##

- RefreshingView から子 component を探し出すことはできたが、やや手間がかかる。
    - see com.github.tempest200903.TopPageTest.findDeleteProjectLink(ProjectListView)
- 課題
    - delete project をクリックしたとき画面では project list から item が1個減っている。データベースには保存していない。再起動したら削除したつもりの item が残っている。
- [2014-06-22 日 23:30] commit https://github.com/tempest200903/20140419_wbsestimate_3/commit/76c912f5aefbfc32de617deb5083ac4b6e34baae

## save wiki ##

- F:\goat-pc-data\ecworkspace\20140621-wbsestimate-7\wiki\20140621-wbsestimate-7.md
- https://github.com/tempest200903/20140419_wbsestimate_3/blob/morphia_first/wiki/20140621-wbsestimate-7.md
- [2014-06-22 日 23:32] commit

## Morphia DAO を使う。 ##

- http://dayafterneet.blogspot.jp/2012/02/mongodbjavamorphia.html
- [2014-06-23 月 19:16] commit https://github.com/tempest200903/20140419_wbsestimate_3/commit/c1ade8a18874ba85819df3c34dd7b1630529197c

## delete project をクリックしたときデータベースからも削除する。 ##

- save() で getDatastore().save(projectModelList); としてる。削除対象オブジェクトを保持しておいて、ここで削除する。
- [2014-06-23 月 22:57] commit https://github.com/tempest200903/20140419_wbsestimate_3/commit/865573790937cf3df0b33b0b0be38c4293cad497

## TopPage に project の個数を表示する。 ##

- TopPage.addProjectListSizeLabel()
- [2014-06-23 月 23:05] commit https://github.com/tempest200903/20140419_wbsestimate_3/commit/6a8b218fd027d9bc9238a635d79ea038d3461c67

## リファクタリング Extract Method TopPage コンストラクタ ##

- [2014-06-23 月 23:08] commit https://github.com/tempest200903/20140419_wbsestimate_3/commit/f04d7612f48c20df26e157407d35a1acec7d148f

## read project link をクリックしたら ProjectPage に遷移する。 ##

- [2014-06-23 月 23:26] commit https://github.com/tempest200903/20140419_wbsestimate_3/commit/926c289f2517da922914d6bb8b6abf241436a6a1

## ProjectPage から TopPage に遷移するアンカーを設置する。 ##

- [2014-06-24 火 23:58] commit

## topPageLink をクリックしたら TopPage に遷移する。 ##

- プロダクトコードでは、引数1個コンストラクタを使う。
    - 例 com.github.tempest200903.TopPage.ReadProjectLink.onClick() { new ProjectPage(item); }
- テストコードでは、コンストラクタではなく startPage() を使う。
    - 例 tester.startPage(ProjectPage.class);
- 疑問。テストコードで引数1個コンストラクタをテストしたい場合、どうすればいいのか？
- 回答。 startPage(IPageProvider) を使えばよい。

    ```
    > ProjectModel projectModel = new ProjectModel();
    > 		projectModel.setTitle("title123");
    > 		Page page = new ProjectPage(projectModel);
    > 		IPageProvider pageProvider = new PageProvider(page);
    > 		ProjectPage projectPage = (ProjectPage) tester.startPage(pageProvider);
    ```

- [2014-06-25 水 00:30] commit

## timelog ##

- [2014-06-21 土 18:14] begin
- [2014-06-21 土 18:53] end
- [2014-06-21 土 20:45] begin
- [2014-06-21 土 23:21] end
- [2014-06-22 日 18:26] begin
- [2014-06-22 日 18:53] end
- [2014-06-22 日 20:25] begin
- [2014-06-22 日 20:56] end
- [2014-06-22 日 22:23] begin
- [2014-06-22 日 23:30] end
- [2014-06-23 月 18:25] begin
- [2014-06-23 月 19:17] end
- [2014-06-23 月 22:48] begin
- [2014-06-23 月 23:27] end
- [2014-06-24 火 23:48] begin
