package org.training.model;
import java.util.List;

public class SimilarProduct {

	@Override
	public String toString() {
		return "SimilarProduct [brand_name=" + brand_name + ", category=" + category + ", currency=" + currency
				+ ", gender=" + gender + ", id=" + id + ", images=" + images + ", matching_image=" + matching_image
				+ ", name=" + name + ", price=" + price + ", reduced_price=" + reduced_price + ", score=" + score
				+ ", sub_category=" + sub_category + ", url=" + url + ", vendor=" + vendor + ", getBrand_name()="
				+ getBrand_name() + ", getCategory()=" + getCategory() + ", getCurrency()=" + getCurrency()
				+ ", getGender()=" + getGender() + ", getId()=" + getId() + ", getImages()=" + getImages()
				+ ", getMatching_image()=" + getMatching_image() + ", getName()=" + getName() + ", getPrice()="
				+ getPrice() + ", getReduced_price()=" + getReduced_price() + ", getScore()=" + getScore()
				+ ", getSub_category()=" + getSub_category() + ", getUrl()=" + getUrl() + ", getVendor()=" + getVendor()
				+ ", getClass()=" + getClass() + ", hashCode()=" + hashCode() + ", toString()=" + super.toString()
				+ "]";
	}

	public String brand_name;
	public String category;
	public String currency;
	public String gender;
	public String id;
	public List<String> images;
	public String matching_image;
	public String name;
	public String price;
	public String reduced_price;
	public double score;
	public String sub_category;
	public String url;
	public String vendor;

	public String getBrand_name() {
		return brand_name;
	}

	public void setBrand_name(final String brand_name) {
		this.brand_name = brand_name;
	}

	public String getCategory() {
		return category;
	}

	public void setCategory(final String category) {
		this.category = category;
	}

	public String getCurrency() {
		return currency;
	}

	public void setCurrency(final String currency) {
		this.currency = currency;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(final String gender) {
		this.gender = gender;
	}

	public String getId() {
		return id;
	}

	public void setId(final String id) {
		this.id = id;
	}

	public List<String> getImages() {
		return images;
	}

	public void setImages(final List<String> images) {
		this.images = images;
	}

	public String getMatching_image() {
		return matching_image;
	}

	public void setMatching_image(final String matching_image) {
		this.matching_image = matching_image;
	}

	public String getName() {
		return name;
	}

	public void setName(final String name) {
		this.name = name;
	}

	public String getPrice() {
		return price;
	}

	public void setPrice(final String price) {
		this.price = price;
	}

	public String getReduced_price() {
		return reduced_price;
	}

	public void setReduced_price(final String reduced_price) {
		this.reduced_price = reduced_price;
	}

	public double getScore() {
		return score;
	}

	public void setScore(final double score) {
		this.score = score;
	}

	public String getSub_category() {
		return sub_category;
	}

	public void setSub_category(final String sub_category) {
		this.sub_category = sub_category;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(final String url) {
		this.url = url;
	}

	public String getVendor() {
		return vendor;
	}

	public void setVendor(final String vendor) {
		this.vendor = vendor;
	}
}
