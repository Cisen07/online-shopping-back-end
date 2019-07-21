package com.demo.mms.common.domain;

public class Kind {
    private String kindid;

    private String kindname;

    public String getKindid() {
        return kindid;
    }

    public void setKindid(String kindid) {
        this.kindid = kindid == null ? null : kindid.trim();
    }

    public String getKindname() {
        return kindname;
    }

    public void setKindname(String kindname) {
        this.kindname = kindname == null ? null : kindname.trim();
    }
}