package com.demo.mms.common.domain;

import com.demo.mms.common.utils.DateUtil;

import java.util.Date;

public class Student {
    private String id;

    private String name;

    private Integer sex;

    private String stunumber;

    private Date birthday;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id == null ? null : id.trim();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    public Integer getSex() {
        return sex;
    }

    public String getSexTxt(){
        return sex == 1 ? "男" :"女";
    }

    public void setSex(Integer sex) {
        this.sex = sex;
    }

    public String getStunumber() {
        return stunumber;
    }

    public void setStunumber(String stunumber) {
        this.stunumber = stunumber == null ? null : stunumber.trim();
    }

    public Date getBirthday() {
        return birthday;
    }

    public String getBirthdayTxt() {
        if(birthday == null){
            return null;
        }
        return DateUtil.parseDateToStr(birthday, DateUtil.DATE_FORMAT_YYYY_MM_DD);
    }

    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }
}