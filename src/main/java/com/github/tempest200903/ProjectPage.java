package com.github.tempest200903;

import org.apache.wicket.markup.html.WebPage;
import org.apache.wicket.markup.html.form.Button;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.form.TextField;
import org.apache.wicket.markup.html.link.Link;
import org.apache.wicket.markup.repeater.Item;
import org.apache.wicket.model.Model;

public class ProjectPage extends WebPage {

	private static final long serialVersionUID = 1L;

	public ProjectPage(Item<ProjectModel> item) {
		final ProjectModel projectModel = item.getModelObject();
		String projectName = projectModel.getName();

		Form<?> form = new Form<Object>("form") {
			private static final long serialVersionUID = 1L;

			protected void onSubmit() {
				info("Form.onSubmit executed");
			}
		};
		add(form);

		final TextField<String> projectNameTextField = new TextField<String>(
				"projectName", Model.of(projectName));
		form.add(projectNameTextField);

		Link<String> rootLink = new Link<String>("rootLink") {
			private static final long serialVersionUID = 1L;

			@Override
			public void onClick() {
				setResponsePage(TopPage.class);
			}
		};
		add(rootLink);

		Button projectSubmitButton = new Button("projectSubmit") {
			private static final long serialVersionUID = 1L;

			public void onSubmit() {
				String projectName = projectNameTextField.getValue();
				projectModel.setName(projectName);
			}
		};
		form.add(projectSubmitButton);
	}
}
