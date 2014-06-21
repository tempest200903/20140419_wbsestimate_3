package com.github.tempest200903;

import java.util.ArrayList;
import java.util.List;

import org.apache.wicket.markup.html.WebPage;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.link.Link;
import org.apache.wicket.request.mapper.parameter.PageParameters;

public class TopPage extends WebPage {

	private static final long serialVersionUID = 1L;

	List<ProjectModel> projectModelList = new ArrayList<ProjectModel>();

	public TopPage(final PageParameters parameters) {
		super(parameters);

		{
			Label projectList = new Label("projectList");
			add(projectList);
		}

		{
			Link<String> createProject = new Link<String>("createProject") {
				@Override
				public void onClick() {
					ProjectModel projectModel = new ProjectModel();
					projectModelList.add(projectModel);
				}
			};
			add(createProject);
		}
	}

}
