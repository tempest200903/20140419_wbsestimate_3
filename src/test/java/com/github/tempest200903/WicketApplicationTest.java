package com.github.tempest200903;

import org.apache.wicket.request.mapper.parameter.PageParameters;
import org.apache.wicket.util.tester.WicketTester;
import org.junit.Before;
import org.junit.Test;

public class WicketApplicationTest {

	private WicketTester tester;

	@Before
	public void setUp() throws Exception {
		tester = new WicketTester(new WicketApplication());
	}

	@Test
	public void testWbsEstimate() {
		// 引数に渡されたページのテストが開始（ページが表示）される。
		tester.startPage(new WbsEstimate(new PageParameters()));
	}

}
