package com.github.tempest200903;

import java.util.ArrayList;
import java.util.List;

import org.apache.wicket.markup.html.WebPage;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.link.Link;
import org.apache.wicket.request.mapper.parameter.PageParameters;

public class ProjectList extends WebPage {

	private static final long serialVersionUID = 1L;

	List<ProjectModel> projectModelList = new ArrayList<ProjectModel>();

	public ProjectList(final PageParameters parameters) {
		super(parameters);

		{
			Label projectList = new Label("projectList");
			add(projectList);
		}

		{
			Link<String> createProject = new Link<String>("createProject") {
				@Override
				public void onClick() {
					// TODO Auto-generated method stub
				}
			};
			add(createProject);
		}
	}

}