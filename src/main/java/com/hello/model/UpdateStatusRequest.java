package com.hello.model;

public class UpdateStatusRequest {
	
	private boolean flag;

	private String message;
	
	private boolean start;
	
	private boolean oneTime;
	
	public boolean isFlag() {
		return flag;
	}

	public void setFlag(boolean flag) {
		this.flag = flag;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public boolean isStart() {
		return start;
	}

	public void setStart(boolean start) {
		this.start = start;
	}

	public boolean isOneTime() {
		return oneTime;
	}

	public void setOneTime(boolean oneTime) {
		this.oneTime = oneTime;
	}

	@Override
	public String toString() {
		return "UpdateStatusRequest [message=" + message + ", start=" + start + ", oneTime=" + oneTime + "]";
	}
}
