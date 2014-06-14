package com.github.tempest200903;

import org.apache.wicket.markup.html.WebPage;
import org.apache.wicket.markup.html.form.Button;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.form.TextField;
import org.apache.wicket.markup.html.link.Link;
import org.apache.wicket.markup.repeater.Item;
import org.apache.wicket.model.Model;

import com.googlecode.mjorm.MongoDao;

public class ProjectPage extends WebPage {

	private static final long serialVersionUID = 1L;

	private String projectCollection = "project";

	private transient MongoDao dao_;

	public ProjectPage(final MongoDao dao, Item<ProjectModel> item) {
		this.dao_ = dao;
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

				String id = projectModel.getId();
				// TODO 変更後の　projectModel　オブジェクトをデータベースに保存する。
				if (false) {
					dao_.updateObject(projectCollection, id, projectModel);
				}
				dao_.savePartialObject(projectCollection, id, "name",
						projectName, true);
			}
		};
		form.add(projectSubmitButton);
	}
}
