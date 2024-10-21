package org.training.model;
import com.fasterxml.jackson.annotation.JsonProperty;

public class VersionInfo {

	@Override
	public String toString() {
		return "VersionInfo [id=" + id + ", name=" + name + ", getId()=" + getId() + ", getName()=" + getName()
				+ ", getClass()=" + getClass() + ", hashCode()=" + hashCode() + ", toString()=" + super.toString()
				+ "]";
	}

	public String getId() {
		return id;
	}

	public void setId(final String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(final String name) {
		this.name = name;
	}

	@JsonProperty("id")
	private String id;

	@JsonProperty("name")
	private String name;

}