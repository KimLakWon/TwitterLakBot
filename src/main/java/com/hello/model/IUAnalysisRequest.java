package com.hello.model;

public class IUAnalysisRequest {
	
	private boolean flag;

	private int interval;
	
	private boolean start;
	
	private boolean oneTime;
	
	public boolean isFlag() {
		return flag;
	}

	public void setFlag(boolean flag) {
		this.flag = flag;
	}

	public int getInterval() {
		return interval;
	}

	public void setInterval(int interval) {
		this.interval = interval;
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
		return "UpdateStatusRequest [interval=" + interval + ", start=" + start + ", oneTime=" + oneTime + "]";
	}
}
