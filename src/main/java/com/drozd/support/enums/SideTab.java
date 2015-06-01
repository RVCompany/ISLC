package com.drozd.support.enums;

public enum SideTab {
    LEASE_SUBJECT(1);

    private int code;

    SideTab(int code) {
        this.code = code;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }
}
