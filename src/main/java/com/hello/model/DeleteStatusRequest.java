package com.hello.model;

public class DeleteStatusRequest {
	
	private boolean start;
	
	private int cnt;
	
	private boolean all;
	
	private boolean oneTime;

	public boolean isStart() {
		return start;
	}

	public void setStart(boolean start) {
		this.start = start;
	}

	public int getCnt() {
		return cnt;
	}

	public void setCnt(int cnt) {
		this.cnt = cnt;
	}

	public boolean isAll() {
		return all;
	}

	public void setAll(boolean all) {
		this.all = all;
	}

	public boolean isOneTime() {
		return oneTime;
	}

	public void setOneTime(boolean oneTime) {
		this.oneTime = oneTime;
	}

	@Override
	public String toString() {
		return "DeleteStatusRequest [start=" + start + ", cnt=" + cnt + ", all=" + all + ", oneTime=" + oneTime + "]";
	}
}
