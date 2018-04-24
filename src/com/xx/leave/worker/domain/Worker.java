package com.xx.leave.worker.domain;

public class Worker {
    private Integer id;

    private String wId;

    private String wName;

    private String wSex;

    private String wTel;

    private String wPhone;

    private Integer dId;

    private Integer departmentId;

    private Integer auth;

    private String wPassword;
    private Integer sId;

    public Integer getsId() {
		return sId;
	}

	public void setsId(Integer sId) {
		this.sId = sId;
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
        this.wId = wId == null ? null : wId.trim();
    }

    public String getwName() {
        return wName;
    }

    public void setwName(String wName) {
        this.wName = wName == null ? null : wName.trim();
    }

    public String getwSex() {
        return wSex;
    }

    public void setwSex(String wSex) {
        this.wSex = wSex == null ? null : wSex.trim();
    }

    public String getwTel() {
        return wTel;
    }

    public void setwTel(String wTel) {
        this.wTel = wTel == null ? null : wTel.trim();
    }

    public String getwPhone() {
        return wPhone;
    }

    public void setwPhone(String wPhone) {
        this.wPhone = wPhone == null ? null : wPhone.trim();
    }

    public Integer getdId() {
        return dId;
    }

    public void setdId(Integer dId) {
        this.dId = dId;
    }

    public Integer getDepartmentId() {
        return departmentId;
    }

    public void setDepartmentId(Integer departmentId) {
        this.departmentId = departmentId;
    }

    public Integer getAuth() {
        return auth;
    }

    public void setAuth(Integer auth) {
        this.auth = auth;
    }

    public String getwPassword() {
        return wPassword;
    }

    public void setwPassword(String wPassword) {
        this.wPassword = wPassword == null ? null : wPassword.trim();
    }
}