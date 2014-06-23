package com.github.tempest200903;

import org.bson.types.ObjectId;

import com.google.code.morphia.Morphia;
import com.google.code.morphia.dao.BasicDAO;
import com.mongodb.Mongo;

/**
 *
 */
public class ProjectModelDAO extends BasicDAO<ProjectModel, ObjectId> {

	public ProjectModelDAO(Morphia morphia, Mongo mongo, String dbName) {
		super(mongo, morphia, dbName);
	}

}
