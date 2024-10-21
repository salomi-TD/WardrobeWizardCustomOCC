package org.training.model;

public class JsonResponse {

	@Override
	public String toString() {
		// Customize the string representation here.
		return "JsonResponse{query_image='" + data.getQuery_image() + "', result_groups=" + data.getResult_groups()
				+ '}';
	}

	public Data data;

	public Data getData() {
		return data;
	}

	public void setData(final Data data) {
		this.data = data;
	}

}