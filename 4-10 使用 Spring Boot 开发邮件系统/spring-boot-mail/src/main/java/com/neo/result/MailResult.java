package com.neo.result;

public class MailResult {
    private String rspCode;
    private String rspMsg;

    public MailResult() {
        this.rspCode = "00";
        this.rspMsg = "发送成功";
    }

    public String getRspCode() {
        return rspCode;
    }

    public void setRspCode(String rspCode) {
        this.rspCode = rspCode;
    }

    public String getRspMsg() {
        return rspMsg;
    }

    public void setRspMsg(String rspMsg) {
        this.rspMsg = rspMsg;
    }
}
