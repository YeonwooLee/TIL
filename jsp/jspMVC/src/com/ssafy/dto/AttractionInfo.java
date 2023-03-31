package com.ssafy.dto;

public class AttractionInfo {
	int contentTypeId;
	int contentId;
	String title;
	String addr1;
	String addr2;
	String zipcode;
	String tel;
	String firstImage;
	String firstImage2;
	int readCount;
	int sidoCode;
	int gugunCode;
	double latitude;
	double longitude;
	
	public AttractionInfo() {
	}
	
	public AttractionInfo(int contentTypeId, int contentId, String title, String addr1, String addr2, String zipcode,
			String tel, String firstImage, String firstImage2, int readCount, int sidoCode, int gugunCode,
			double latitude, double longitude) {
		super();
		this.contentTypeId = contentTypeId;
		this.contentId = contentId;
		this.title = title;
		this.addr1 = addr1;
		this.addr2 = addr2;
		this.zipcode = zipcode;
		this.tel = tel;
		this.firstImage = firstImage;
		this.firstImage2 = firstImage2;
		this.readCount = readCount;
		this.sidoCode = sidoCode;
		this.gugunCode = gugunCode;
		this.latitude = latitude;
		this.longitude = longitude;
	}

	public int getContentTypeId() {
		return contentTypeId;
	}

	public void setContentTypeId(int contentTypeId) {
		this.contentTypeId = contentTypeId;
	}

	public int getContentId() {
		return contentId;
	}

	public void setContentId(int contentId) {
		this.contentId = contentId;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getAddr1() {
		return addr1;
	}

	public void setAddr1(String addr1) {
		this.addr1 = addr1;
	}

	public String getAddr2() {
		return addr2;
	}

	public void setAddr2(String addr2) {
		this.addr2 = addr2;
	}

	public String getZipcode() {
		return zipcode;
	}

	public void setZipcode(String zipcode) {
		this.zipcode = zipcode;
	}

	public String getTel() {
		return tel;
	}

	public void setTel(String tel) {
		this.tel = tel;
	}

	public String getFirstImage() {
		return firstImage;
	}

	public void setFirstImage(String firstImage) {
		this.firstImage = firstImage;
	}

	public String getFirstImage2() {
		return firstImage2;
	}

	public void setFirstImage2(String firstImage2) {
		this.firstImage2 = firstImage2;
	}

	public int getReadCount() {
		return readCount;
	}

	public void setReadCount(int readCount) {
		this.readCount = readCount;
	}

	public int getSidoCode() {
		return sidoCode;
	}

	public void setSidoCode(int sidoCode) {
		this.sidoCode = sidoCode;
	}

	public int getGugunCode() {
		return gugunCode;
	}

	public void setGugunCode(int gugunCode) {
		this.gugunCode = gugunCode;
	}

	public double getLatitude() {
		return latitude;
	}

	public void setLatitude(double latitude) {
		this.latitude = latitude;
	}

	public double getLongitude() {
		return longitude;
	}

	public void setLongitude(double longitude) {
		this.longitude = longitude;
	}

	@Override
	public String toString() {
		return "TripInfo [contentTypeId=" + contentTypeId + ", contentId=" + contentId + ", title=" + title + ", addr1="
				+ addr1 + ", addr2=" + addr2 + ", zipcode=" + zipcode + ", tel=" + tel + ", firstImage=" + firstImage
				+ ", firstImage2=" + firstImage2 + ", readCount=" + readCount + ", sidoCode=" + sidoCode
				+ ", gugunCode=" + gugunCode + ", latitude=" + latitude + ", longitude=" + longitude + "]";
	}
	
	
	
}
