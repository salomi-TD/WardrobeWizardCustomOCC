package org.training.model;
import com.fasterxml.jackson.annotation.JsonProperty;

public class ImageUploadResponse {
	@Override
	public String toString() {
		return "ImageUploadResponse [fileId=" + fileId + ", name=" + name + ", size=" + size + ", versionInfo="
				+ versionInfo + ", filePath=" + filePath + ", url=" + url + ", fileType=" + fileType + ", height="
				+ height + ", width=" + width + ", thumbnailUrl=" + thumbnailUrl + ", getFileId()=" + getFileId()
				+ ", getName()=" + getName() + ", getSize()=" + getSize() + ", getVersionInfo()=" + getVersionInfo()
				+ ", getFilePath()=" + getFilePath() + ", getUrl()=" + getUrl() + ", getFileType()=" + getFileType()
				+ ", getHeight()=" + getHeight() + ", getWidth()=" + getWidth() + ", getThumbnailUrl()="
				+ getThumbnailUrl() + ", getClass()=" + getClass() + ", hashCode()=" + hashCode() + ", toString()="
				+ super.toString() + "]";
	}

	public String getFileId() {
		return fileId;
	}

	public void setFileId(final String fileId) {
		this.fileId = fileId;
	}

	public String getName() {
		return name;
	}

	public void setName(final String name) {
		this.name = name;
	}

	public long getSize() {
		return size;
	}

	public void setSize(final long size) {
		this.size = size;
	}

	public VersionInfo getVersionInfo() {
		return versionInfo;
	}

	public void setVersionInfo(final VersionInfo versionInfo) {
		this.versionInfo = versionInfo;
	}

	public String getFilePath() {
		return filePath;
	}

	public void setFilePath(final String filePath) {
		this.filePath = filePath;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(final String url) {
		this.url = url;
	}

	public String getFileType() {
		return fileType;
	}

	public void setFileType(final String fileType) {
		this.fileType = fileType;
	}

	public int getHeight() {
		return height;
	}

	public void setHeight(final int height) {
		this.height = height;
	}

	public int getWidth() {
		return width;
	}

	public void setWidth(final int width) {
		this.width = width;
	}

	public String getThumbnailUrl() {
		return thumbnailUrl;
	}

	public void setThumbnailUrl(final String thumbnailUrl) {
		this.thumbnailUrl = thumbnailUrl;
	}

	@JsonProperty("fileId")
	private String fileId;

	@JsonProperty("name")
	private String name;

	@JsonProperty("size")
	private long size;

	@JsonProperty("versionInfo")
	private VersionInfo versionInfo;

	@JsonProperty("filePath")
	private String filePath;

	@JsonProperty("url")
	private String url;

	@JsonProperty("fileType")
	private String fileType;

	@JsonProperty("height")
	private int height;

	@JsonProperty("width")
	private int width;

	@JsonProperty("thumbnailUrl")
	private String thumbnailUrl;

	@JsonProperty("AITags")
	private String AITags;

	// Getters and setters for the fields

	public String getAITags() {
		return AITags;
	}

	public void setAITags(final String aITags) {
		AITags = aITags;
	}

}
