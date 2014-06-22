package com.github.tempest200903;

import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.logging.Logger;

import org.apache.wicket.markup.html.WebPage;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.link.Link;
import org.apache.wicket.markup.repeater.Item;
import org.apache.wicket.markup.repeater.RefreshingView;
import org.apache.wicket.model.IModel;
import org.apache.wicket.model.Model;
import org.apache.wicket.model.PropertyModel;
import org.apache.wicket.protocol.http.WebApplication;
import org.apache.wicket.request.mapper.parameter.PageParameters;

import com.google.code.morphia.Datastore;
import com.google.code.morphia.query.Query;

public class TopPage extends WebPage {

	final class ProjectListView extends RefreshingView<ProjectModel> {

		private static final long serialVersionUID = 1L;

		private ProjectListView(String id) {
			super(id);
		}

		@Override
		protected Iterator<IModel<ProjectModel>> getItemModels() {
			return wicketProjectModelList.iterator();
		}

		@Override
		protected void populateItem(final Item<ProjectModel> item) {
			PropertyModel<ProjectModel> model = new PropertyModel<ProjectModel>(
					item.getModel(), "title");
			item.add(new Label("projectTitle", model));

			Link<String> deleteProjectLink = new DeleteProjectLink(
					"deleteProject", item);
			item.add(deleteProjectLink);

			// Link<String> readProjectLink = new ReadProjectLink("readProject",
			// item);
			// item.add(readProjectLink);
		}
	}

	private final class DeleteProjectLink extends Link<String> {

		private final Item<ProjectModel> item;

		private static final long serialVersionUID = 1L;

		private DeleteProjectLink(String id, Item<ProjectModel> item) {
			super(id);
			this.item = item;
		}

		@Override
		public void onClick() {
			IModel<ProjectModel> model = item.getModel();
			wicketProjectModelList.remove(model);
			projectModelList.remove(model.getObject());
			save();
		}

	}

	private static final Logger myLogger = Logger.getLogger(TopPage.class
			.getName());

	private static final long serialVersionUID = 1L;

	List<ProjectModel> projectModelList = new ArrayList<ProjectModel>();

	final List<IModel<ProjectModel>> wicketProjectModelList = new ArrayList<IModel<ProjectModel>>();

	public TopPage(final PageParameters parameters) throws UnknownHostException {
		super(parameters);

		add(new ProjectListView("projectList"));

		{
			Link<String> createProject = new Link<String>("createProject") {
				private static final long serialVersionUID = 1L;

				@Override
				public void onClick() {
					ProjectModel projectModel = new ProjectModel();
					String title = "project unknown "
							+ (System.currentTimeMillis() % 10000);
					projectModel.setTitle(title);
					addProjectModel(projectModel);
					myLogger.info("projectModelList.size() =: "
							+ projectModelList.size());
					save();
				}

			};
			add(createProject);
		}

		load();

		myLogger.info("projectModelList.size() =: " + projectModelList.size());
	}

	private Datastore getDatastore() {
		WebApplication webApplication = WebApplication.get();
		if (webApplication instanceof WicketApplication) {
			WicketApplication wicketApplication = (WicketApplication) webApplication;
			return wicketApplication.getDatastore();
		}
		throw new RuntimeException("missing WicketApplication");
	}

	void load() {
		Query<ProjectModel> query = getDatastore().find(ProjectModel.class);
		List<ProjectModel> queryProjectModelList = new ArrayList<ProjectModel>(
				query.asList());
		for (ProjectModel projectModel : queryProjectModelList) {
			addProjectModel(projectModel);
		}
	}

	private void addProjectModel(ProjectModel projectModel) {
		projectModelList.add(projectModel);
		Model<ProjectModel> model = Model.of(projectModel);
		wicketProjectModelList.add(model);
	}

	void save() {
		getDatastore().save(projectModelList);
	}

}
