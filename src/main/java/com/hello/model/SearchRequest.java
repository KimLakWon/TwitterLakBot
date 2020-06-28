package com.hello.model;

public class SearchRequest {
	
	private String query;
	
	private int count;
	
	private boolean start;
	
	private boolean saveFile;
	
	private boolean oneTime;

	public String getQuery() {
		return query;
	}

	public void setQuery(String query) {
		this.query = query;
	}

	public int getCount() {
		return count;
	}

	public void setCount(int count) {
		this.count = count;
	}

	public boolean isStart() {
		return start;
	}

	public void setStart(boolean start) {
		this.start = start;
	}

	public boolean isSaveFile() {
		return saveFile;
	}

	public void setSaveFile(boolean saveFile) {
		this.saveFile = saveFile;
	}

	public boolean isOneTime() {
		return oneTime;
	}

	public void setOneTime(boolean oneTime) {
		this.oneTime = oneTime;
	}

	@Override
	public String toString() {
		return "SearchRequest [query=" + query + ", count=" + count + ", start=" + start + ", saveFile=" + saveFile
				+ ", oneTime=" + oneTime + "]";
	}
}
