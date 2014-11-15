package com.medical.model;

public class ExecutSQL {
	
	private String executsql;
	private int start;

	private int end;

	public int getEnd() {
		return end;
	}
	public String getExecutsql() {
		return executsql;
	}

	public int getStart() {
		return start;
	}

	public void setEnd(int end) {
		this.end = end;
	}

	public void setExecutsql(String executsql) {
		this.executsql = executsql;
	}

	public void setStart(int start) {
		this.start = start;
	}
}
