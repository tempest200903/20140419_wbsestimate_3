package com.github.tempest200903;

import java.util.List;
import java.util.logging.Logger;

import org.apache.commons.collections4.IteratorUtils;
import org.apache.wicket.Component;
import org.apache.wicket.markup.html.link.Link;
import org.apache.wicket.markup.repeater.Item;
import org.apache.wicket.model.IModel;
import org.apache.wicket.util.tester.WicketTester;
import org.junit.Before;
import org.junit.Test;

import static org.hamcrest.CoreMatchers.is;

import static org.junit.Assert.assertThat;

/**
 * Simple test using the WicketTester
 */
public class TopPageTest {

	private static final Logger myLogger = Logger.getLogger(TopPageTest.class
			.getName());

	private WicketTester tester;

	private Link<?> findDeleteProjectLink(
			TopPage.ProjectListView projectListView) {
		Link<?> deleteProjectLink = null;
		List<Component> componentList = IteratorUtils.toList(projectListView
				.iterator());
		for (Component component : componentList) {
			if (component instanceof Item) {
				Item<?> item = (Item<?>) component;
				Component deleteProjectItem = item.get("deleteProject");
				if (deleteProjectItem instanceof Link) {
					deleteProjectLink = (Link<?>) deleteProjectItem;
					break;
				}
			}
		}
		return deleteProjectLink;
	}

	@Before
	public void setUp() {
		tester = new WicketTester(new WicketApplication());
	}

	@Test
	public void topPageClickCreateProject() {
		myLogger.info("topPageClickCreateProject begin");
		// setup

		// start and render the test page
		TopPage topPage = tester.startPage(TopPage.class);

		// assert rendered page class
		tester.assertRenderedPage(TopPage.class);

		int sizeBeforeClick = topPage.projectModelList.size();

		// exercise
		Component createProjectLink = tester
				.getComponentFromLastRenderedPage("createProject");
		tester.clickLink(createProjectLink);

		// verify
		{
			// projectModelList に要素が1個増えている
			int expected = sizeBeforeClick + 1;
			int actual = topPage.projectModelList.size();
			assertThat("projectModelList.size()", actual, is(expected));
		}
		{
			// wicketProjectModelList に要素が1個増えている
			int expected = sizeBeforeClick + 1;
			int actual = topPage.wicketProjectModelList.size();
			assertThat("wicketProjectModelList.size()", actual, is(expected));
		}
		{
			// projectModelList 最後の要素の title を wicketProjectModelList
			// 最後の要素に表示している。
			ProjectModel lastProjectModel = topPage.projectModelList
					.get(topPage.projectModelList.size() - 1);
			IModel<ProjectModel> lastWicketProjectModel = topPage.wicketProjectModelList
					.get(topPage.wicketProjectModelList.size() - 1);
			String expected = lastProjectModel.getTitle();
			String actual = lastWicketProjectModel.getObject().getTitle();
			assertThat("getTitle()", actual, is(expected));
		}

		tester.startPage(HomePage.class);
		TopPage topPage2 = tester.startPage(TopPage.class);
		tester.assertRenderedPage(TopPage.class);
		{
			// projectModelList を永続化していることをテストする。
			int expected = sizeBeforeClick + 1;
			int actual = topPage2.projectModelList.size();
			assertThat(actual, is(expected));
		}

		myLogger.info("topPageClickCreateProject end");
	}

	@Test
	public void topPageClickDeleteProject() {
		myLogger.info("topPageClickDeleteProject begin");

		// start and render the test page
		TopPage topPage = tester.startPage(TopPage.class);

		// assert rendered page class
		tester.assertRenderedPage(TopPage.class);

		int sizeBeforeClick = topPage.projectModelList.size();

		TopPage.ProjectListView projectListView = (TopPage.ProjectListView) tester
				.getComponentFromLastRenderedPage("projectList");
		Link<?> deleteProjectLink = findDeleteProjectLink(projectListView);
		tester.clickLink(deleteProjectLink);

		{
			// projectModelList に要素が1個減っている
			int expected = sizeBeforeClick - 1;
			int actual = topPage.projectModelList.size();
			assertThat("projectModelList.size()", actual, is(expected));
		}
		{
			// wicketProjectModelList に要素が1個減っている
			int expected = sizeBeforeClick - 1;
			int actual = topPage.wicketProjectModelList.size();
			assertThat("wicketProjectModelList.size()", actual, is(expected));
		}
		tester.startPage(HomePage.class);
		TopPage topPage2 = tester.startPage(TopPage.class);
		tester.assertRenderedPage(TopPage.class);
		{
			// projectModelList に要素が1個減っている
			int expected = sizeBeforeClick - 1;
			int actual = topPage2.projectModelList.size();
			assertThat("projectModelList.size()", actual, is(expected));
		}
		// TODO 削除した projectModel が projectModelList に含まれていないことを検査する。

		myLogger.info("topPageClickDeleteProject end");
	}

	@Test
	public void topPageRendersSuccessfully() {
		myLogger.info("topPageRendersSuccessfully begin");

		// start and render the test page
		tester.startPage(TopPage.class);

		// assert rendered page class
		tester.assertRenderedPage(TopPage.class);

		myLogger.info("topPageRendersSuccessfully end");
	}

}
