package com.jk.model;

public class CommBean {
    private Integer commId;
    private String commName;
    private String townCommName;
    private Integer townId;


    public Integer getCommId() {
        return commId;
    }

    public void setCommId(Integer commId) {
        this.commId = commId;
    }

    public String getCommName() {
        return commName;
    }

    public void setCommName(String commName) {
        this.commName = commName;
    }

    public String getTownCommName() {
        return townCommName;
    }

    public void setTownCommName(String townCommName) {
        this.townCommName = townCommName;
    }

    public Integer getTownId() {
        return townId;
    }

    public void setTownId(Integer townId) {
        this.townId = townId;
    }
}
