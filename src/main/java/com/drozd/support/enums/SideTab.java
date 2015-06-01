package com.drozd.support.enums;

public enum SideTab {
    LEASE_SUBJECT(1),
    HOME(2),
    REQUEST(3);

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
