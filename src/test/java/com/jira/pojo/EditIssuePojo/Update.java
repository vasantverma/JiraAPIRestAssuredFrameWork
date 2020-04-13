package com.jira.pojo.EditIssuePojo;

import java.util.List;

public class Update
{
	private List<Summary> summary;
	private List<Description> description;
	private List<Labels> labels;
	
	public List<Summary> getSummary() {
		return summary;
	}
	public void setSummary(List<Summary> summary) {
		this.summary = summary;
	}
	public List<Description> getDescription() {
		return description;
	}
	public void setDescription(List<Description> description) {
		this.description = description;
	}
	public List<Labels> getLabels() {
		return labels;
	}
	public void setLabels(List<Labels> labels) {
		this.labels = labels;
	}

}
