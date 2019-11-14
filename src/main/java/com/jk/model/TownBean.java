package com.jk.model;

public class TownBean {
    private Integer townId;
    private String townName;
    private String areaTownName;
    private Integer areaId;

    public Integer getTownId() {
        return townId;
    }

    public void setTownId(Integer townId) {
        this.townId = townId;
    }

    public String getTownName() {
        return townName;
    }

    public void setTownName(String townName) {
        this.townName = townName;
    }

    public String getAreaTownName() {
        return areaTownName;
    }

    public void setAreaTownName(String areaTownName) {
        this.areaTownName = areaTownName;
    }

    public Integer getAreaId() {
        return areaId;
    }

    public void setAreaId(Integer areaId) {
        this.areaId = areaId;
    }

}
