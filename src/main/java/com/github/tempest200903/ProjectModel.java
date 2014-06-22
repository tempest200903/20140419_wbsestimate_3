package com.github.tempest200903;

import java.io.Serializable;

import org.bson.types.ObjectId;

import com.google.code.morphia.annotations.Entity;
import com.google.code.morphia.annotations.Id;

/**
 *
 */
@Entity
public class ProjectModel implements Serializable {

	@Id
	ObjectId oid;

	String title;

	public ProjectModel() {
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

}
