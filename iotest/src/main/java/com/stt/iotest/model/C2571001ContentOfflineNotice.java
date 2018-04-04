package com.stt.iotest.model;

public class C2571001ContentOfflineNotice {
	private String message;
	
	public C2571001ContentOfflineNotice() {}
	
	public C2571001ContentOfflineNotice(String message) {
		this.message = message;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}
	
	@Override
	public String toString() {
		return String.format("get [%s]", getMessage());
	}
}
