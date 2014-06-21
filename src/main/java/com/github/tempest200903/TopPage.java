package com.github.tempest200903;

import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

import org.apache.wicket.markup.html.WebPage;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.link.Link;
import org.apache.wicket.request.mapper.parameter.PageParameters;

import com.google.code.morphia.Datastore;
import com.google.code.morphia.Morphia;
import com.google.code.morphia.query.Query;
import com.mongodb.Mongo;

public class TopPage extends WebPage {

	private static final Logger myLogger = Logger.getLogger(TopPage.class
			.getName());

	private static final long serialVersionUID = 1L;

	List<ProjectModel> projectModelList = new ArrayList<ProjectModel>();

	private transient Datastore datastore;

	public TopPage(final PageParameters parameters) throws UnknownHostException {
		super(parameters);

		{
			Label projectList = new Label("projectList");
			add(projectList);
		}

		{
			Link<String> createProject = new Link<String>("createProject") {
				private static final long serialVersionUID = 1L;

				@Override
				public void onClick() {
					ProjectModel projectModel = new ProjectModel();
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
