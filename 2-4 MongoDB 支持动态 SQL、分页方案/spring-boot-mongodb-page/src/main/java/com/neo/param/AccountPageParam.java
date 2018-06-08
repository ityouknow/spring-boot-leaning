package com.neo.param;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.springframework.data.mongodb.core.mapping.Field;

public class AccountPageParam extends PageParam{
    private String accountNo;
    /** 账户结算冻结状态 Y 冻结  N 正常*/
    private String accountStatus;
    @Field("balance")
    private Long balanceBegin;
    @Field("balance")
    private Long balanceEnd;
    @Field("createDate")
    private String createDateBegin;
    @Field("createDate")
    private String createDateEnd;

    public String getAccountNo() {
        return accountNo;
    }

    public void setAccountNo(String accountNo) {
        this.accountNo = accountNo;
    }

    public String getAccountStatus() {
        return accountStatus;
    }

    public void setAccountStatus(String accountStatus) {
        this.accountStatus = accountStatus;
    }

    public Long getBalanceBegin() {
        return balanceBegin;
    }

    public void setBalanceBegin(Long balanceBegin) {
        this.balanceBegin = balanceBegin;
    }

    public Long getBalanceEnd() {
        return balanceEnd;
    }

    public void setBalanceEnd(Long balanceEnd) {
        this.balanceEnd = balanceEnd;
    }

    public String getCreateDateBegin() {
        return createDateBegin;
    }

    public void setCreateDateBegin(String createDateBegin) {
        this.createDateBegin = createDateBegin;
    }

    public String getCreateDateEnd() {
        return createDateEnd;
    }

    public void setCreateDateEnd(String createDateEnd) {
        this.createDateEnd = createDateEnd;
    }

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this);
    }
}
