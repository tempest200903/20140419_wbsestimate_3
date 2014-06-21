package com.github.tempest200903;

import org.apache.wicket.util.tester.WicketTester;
import org.junit.Before;
import org.junit.Test;

/**
 * Simple test using the WicketTester
 */
public class TopPageTest {

	private WicketTester tester;

	@Test
	public void homepageRendersSuccessfully() {
		// start and render the test page
		tester.startPage(TopPage.class);

		// assert rendered page class
		tester.assertRenderedPage(TopPage.class);
	}

	@Before
	public void setUp() {
		tester = new WicketTester(new WicketApplication());
	}
}
