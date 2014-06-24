package com.github.tempest200903;

import java.util.logging.Logger;

import org.apache.wicket.Component;
import org.apache.wicket.Page;
import org.apache.wicket.core.request.handler.IPageProvider;
import org.apache.wicket.core.request.handler.PageProvider;
import org.apache.wicket.util.tester.WicketTester;
import org.junit.Before;
import org.junit.Test;

/**
 * Simple test using the WicketTester
 */
public class ProjectPageTest {

	private static final Logger myLogger = Logger
			.getLogger(ProjectPageTest.class.getName());

	private WicketTester tester;

	@Before
	public void setUp() throws Exception {
		tester = new WicketTester(new WicketApplication());
	}

	@Test
	public void testProjectPage() {
		myLogger.info("topPageClickCreateProject begin");
		// setup
		ProjectModel projectModel = new ProjectModel();
		projectModel.setTitle("title123");
		Page page = new ProjectPage(projectModel);
		IPageProvider pageProvider = new PageProvider(page);
		ProjectPage projectPage = (ProjectPage) tester.startPage(pageProvider);

		tester.assertRenderedPage(ProjectPage.class);

		// exercise
		Component createProjectLink = tester
				.getComponentFromLastRenderedPage("topPageLink");
		tester.clickLink(createProjectLink);

		// verify
		tester.assertRenderedPage(TopPage.class);

		myLogger.info("topPageClickCreateProject end");
	}

}
