package com.airparking.apms.api.district.entity;

import java.io.Serializable;

import com.airparking.core.base.entity.AbstractEntity;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;

/**
 * Created by ryan on 2016-01-28.
 */
public class District extends AbstractEntity implements Serializable {
    private Long id;
    private String name;
    private String shortName;
    private String cityCde;
    private String adcode;
    private Double longitude;
    private Double latitude;
    // 2-province, 3-city, 4-district, 5-biz_area
    private Byte level;
    private Long parentId;
    private Byte enabled;

    public void setId(Long id){
        this.id = id;
    }

    public Long getId(){
        return this.id;
    }

    public void setName(String name){
        this.name = name;
    }

    @JsonInclude(value = JsonInclude.Include.NON_NULL)
    public String getName(){
        return this.name;
    }

    public void setShortName(String shortName){
        this.shortName = shortName;
    }

    public String getShortName(){
        return this.shortName;
    }

    public void setCityCde(String cityCde){
        this.cityCde = cityCde;
    }

    public String getCityCde(){
        return this.cityCde;
    }

    public void setAdcode(String adcode){
        this.adcode = adcode;
    }

    public String getAdcode(){
        return this.adcode;
    }

    public void setLongitude(Double longitude){
        this.longitude = longitude;
    }

    public Double getLongitude(){
        return this.longitude;
    }

    public void setLatitude(Double latitude){
        this.latitude = latitude;
    }

    public Double getLatitude(){
        return this.latitude;
    }

    public void setLevel(Byte level){
        this.level = level;
    }

    public Byte getLevel(){
        return this.level;
    }

    public void setParentId(Long parentId){
        this.parentId = parentId;
    }

    public Long getParentId(){
        return this.parentId;
    }

    public void setEnabled(Byte enabled){
        this.enabled = enabled;
    }

    @JsonIgnore
    public Byte getEnabled(){
        return this.enabled;
    }

    public enum DistrictLevel {
        PROVINCE(2),
        CITY(3),
        DISTRICT(4);

        private int value;

        DistrictLevel(int value) {
            this.value = value;
        }

        public int getValue() {
            return value;
        }
    }

}