package com.medical.dto;

import java.util.Date;

public class TestSsnDTO {
	 	private String tId;
	    private String memberId;
	    private String ssn1;
	    private String ssn2;
	    private String ssn3;
	    private Date ctime;
	    private Date utime;
	    private String ds;
		public String gettId() {
			return tId;
		}
		public void settId(String tId) {
			this.tId = tId;
		}
		public String getMemberId() {
			return memberId;
		}
		public void setMemberId(String memberId) {
			this.memberId = memberId;
		}
		public String getSsn1() {
			return ssn1;
		}
		public void setSsn1(String ssn1) {
			this.ssn1 = ssn1;
		}
		public String getSsn2() {
			return ssn2;
		}
		public void setSsn2(String ssn2) {
			this.ssn2 = ssn2;
		}
		public String getSsn3() {
			return ssn3;
		}
		public void setSsn3(String ssn3) {
			this.ssn3 = ssn3;
		}
		public Date getCtime() {
			return ctime;
		}
		public void setCtime(Date ctime) {
			this.ctime = ctime;
		}
		public Date getUtime() {
			return utime;
		}
		public void setUtime(Date utime) {
			this.utime = utime;
		}
		public String getDs() {
			return ds;
		}
		public void setDs(String ds) {
			this.ds = ds;
		}
	
}
