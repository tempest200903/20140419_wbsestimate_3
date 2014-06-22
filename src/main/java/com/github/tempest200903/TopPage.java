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
import org.apache.wicket.request.mapper.parameter.PageParameters;

import com.google.code.morphia.Datastore;
import com.google.code.morphia.Morphia;
import com.google.code.morphia.query.Query;
import com.mongodb.Mongo;

public class TopPage extends WebPage {

	private final class ProjectListView extends RefreshingView<ProjectModel> {

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

			// Link<String> deleteProjectLink = new DeleteProjectLink(
			// "deleteProject", item);
			// item.add(deleteProjectLink);
			//
			// Link<String> readProjectLink = new ReadProjectLink("readProject",
			// item);
			// item.add(readProjectLink);
		}
	}

	private static final Logger myLogger = Logger.getLogger(TopPage.class
			.getName());

	private static final long serialVersionUID = 1L;

	List<ProjectModel> projectModelList = new ArrayList<ProjectModel>();

	final List<IModel<ProjectModel>> wicketProjectModelList = new ArrayList<IModel<ProjectModel>>();

	private transient Datastore datastore;

	public TopPage(final PageParameters parameters) throws UnknownHostException {
		super(parameters);

		if (false) {
			Label projectList = new Label("projectList");
			add(projectList);
		}
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
					projectModelList.add(projectModel);
					myLogger.info("projectModelList.size() =: "
							+ projectModelList.size());
					save();
				}

			};
			add(createProject);
		}

		setupDatastore();
		load();

		myLogger.info("projectModelList.size() =: " + projectModelList.size());
	}

	void load() {
		Query<ProjectModel> query = datastore.find(ProjectModel.class);
		projectModelList = new ArrayList<ProjectModel>(query.asList());
		for (ProjectModel projectModel : projectModelList) {
			Model<ProjectModel> model = org.apache.wicket.model.Model
					.of(projectModel);
			wicketProjectModelList.add(model);
		}
	}

	void save() {
		datastore.save(projectModelList);
	}

	public void setupDatastore() throws UnknownHostException {
		String mongoServer = "localhost";
		@SuppressWarnings("deprecation")
		Mongo mongo = new Mongo(mongoServer);
		Morphia morphia = new Morphia();
		morphia.map(ProjectModel.class);
		String dbName = "wbsestimate";
		datastore = morphia.createDatastore(mongo, dbName);
	}

}
