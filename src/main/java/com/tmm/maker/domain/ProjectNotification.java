package com.tmm.maker.domain;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;

import org.json.JSONObject;

@Entity
@DiscriminatorValue("Project")
public class ProjectNotification extends Notification{
	
	@OneToOne(fetch = FetchType.LAZY)
	@JoinColumn
	private Project project;

	public Project getProject() {
		return project;
	}

	public void setProject(Project project) {
		this.project = project;
	}

	@Override
	JSONObject getNotificationMessage() {
		JSONObject msg = new JSONObject();
		return msg;
	}

}
