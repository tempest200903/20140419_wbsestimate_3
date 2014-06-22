package com.github.tempest200903;

import java.util.logging.Logger;

import org.apache.wicket.Component;
import org.apache.wicket.model.IModel;
import org.apache.wicket.model.Model;
import org.apache.wicket.util.tester.WicketTester;
import org.junit.Before;
import org.junit.Test;

import static org.hamcrest.CoreMatchers.is;

import static org.junit.Assert.*;

/**
 * Simple test using the WicketTester
 */
public class TopPageTest {

	private static final Logger myLogger = Logger.getLogger(TopPageTest.class
			.getName());

	private WicketTester tester;

	@Before
	public void setUp() {
		tester = new WicketTester(new WicketApplication());
	}

	@Test
	public void topPageClickCreateProject() {
		// start and render the test page
		TopPage topPage = tester.startPage(TopPage.class);

		// assert rendered page class
		tester.assertRenderedPage(TopPage.class);

		int sizeBeforeClick = topPage.projectModelList.size();

		Component createProjectLink = tester
				.getComponentFromLastRenderedPage("createProject");
		myLogger.info("createProjectLink =: " + createProjectLink);
		tester.clickLink(createProjectLink);
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

		HomePage homePage = tester.startPage(HomePage.class);
		TopPage topPage2 = tester.startPage(TopPage.class);
		tester.assertRenderedPage(TopPage.class);
		{
			// projectModelList を永続化していることをテストする。
			int expected = sizeBeforeClick + 1;
			int actual = topPage2.projectModelList.size();
			assertThat(actual, is(expected));
		}
	}

	@Test
	public void topPageRendersSuccessfully() {
		// start and render the test page
		tester.startPage(TopPage.class);

		// assert rendered page class
		tester.assertRenderedPage(TopPage.class);
	}

}
