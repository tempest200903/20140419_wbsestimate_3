package com.github.tempest200903;

import org.apache.wicket.markup.html.WebPage;
import org.apache.wicket.protocol.http.WebApplication;
import org.apache.wicket.request.mapper.parameter.PageParameters;

/**
 *
 */
class MyWebPage extends WebPage {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public MyWebPage() {
		super();
	}

	public MyWebPage(PageParameters parameters) {
		super(parameters);
	}

	protected WicketApplication getWicketApplication() {
		WebApplication webApplication = WebApplication.get();
		if (webApplication instanceof WicketApplication) {
			return (WicketApplication) webApplication;
		}
		throw new RuntimeException("missing WicketApplication");
	}

}
