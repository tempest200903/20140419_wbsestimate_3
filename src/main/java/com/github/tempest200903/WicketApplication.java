package com.github.tempest200903;

import java.net.UnknownHostException;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.apache.wicket.markup.html.WebPage;
import org.apache.wicket.protocol.http.WebApplication;

import com.google.code.morphia.Datastore;
import com.google.code.morphia.Morphia;
import com.mongodb.Mongo;

/**
 * Application object for your web application. If you want to run this
 * application without deploying, run the Start class.
 * 
 * @see com.github.tempest200903.Start#main(String[])
 */
public class WicketApplication extends WebApplication {

	private static final Logger myLogger = Logger
			.getLogger(WicketApplication.class.getName());

	private Datastore datastore;

	private ProjectModelDAO projectModelDAO;

	Datastore getDatastore() {
		return datastore;
	}

	/**
	 * @see org.apache.wicket.Application#getHomePage()
	 */
	@Override
	public Class<? extends WebPage> getHomePage() {
		return TopPage.class;
	}

	public ProjectModelDAO getProjectModelDAO() {
		return projectModelDAO;
	}

	/**
	 * @see org.apache.wicket.Application#init()
	 */
	@Override
	public void init() {
		super.init();

		// add your configuration here
		try {
			setupDatastore();
		} catch (UnknownHostException e) {
			myLogger.log(Level.WARNING, "setupDatastore", e);
		}
	}

	public void setupDatastore() throws UnknownHostException {
		String mongoServer = "localhost";
		@SuppressWarnings("deprecation")
		Mongo mongo = new Mongo(mongoServer);
		Morphia morphia = new Morphia();
		morphia.map(ProjectModel.class);
		String dbName = "wbsestimate";
		datastore = morphia.createDatastore(mongo, dbName);
		projectModelDAO = new ProjectModelDAO(morphia, mongo, dbName);
	}
	
}
