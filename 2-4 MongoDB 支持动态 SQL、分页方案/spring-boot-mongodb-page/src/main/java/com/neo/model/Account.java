package com.neo.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import org.apache.commons.lang3.builder.ToStringBuilder;

import java.util.Date;

public class Account {
        private String accountNo;
        /** 账户结算冻结状态 Y 冻结  N 正常*/
        private String accountStatus;
        private Long balance;
        private Date createDate;

        public Account(String accountNo, String accountStatus, Long balance, Date createDate) {
                this.accountNo = accountNo;
                this.accountStatus = accountStatus;
                this.balance = balance;
                this.createDate = createDate;
        }

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

        public Long getBalance() {
                return balance;
        }

        public void setBalance(Long balance) {
                this.balance = balance;
        }

        public Date getCreateDate() {
                return createDate;
        }

        public void setCreateDate(Date createDate) {
                this.createDate = createDate;
        }

        @Override
        public String toString() {
                return ToStringBuilder.reflectionToString(this);
        }
}
