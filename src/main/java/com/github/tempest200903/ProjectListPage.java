package com.github.tempest200903;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.apache.wicket.ajax.AjaxRequestTarget;
import org.apache.wicket.ajax.markup.html.AjaxFallbackLink;
import org.apache.wicket.markup.html.WebPage;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.form.TextField;
import org.apache.wicket.markup.html.link.Link;
import org.apache.wicket.markup.html.navigation.paging.PagingNavigator;
import org.apache.wicket.markup.repeater.Item;
import org.apache.wicket.markup.repeater.data.GridView;
import org.apache.wicket.markup.repeater.data.IDataProvider;
import org.apache.wicket.model.IModel;
import org.apache.wicket.model.Model;
import org.apache.wicket.request.mapper.parameter.PageParameters;

public class ProjectListPage extends WebPage {

	private static final long serialVersionUID = 1L;

	List<ProjectModel> projectModelList = new ArrayList<ProjectModel>();

	Label projectListLabel;

	Link<String> createProjectLink;

	public ProjectListPage(final PageParameters parameters) {
		super(parameters);

		// projectListLabel = new Label("projectList");
		// add(projectListLabel);

		createProjectLink = new Link<String>("createProject") {
			@Override
			public void onClick() {
				createProject();
			}

		};
		add(createProjectLink);
	}

	private void createProject() {
		ProjectModel e = new ProjectModel();
		e.setName("Name TBD");
		projectModelList.add(e);
	}

}
