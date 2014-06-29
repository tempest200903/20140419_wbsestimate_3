package com.github.tempest200903;

import java.util.logging.Logger;

import org.apache.wicket.Component;
import org.apache.wicket.Page;
import org.apache.wicket.core.request.handler.IPageProvider;
import org.apache.wicket.core.request.handler.PageProvider;
import org.apache.wicket.markup.html.form.Button;
import org.apache.wicket.markup.html.form.TextField;
import org.apache.wicket.markup.html.link.Link;
import org.apache.wicket.model.Model;
import org.apache.wicket.util.tester.FormTester;
import org.apache.wicket.util.tester.WicketTester;
import org.junit.Before;
import org.junit.Test;

import static org.hamcrest.CoreMatchers.is;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.Assert.assertNotNull;

/**
 * Simple test using the WicketTester
 */
public class ProjectPageTest {

	private static final Logger myLogger = Logger
			.getLogger(ProjectPageTest.class.getName());

	private WicketTester tester;

	private ProjectModel createProjectModel(String title) {
		ProjectModel projectModel = new ProjectModel();
		projectModel.setTitle(title);
		return projectModel;
	}

	private ProjectPage createProjectPage(ProjectModel projectModel) {
		Page page = new ProjectPage(projectModel);
		IPageProvider pageProvider = new PageProvider(page);
		ProjectPage projectPage = (ProjectPage) tester.startPage(pageProvider);
		return projectPage;
	}

	@Before
	public void setUp() throws Exception {
		tester = new WicketTester(new WicketApplication());
	}

	@Test
	public void testProjectPage() {
		myLogger.info("topPageClickCreateProject begin");
		// setup
		String title = "title123";
		ProjectModel projectModel = createProjectModel(title);
		ProjectPage projectPage = createProjectPage(projectModel);
		assertNotNull(projectPage);
		tester.assertRenderedPage(ProjectPage.class);

		// exercise
		Component createProjectLink = tester
				.getComponentFromLastRenderedPage("topPageLink");
		tester.clickLink(createProjectLink);

		// verify
		tester.assertRenderedPage(TopPage.class);

		myLogger.info("topPageClickCreateProject end");
	}

	@Test
	public void testProjectTitleSubmit() {
		myLogger.info("begin");
		// setup
		String title = "title123";
		ProjectModel projectModel = createProjectModel(title);

		// exercise
		ProjectPage projectPage = createProjectPage(projectModel);

		// verify
		assertNotNull(projectPage);
		tester.assertRenderedPage(ProjectPage.class);
		@SuppressWarnings("unchecked")
		TextField<String> projectNameTextField = (TextField<String>) tester
				.getComponentFromLastRenderedPage("form1:projectTitle");
		{
			String actual = projectNameTextField.getModelObject();
			String expected = title;
			assertThat(actual, is(expected));
		}

		// exercise
		String expected = "title456";
		@SuppressWarnings("unused")
		Button projectSubmitButton = (Button) tester
				.getComponentFromLastRenderedPage("form1:projectSubmit");
		Link projectButton = (Link) tester
				.getComponentFromLastRenderedPage("projectButton");
		// projectNameTextField.setModelObject(expected);
		// projectNameTextField.setDefaultModelObject(expected);
		// projectNameTextField.setModelValue(new String[]{expected});
		// projectNameTextField.setConvertedInput(expected);
		// projectNameTextField.setDefaultModel(Model.of(expected));
		projectNameTextField.getModel().setObject(expected);
		boolean b = true;
		if (b) {
			 tester.submitForm("form1");
			 FormTester formTester = tester.newFormTester("form1", false);
			 formTester.setValue("projectTitle", expected);
			 formTester.submit();
		} else {
			tester.executeListener(projectButton);
		}


		// verify
		ProjectModel projectModel2 = projectPage.getProjectModel();
		String actual = projectModel2.getTitle();
		assertThat(actual, is(expected));

		myLogger.info("end");
	}

}
