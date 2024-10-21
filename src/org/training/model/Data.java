package org.training.model;
import java.util.List;

public class Data {

	public String query_image;
	public List<ResultGroup> result_groups;

	public String getQuery_image() {
		return query_image;
	}

	public void setQuery_image(final String query_image) {
		this.query_image = query_image;
	}

	@Override
	public String toString() {
		return "Data [query_image=" + query_image + ", result_groups=" + result_groups + ", getQuery_image()="
				+ getQuery_image() + ", getResult_groups()=" + getResult_groups() + ", getClass()=" + getClass()
				+ ", hashCode()=" + hashCode() + ", toString()=" + super.toString() + "]";
	}

	public List<ResultGroup> getResult_groups() {
		return result_groups;
	}

	public void setResult_groups(final List<ResultGroup> result_groups) {
		this.result_groups = result_groups;
	}
}
