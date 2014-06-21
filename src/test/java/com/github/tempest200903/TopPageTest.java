package com.github.tempest200903;

import java.util.logging.Logger;

import org.apache.wicket.Component;
import org.apache.wicket.util.tester.WicketTester;
import org.junit.Before;
import org.junit.Test;

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
		tester.startPage(TopPage.class);

		// assert rendered page class
		tester.assertRenderedPage(TopPage.class);

		Component createProjectLink = tester
				.getComponentFromLastRenderedPage("createProject");
		myLogger.info("createProjectLink =: " + createProjectLink);
		tester.clickLink(createProjectLink);
	}

	@Test
	public void topPageRendersSuccessfully() {
		// start and render the test page
		tester.startPage(TopPage.class);

		// assert rendered page class
		tester.assertRenderedPage(TopPage.class);
	}

}
