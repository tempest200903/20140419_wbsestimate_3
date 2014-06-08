package com.github.tempest200903;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.apache.wicket.markup.html.WebPage;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.link.Link;
import org.apache.wicket.markup.repeater.Item;
import org.apache.wicket.markup.repeater.RefreshingView;
import org.apache.wicket.model.IModel;
import org.apache.wicket.model.Model;
import org.apache.wicket.model.PropertyModel;
import org.apache.wicket.request.mapper.parameter.PageParameters;

public class ProjectListPage extends WebPage {

	private final class CreateProjectLink extends Link<String> {
		private static final long serialVersionUID = 1L;

		private CreateProjectLink(String id) {
			super(id);
		}

		@Override
		public void onClick() {
			String name = "Name TBD "
					+ String.valueOf(System.currentTimeMillis());
			ProjectModel p = new ProjectModel(name);
			projectModelList.add(Model.of(p));
		}
	}

	private final class ProjectListView extends RefreshingView<ProjectModel> {
		private static final long serialVersionUID = 1L;

		private ProjectListView(String id) {
			super(id);
		}

		@Override
		protected Iterator<IModel<ProjectModel>> getItemModels() {
			return projectModelList.iterator();
		}

		@Override
		protected void populateItem(final Item<ProjectModel> item) {
			PropertyModel<ProjectModel> model = new PropertyModel<ProjectModel>(
					item.getModel(), "name");
			item.add(new Label("projectName", model));

			Link<String> deleteProjectLink = new Link<String>("deleteProject") {
				private static final long serialVersionUID = 1L;

				@Override
				public void onClick() {
					IModel<ProjectModel> model = item.getModel();
					projectModelList.remove(model);
				}
			};
			item.add(deleteProjectLink);
		}
	}

	private static final long serialVersionUID = 1L;

	final List<IModel<ProjectModel>> projectModelList = new ArrayList<IModel<ProjectModel>>();

	Link<String> createProjectLink;

	public ProjectListPage(final PageParameters parameters) {
		super(parameters);

		projectModelList.add(Model.of(new ProjectModel("sample project 1")));
		projectModelList.add(Model.of(new ProjectModel("sample project 2")));
		add(new ProjectListView("projectList"));

		createProjectLink = new CreateProjectLink("createProject");
		add(createProjectLink);
	}

}
