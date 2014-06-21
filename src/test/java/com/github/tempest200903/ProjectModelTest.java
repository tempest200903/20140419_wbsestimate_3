package com.github.tempest200903;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import com.google.code.morphia.Datastore;
import com.google.code.morphia.Key;
import com.google.code.morphia.Morphia;
import com.mongodb.Mongo;

public class ProjectModelTest {

	Datastore datastore;

	private ProjectModel createExpectedProjectModel() {
		return new ProjectModel();
	}

	@Before
	public void setUp() throws Exception {
		String mongoServer = "localhost";
		@SuppressWarnings("deprecation")
		Mongo mongo = new Mongo(mongoServer);
		Morphia morphia = new Morphia();
		morphia.map(ProjectModel.class);
		String dbName = "ProjectModelTest";
		datastore = morphia.createDatastore(mongo, dbName);
	}

	@Test
	public void testMorphiaFind() {
		ProjectModel expected = createExpectedProjectModel();
		datastore.save(expected);
		ProjectModel actual = datastore.find(ProjectModel.class).field("title")
				.equal(expected.getTitle()).get();

		Assert.assertEquals(expected.getTitle(), actual.getTitle());
	}

	@Test
	public void testMorphiaGetByKey() {
		ProjectModel expected = createExpectedProjectModel();
		Key<ProjectModel> key = datastore.save(expected);
		ProjectModel actual = datastore.getByKey(ProjectModel.class, key);

		Assert.assertEquals(expected.getTitle(), actual.getTitle());
	}

}
