# 20140621-wbsestimate-7 #

## timelog ##

- [2014-06-21 土 18:14] begin
- [2014-06-21 土 18:53] end
- [2014-06-21 土 20:45] begin
- [2014-06-21 土 23:21] end

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

## save wiki ##

- F:\goat-pc-data\ecworkspace\20140621-wbsestimate-7\wiki\20140621-wbsestimate-7.md
- https://github.com/tempest200903/20140419_wbsestimate_3/blob/morphia_first/wiki/20140621-wbsestimate-7.md

