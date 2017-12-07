package com.kapphk.web.bean.finance;

import com.kapphk.web.bean.BaseModel;

/**
 * 主键：user_pay_password
 * @author zoneyu 2016-11-09
*/
public class BeanUserPayPassword extends BaseModel {

    /**
     * 表字段：user_pay_password.id 注释：主键id
     * @author zoneyu 2016-11-09
     */
    private Long id;
    /**
     * 表字段：user_pay_password.uid 注释：用户id
     * @author zoneyu 2016-11-09
     */
    private Long uid;
    /**
     * 表字段：user_pay_password.question1 注释：问题一
     * @author zoneyu 2016-11-09
     */
    private String question1;
    /**
     * 表字段：user_pay_password.answer1 注释：答案一
     * @author zoneyu 2016-11-09
     */
    private String answer1;
    /**
     * 表字段：user_pay_password.question2 注释：问题二
     * @author zoneyu 2016-11-09
     */
    private String question2;
    /**
     * 表字段：user_pay_password.answer2 注释：答案二
     * @author zoneyu 2016-11-09
     */
    private String answer2;
    /**
     * 表字段：user_pay_password.pwd 注释：支付密码
     * @author zoneyu 2016-11-09
     */
    private String pwd;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getUid() {
        return uid;
    }

    public void setUid(Long uid) {
        this.uid = uid;
    }

    public String getQuestion1() {
        return question1;
    }

    public void setQuestion1(String question1) {
        this.question1 = question1;
    }

    public String getAnswer1() {
        return answer1;
    }

    public void setAnswer1(String answer1) {
        this.answer1 = answer1;
    }

    public String getQuestion2() {
        return question2;
    }

    public void setQuestion2(String question2) {
        this.question2 = question2;
    }

    public String getAnswer2() {
        return answer2;
    }

    public void setAnswer2(String answer2) {
        this.answer2 = answer2;
    }

    public String getPwd() {
        return pwd;
    }

    public void setPwd(String pwd) {
        this.pwd = pwd;
    }
}