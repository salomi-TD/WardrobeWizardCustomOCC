package org.training.model;
public  class DetectedItem {
    @Override
	public String toString() {
		return "DetectedItem [area=" + area + ", bounding_box=" + bounding_box + ", category=" + category
				+ ", detection_confidence=" + detection_confidence + ", item_image=" + item_image + ", name=" + name
				+ ", getArea()=" + getArea() + ", getBounding_box()=" + getBounding_box() + ", getCategory()="
				+ getCategory() + ", getDetection_confidence()=" + getDetection_confidence() + ", getItem_image()="
				+ getItem_image() + ", getName()=" + getName() + ", getClass()=" + getClass() + ", hashCode()="
				+ hashCode() + ", toString()=" + super.toString() + "]";
	}

	public double area;
    public BoundingBox bounding_box;
    public String category;
    public double detection_confidence;
    public String item_image;
    public String name;

    public double getArea() {
        return area;
    }

    public void setArea(final double area) {
        this.area = area;
    }

    public BoundingBox getBounding_box() {
        return bounding_box;
    }

    public void setBounding_box(final BoundingBox bounding_box) {
        this.bounding_box = bounding_box;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(final String category) {
        this.category = category;
    }

    public double getDetection_confidence() {
        return detection_confidence;
    }

    public void setDetection_confidence(final double detection_confidence) {
        this.detection_confidence = detection_confidence;
    }

    public String getItem_image() {
        return item_image;
    }

    public void setItem_image(final String item_image) {
        this.item_image = item_image;
    }

    public String getName() {
        return name;
    }

    public void setName(final String name) {
        this.name = name;
    }
}
