package com.airparking.apms.api.version.entity;

import java.io.Serializable;
import java.util.Date;

import com.airparking.core.base.entity.AbstractEntity;

/**
 * Created by ryan on 2016-08-05.
 */
public class Version extends AbstractEntity implements Serializable {
  private Long id;
  private String appid;
  private String systemName;
  private Long currentVersion;
  private String currentVersionName;
  private Long newestVersion;
  private String newestVersionName;
  private String downloadLink;
  private String description;
  private Boolean isDisabled;
  private Byte type;
  private Date versionUdate;
  private Date createdDate;
  private Date updatedDate;
  private String createdBy;
  private String updatedBy;
  private Boolean isDeleted = false;
  public Long getId() {
    return id;
  }
  public void setId(Long id) {
    this.id = id;
  }
  public String getAppid() {
    return appid;
  }
  public void setAppid(String appid) {
    this.appid = appid;
  }
  public String getSystemName() {
    return systemName;
  }
  public void setSystemName(String systemName) {
    this.systemName = systemName;
  }
  public Long getCurrentVersion() {
    return currentVersion;
  }
  public void setCurrentVersion(Long currentVersion) {
    this.currentVersion = currentVersion;
  }
  public String getCurrentVersionName() {
    return currentVersionName;
  }
  public void setCurrentVersionName(String currentVersionName) {
    this.currentVersionName = currentVersionName;
  }
  public Long getNewestVersion() {
    return newestVersion;
  }
  public void setNewestVersion(Long newestVersion) {
    this.newestVersion = newestVersion;
  }
  public String getNewestVersionName() {
    return newestVersionName;
  }
  public void setNewestVersionName(String newestVersionName) {
    this.newestVersionName = newestVersionName;
  }
  public String getDownloadLink() {
    return downloadLink;
  }
  public void setDownloadLink(String downloadLink) {
    this.downloadLink = downloadLink;
  }
  public String getDescription() {
    return description;
  }
  public void setDescription(String description) {
    this.description = description;
  }
  public Boolean getIsDisabled() {
    return isDisabled;
  }
  public void setIsDisabled(Boolean isDisabled) {
    this.isDisabled = isDisabled;
  }
  public Byte getType() {
    return type;
  }
  public void setType(Byte type) {
    this.type = type;
  }
  public Date getVersionUdate() {
    return versionUdate;
  }
  public void setVersionUdate(Date versionUdate) {
    this.versionUdate = versionUdate;
  }
  public Date getCreatedDate() {
    return createdDate;
  }
  public void setCreatedDate(Date createdDate) {
    this.createdDate = createdDate;
  }
  public Date getUpdatedDate() {
    return updatedDate;
  }
  public void setUpdatedDate(Date updatedDate) {
    this.updatedDate = updatedDate;
  }
  public String getCreatedBy() {
    return createdBy;
  }
  public void setCreatedBy(String createdBy) {
    this.createdBy = createdBy;
  }
  public String getUpdatedBy() {
    return updatedBy;
  }
  public void setUpdatedBy(String updatedBy) {
    this.updatedBy = updatedBy;
  }
  public Boolean getIsDeleted() {
    return isDeleted;
  }
  public void setIsDeleted(Boolean isDeleted) {
    this.isDeleted = isDeleted;
  }

}