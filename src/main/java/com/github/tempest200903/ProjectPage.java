package com.github.tempest200903;

import java.util.logging.Logger;

import org.apache.wicket.markup.html.WebPage;
import org.apache.wicket.markup.html.form.Button;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.form.TextField;
import org.apache.wicket.markup.html.link.BookmarkablePageLink;
import org.apache.wicket.markup.html.link.Link;
import org.apache.wicket.model.Model;

/**
 *
 */
public class ProjectPage extends WebPage {

	private static final Logger myLogger = Logger.getLogger(ProjectPage.class
			.getName());

	private ProjectModel projectModel;

	private static final long serialVersionUID = 1L;

	public ProjectPage(ProjectModel projectModel) {
		this.projectModel = projectModel;
		String projectTitle = projectModel.getTitle();
		myLogger.info("projectTitle =: " + projectTitle);

		final TextField<String> projectNameTextField = new TextField<String>(
				"projectTitle", Model.of(projectTitle));
		myLogger.info("projectNameTextField.getModelObject() =: "
				+ projectNameTextField.getModelObject());

		Form<?> form = new Form<Object>("form1") {
			private static final long serialVersionUID = 1L;

			@Override
			public void onSubmit() {
				myLogger.info("projectSubmitButton");
				String projectTitle = projectNameTextField.getModelObject();
				myLogger.info("onSubmit() # projectTitle =: " + projectTitle);
				ProjectPage.this.projectModel.setTitle(projectTitle);
			}
		};
		Button projectSubmitButton = new Button("projectSubmit") {
			private static final long serialVersionUID = 1L;
		};
		Link<?> projectButton = new Link<Object>("projectButton") {
			private static final long serialVersionUID = 1L;

			@Override
			public void onClick() {
				myLogger.info("projectSubmitButton");
				String projectTitle = projectNameTextField.getModelObject();
				myLogger.info("onSubmit() # projectTitle =: " + projectTitle);
				ProjectPage.this.projectModel.setTitle(projectTitle);
			}
		};

		add(form);
		form.add(projectNameTextField);
		form.add(projectSubmitButton);
		add(projectButton);

		BookmarkablePageLink<String> topPageLink = new BookmarkablePageLink<String>(
				"topPageLink", TopPage.class);
		add(topPageLink);
	}

	public ProjectModel getProjectModel() {
		return projectModel;
	}

	// private String projectCollection = "project";
	//
	// private transient MongoDao dao_;
	//
	// public ProjectPage(final MongoDao dao, Item<ProjectModel> item) {
	// this.dao_ = dao;
	// final ProjectModel projectModel = item.getModelObject();
	// String projectName = projectModel.getName();
	//
	// Form<?> form = new Form<Object>("form") {
	// private static final long serialVersionUID = 1L;
	//
	// protected void onSubmit() {
	// info("Form.onSubmit executed");
	// }
	// };
	// add(form);
	//
	// final TextField<String> projectNameTextField = new TextField<String>(
	// "projectName", Model.of(projectName));
	// form.add(projectNameTextField);
	//
	// Button projectSubmitButton = new Button("projectSubmit") {
	// private static final long serialVersionUID = 1L;
	//
	// public void onSubmit() {
	// String projectName = projectNameTextField.getValue();
	// projectModel.setName(projectName);
	//
	// String id = projectModel.getId();
	// // TODO 変更後の　projectModel　オブジェクトをデータベースに保存する。
	// if (false) {
	// dao_.updateObject(projectCollection, id, projectModel);
	// }
	// dao_.savePartialObject(projectCollection, id, "name",
	// projectName, true);
	// }
	// };
	// form.add(projectSubmitButton);
	// }

}
