package com.github.tempest200903;

import org.apache.wicket.markup.html.WebPage;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.link.Link;
import org.apache.wicket.markup.repeater.Item;

public class ProjectPage extends WebPage {

	private static final long serialVersionUID = 1L;

	Item<ProjectModel> item;

	public ProjectPage(Item<ProjectModel> item) {
		this.item = item;
		ProjectModel projectModel = item.getModelObject();
		String projectName = projectModel.getName();
		Label label = new Label("projectName", projectName);
		add(label);

		Link<String> rootLink = new Link<String>("rootLink") {
			@Override
			public void onClick() {
				setResponsePage(TopPage.class);
			}
		};
		add(rootLink);
	}
}
