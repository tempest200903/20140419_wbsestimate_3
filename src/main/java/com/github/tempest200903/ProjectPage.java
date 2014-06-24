package com.github.tempest200903;

import java.util.logging.Logger;

import org.apache.wicket.markup.html.WebPage;
import org.apache.wicket.markup.html.form.Button;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.form.TextField;
import org.apache.wicket.markup.html.link.BookmarkablePageLink;
import org.apache.wicket.model.Model;

/**
 *
 */
public class ProjectPage extends WebPage {

	private static final Logger myLogger = Logger.getLogger(ProjectPage.class
			.getName());

	private ProjectModel projectModel;

	public ProjectPage(ProjectModel projectModel) {
		this.projectModel = projectModel;
		String projectTitle = projectModel.getTitle();

		Form<?> form = new Form<Object>("form1") {
			private static final long serialVersionUID = 1L;
		};
		add(form);

		final TextField<String> projectNameTextField = new TextField<String>(
				"projectTitle", Model.of(projectTitle));
		form.add(projectNameTextField);

		Button projectSubmitButton = new Button("projectSubmit") {
			private static final long serialVersionUID = 1L;

			public void onSubmit() {
				myLogger.info("projectSubmitButton");
			}
		};
		form.add(projectSubmitButton);

		BookmarkablePageLink<String> topPageLink = new BookmarkablePageLink<String>(
				"topPageLink", TopPage.class);
		add(topPageLink);
	}

	private static final long serialVersionUID = 1L;

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
