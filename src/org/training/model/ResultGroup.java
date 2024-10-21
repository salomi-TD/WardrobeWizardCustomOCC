package org.training.model;
import java.util.List;

public class ResultGroup {


	@Override
	public String toString() {
		return "ResultGroup [average_score=" + average_score + ", detected_item=" + detected_item + ", max_score="
				+ max_score + ", rank_score=" + rank_score + ", similar_products=" + similar_products
				+ ", getAverage_score()=" + getAverage_score() + ", getDetected_item()=" + getDetected_item()
				+ ", getMax_score()=" + getMax_score() + ", getRank_score()=" + getRank_score()
				+ ", getSimilar_products()=" + getSimilar_products() + ", getClass()=" + getClass() + ", hashCode()="
				+ hashCode() + ", toString()=" + super.toString() + "]";
	}

	public double average_score;
	public DetectedItem detected_item;
	public double max_score;
	public double rank_score;
	public List<SimilarProduct> similar_products;

	public double getAverage_score() {
		return average_score;
	}

	public void setAverage_score(final double average_score) {
		this.average_score = average_score;
	}

	public DetectedItem getDetected_item() {
		return detected_item;
	}

	public void setDetected_item(final DetectedItem detected_item) {
		this.detected_item = detected_item;
	}

	public double getMax_score() {
		return max_score;
	}

	public void setMax_score(final double max_score) {
		this.max_score = max_score;
	}

	public double getRank_score() {
		return rank_score;
	}

	public void setRank_score(final double rank_score) {
		this.rank_score = rank_score;
	}

	public List<SimilarProduct> getSimilar_products() {
		return similar_products;
	}

	public void setSimilar_products(final List<SimilarProduct> similar_products) {
		this.similar_products = similar_products;
	}
}
