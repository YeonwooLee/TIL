package com.ssafy.dto;

public class Sido {
	int sidoCode;
	String sidoName;
	
	public Sido() {
	}

	
	public Sido(int sidoCode, String sidoName) {
		super();
		this.sidoCode = sidoCode;
		this.sidoName = sidoName;
	}

	public int getSidoCode() {
		return sidoCode;
	}

	public void setSidoCode(int sidoCode) {
		this.sidoCode = sidoCode;
	}

	public String getSidoName() {
		return sidoName;
	}

	public void setSidoName(String sidoName) {
		this.sidoName = sidoName;
	}

	@Override
	public String toString() {
		return "Sido [sidoCode=" + sidoCode + ", sidoName=" + sidoName + "]";
	}
	
}
