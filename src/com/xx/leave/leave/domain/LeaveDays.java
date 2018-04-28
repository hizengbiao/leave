package com.xx.leave.leave.domain;

public class LeaveDays implements Comparable<LeaveDays>{
	
	 	private String wId;

	    private String wName;

		private Integer leaveDays;
		
	    public String getwId() {
			return wId;
		}

		public void setwId(String wId) {
			this.wId = wId;
		}

		public String getwName() {
			return wName;
		}

		public void setwName(String wName) {
			this.wName = wName;
		}

		public Integer getLeaveDays() {
			return leaveDays;
		}

		public void setLeaveDays(Integer leaveDays) {
			this.leaveDays = leaveDays;
		}

		public int compareTo(LeaveDays o) {
			// TODO Auto-generated method stub
			int i = this.getLeaveDays() - o.getLeaveDays();//œ»∞¥’’ƒÍ¡‰≈≈–Ú  
	        
	        return -i;  
		}


}
