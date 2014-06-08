package com.github.tempest200903;

import org.apache.wicket.ajax.AjaxRequestTarget;
import org.apache.wicket.ajax.markup.html.AjaxFallbackLink;
import org.apache.wicket.markup.html.WebPage;
import org.apache.wicket.markup.html.form.TextField;
import org.apache.wicket.markup.html.link.Link;
import org.apache.wicket.model.Model;
import org.apache.wicket.request.mapper.parameter.PageParameters;

public class WbsEstimate extends WebPage {

	private static final long serialVersionUID = 1L;

	public WbsEstimate(final PageParameters parameters) {
		super(parameters);

		// project
		TextField<String> projectNameUI = new TextField<String>(
				"projectNameUI", Model.of("projectNameDefault"));

		// phase
		TextField<String> phaseNameUI = new TextField<String>("phaseNameUI",
				Model.of("phaseNameDefault"));
		Link<String> phaseAdd = new AjaxFallbackLink<String>("phaseAdd") {
			private static final long serialVersionUID = 1L;

			@Override
			public void onClick(AjaxRequestTarget target) {
				// TODO Auto-generated method stub
			}
		};
		Link<String> phaseDelete = new AjaxFallbackLink<String>("phaseDelete") {
			private static final long serialVersionUID = 1L;

			@Override
			public void onClick(AjaxRequestTarget target) {
				// TODO Auto-generated method stub
			}
		};

		add(phaseAdd);
		add(phaseDelete);
		add(projectNameUI);
		add(phaseNameUI);
	}

}
