package com.kapphk.web.bean.user;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

import com.kapphk.web.bean.BaseModel;

/**
 * 用户：sys_user
 * @author zoneyu 2016-10-18
*/
public class BeanUser extends BaseModel implements Serializable {

	private static final long serialVersionUID = 4914073939345865014L;
	
	/**
     * 表字段：sys_user.id 注释：用户id
     * @author zoneyu 2016-10-18
     */
    private Long id;
    /**
     * 表字段：sys_user.user_name 注释：用户账号（手机号码）
     * @author zoneyu 2016-10-18
     */
    private String userName;
    /**
     * 表字段：sys_user.pwd 注释：密码
     * @author zoneyu 2016-10-18
     */
    private String pwd;
    /**
     * 表字段：sys_user.nick_name 注释：用户昵称
     * @author zoneyu 2016-10-18
     */
    private String nickName;
    /**
     * 表字段：sys_user.real_name 注释：真实姓名
     * @author zoneyu 2016-10-18
     */
    private String realName;
    /**
     * 表字段：sys_user.logo_path 注释：用户头像路径
     * @author zoneyu 2016-10-18
     */
    private String logoPath;
    /**
     * 表字段：sys_user.sex 注释：性别 1:男 2:女
     * @author zoneyu 2016-10-18
     */
    private Integer sex;
    private String position;
    /**
     * 表字段：sys_user.province 注释：省
     * @author zoneyu 2016-10-18
     */
    private String province;
    /**
     * 表字段：sys_user.city 注释：市
     * @author zoneyu 2016-10-18
     */
    private String city;
    /**
     * 表字段：sys_user.district 注释：区
     * @author zoneyu 2016-10-18
     */
    private String district;
    /**
     * 表字段：sys_user.blue_currency 注释：蓝币
     * @author zoneyu 2016-10-18
     */
    private BigDecimal blueCurrency;
    /**
     * 表字段：sys_user.red_currency 注释：红币
     * @author zoneyu 2016-10-18
     */
    private BigDecimal redCurrency;
    /**
     * 表字段：sys_user.invite_code 注释：邀请码
     * @author zoneyu 2016-10-18
     */
    private String inviteCode;
    /**
     * 表字段：sys_user.id_card 注释：身份证号
     * @author zoneyu 2016-10-18
     */
    private String idCard;
    /**
     * 表字段：sys_user.id_photo_front 注释：身份证正面
     * @author zoneyu 2016-10-18
     */
    private String idPhotoFront;
    /**
     * 表字段：sys_user.id_photo_back 注释：身份证反面
     * @author zoneyu 2016-10-18
     */
    private String idPhotoBack;
    /**
     * 表字段：sys_user.uid_from 注释：邀请人
     * @author zoneyu 2016-10-18
     */
    private Long uidFrom;
    /**
     * 表字段：sys_user.mail 注释：邮箱
     * @author zoneyu 2016-10-18
     */
    private String mail;
    /**
     * 表字段：sys_user.wechat 注释：微信
     * @author zoneyu 2016-10-18
     */
    private String wechat;
    /**
     * 表字段：sys_user.due_date 注释：截止期限
     * @author zoneyu 2016-10-18
     */
    private Date dueDate;
    /**
     * 表字段：sys_user.reject_reason 注释：拒绝原因
     * @author zoneyu 2016-10-18
     */
    private String rejectReason;
    /**
     * 表字段：sys_user.longitude 注释：经度
     * @author zoneyu 2016-10-18
     */
    private BigDecimal longitude;
    /**
     * 表字段：sys_user.latitude 注释：纬度
     * @author zoneyu 2016-10-18
     */
    private BigDecimal latitude;
    /**
     * 表字段：sys_user.is_permissions 注释：是否有验证课程权限(0：否，1：是)
     * @author zoneyu 2016-10-18
     */
    private Integer isPermissions;
    /**
     * 表字段：sys_user.service_begin 注释：增量客服id
     * @author zoneyu 2016-10-18
     */
    private Long serviceBegin;
    /**
     * 表字段：sys_user.service_stock 注释：存量客服id
     * @author zoneyu 2016-10-18
     */
    private Long serviceStock;
    
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPwd() {
        return pwd;
    }

    public void setPwd(String pwd) {
        this.pwd = pwd;
    }

    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }

    public String getRealName() {
        return realName;
    }

    public void setRealName(String realName) {
        this.realName = realName;
    }

    public String getLogoPath() {
        return logoPath;
    }

    public void setLogoPath(String logoPath) {
        this.logoPath = logoPath;
    }

    public Integer getSex() {
        return sex;
    }

    public void setSex(Integer sex) {
        this.sex = sex;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public String getProvince() {
        return province;
    }

    public void setProvince(String province) {
        this.province = province;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getDistrict() {
        return district;
    }

    public void setDistrict(String district) {
        this.district = district;
    }

    public BigDecimal getBlueCurrency() {
        return blueCurrency;
    }

    public void setBlueCurrency(BigDecimal blueCurrency) {
        this.blueCurrency = blueCurrency;
    }

    public BigDecimal getRedCurrency() {
        return redCurrency;
    }

    public void setRedCurrency(BigDecimal redCurrency) {
        this.redCurrency = redCurrency;
    }

    public String getInviteCode() {
        return inviteCode;
    }

    public void setInviteCode(String inviteCode) {
        this.inviteCode = inviteCode;
    }

    public String getIdCard() {
        return idCard;
    }

    public void setIdCard(String idCard) {
        this.idCard = idCard;
    }

    public String getIdPhotoFront() {
        return idPhotoFront;
    }

    public void setIdPhotoFront(String idPhotoFront) {
        this.idPhotoFront = idPhotoFront;
    }

    public String getIdPhotoBack() {
        return idPhotoBack;
    }

    public void setIdPhotoBack(String idPhotoBack) {
        this.idPhotoBack = idPhotoBack;
    }

    public Long getUidFrom() {
        return uidFrom;
    }

    public void setUidFrom(Long uidFrom) {
        this.uidFrom = uidFrom;
    }

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    public String getWechat() {
        return wechat;
    }

    public void setWechat(String wechat) {
        this.wechat = wechat;
    }

    public Date getDueDate() {
        return dueDate;
    }

    public void setDueDate(Date dueDate) {
        this.dueDate = dueDate;
    }

    public String getRejectReason() {
        return rejectReason;
    }

    public void setRejectReason(String rejectReason) {
        this.rejectReason = rejectReason;
    }

    public BigDecimal getLongitude() {
        return longitude;
    }

    public void setLongitude(BigDecimal longitude) {
        this.longitude = longitude;
    }

    public BigDecimal getLatitude() {
        return latitude;
    }

    public void setLatitude(BigDecimal latitude) {
        this.latitude = latitude;
    }

    public Integer getIsPermissions() {
        return isPermissions;
    }

    public void setIsPermissions(Integer isPermissions) {
        this.isPermissions = isPermissions;
    }

	public Long getServiceBegin() {
		return serviceBegin;
	}

	public void setServiceBegin(Long serviceBegin) {
		this.serviceBegin = serviceBegin;
	}

	public Long getServiceStock() {
		return serviceStock;
	}

	public void setServiceStock(Long serviceStock) {
		this.serviceStock = serviceStock;
	}
    
}