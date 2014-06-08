package com.github.tempest200903;

import java.net.UnknownHostException;
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

import com.googlecode.mjorm.MongoDao;
import com.googlecode.mjorm.MongoDaoImpl;
import com.googlecode.mjorm.annotations.AnnotationsDescriptorObjectMapper;
import com.mongodb.DB;
import com.mongodb.Mongo;

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
			save();
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

	private transient Mongo mongo;

	public ProjectListPage(final PageParameters parameters)
			throws UnknownHostException {
		super(parameters);

		mongo = new Mongo();

		projectModelList.add(Model.of(new ProjectModel("sample project 1")));
		projectModelList.add(Model.of(new ProjectModel("sample project 2")));
		add(new ProjectListView("projectList"));

		Link<String> createProjectLink = new CreateProjectLink("createProject");
		add(createProjectLink);
	}

	void save() {
		System.out.println("save begin");

		AnnotationsDescriptorObjectMapper mapper = new AnnotationsDescriptorObjectMapper();
		mapper.addClass(ProjectModel.class);
		DB db = mongo.getDB("wbsestimate");
		MongoDao dao = new MongoDaoImpl(db, mapper);
		String collection = "project";

		int size = projectModelList.size();
		for (int i = 0; i < size; i++) {
			IModel<ProjectModel> model = projectModelList.get(i);
			ProjectModel projectModel = model.getObject();
			dao.createObject(collection, projectModel);
		}

		System.out.println("save end");
	}

}
