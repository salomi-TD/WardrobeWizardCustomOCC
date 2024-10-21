package org.training.model;

public  class BoundingBox {
    @Override
	public String toString() {
		return "BoundingBox [bottom=" + bottom + ", left=" + left + ", right=" + right + ", top=" + top
				+ ", getBottom()=" + getBottom() + ", getLeft()=" + getLeft() + ", getRight()=" + getRight()
				+ ", getTop()=" + getTop() + ", getClass()=" + getClass() + ", hashCode()=" + hashCode()
				+ ", toString()=" + super.toString() + "]";
	}

	public double bottom;
    public double left;
    public double right;
    public double top;

    public double getBottom() {
        return bottom;
    }

    public void setBottom(final double bottom) {
        this.bottom = bottom;
    }

    public double getLeft() {
        return left;
    }

    public void setLeft(final double left) {
        this.left = left;
    }

    public double getRight() {
        return right;
    }

    public void setRight(final double right) {
        this.right = right;
    }

    public double getTop() {
        return top;
    }

    public void setTop(final double top) {
        this.top = top;
    }
}

