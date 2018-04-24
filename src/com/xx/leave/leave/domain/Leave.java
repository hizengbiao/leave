package com.xx.leave.leave.domain;

import java.util.Date;

import com.xx.leave.worker.domain.WorkerExt;


public class Leave extends WorkerExt{
    private Integer id;

    private String wId;

    private Date startTime;

    private Date endTime;

    private String remark;

    private Integer state;

    private String wName;
    
    private Date sTime;
    
    private String sNames;
    
    private String wIds;
    private String wNames;
    private Integer states;
    
    

    public Integer getStates() {
		return states;
	}

	public void setStates(Integer states) {
		this.states = states;
	}

	public String getwIds() {
		return wIds;
	}

	public void setwIds(String wIds) {
		this.wIds = wIds;
	}

	public String getwNames() {
		return wNames;
	}

	public void setwNames(String wNames) {
		this.wNames = wNames;
	}

	public Date getsTime() {
		return sTime;
	}

	public void setsTime(Date sTime) {
		this.sTime = sTime;
	}

	public String getsNames() {
		return sNames;
	}

	public void setsNames(String sNames) {
		this.sNames = sNames;
	}

	public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }


    public String getwId() {
		return wId;
	}

	public void setwId(String wId) {
		this.wId = wId;
	}

	public Date getStartTime() {
        return startTime;
    }

    public void setStartTime(Date startTime) {
        this.startTime = startTime;
    }

    public Date getEndTime() {
        return endTime;
    }

    public void setEndTime(Date endTime) {
        this.endTime = endTime;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark == null ? null : remark.trim();
    }

    public Integer getState() {
        return state;
    }

    public void setState(Integer state) {
        this.state = state;
    }

    public String getwName() {
        return wName;
    }

    public void setwName(String wName) {
        this.wName = wName == null ? null : wName.trim();
    }
}