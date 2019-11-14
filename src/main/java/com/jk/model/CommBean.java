package com.jk.model;

public class CommBean {
    private Integer commId;
    private String commName;
    private String townCommName;
    private Integer townId;

    private String huanjing;
    private String fujin;

    private String sheshi;

    private Integer stastu;

    public Integer getStastu() {
        return stastu;
    }

    public void setStastu(Integer stastu) {
        this.stastu = stastu;
    }

    public String getHuanjing() {
        return huanjing;
    }

    public void setHuanjing(String huanjing) {
        this.huanjing = huanjing;
    }

    public String getFujin() {
        return fujin;
    }

    public void setFujin(String fujin) {
        this.fujin = fujin;
    }

    public String getSheshi() {
        return sheshi;
    }

    public void setSheshi(String sheshi) {
        this.sheshi = sheshi;
    }

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
