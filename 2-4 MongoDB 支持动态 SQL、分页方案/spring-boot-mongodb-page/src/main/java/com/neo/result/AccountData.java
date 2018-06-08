package com.neo.result;

import com.fasterxml.jackson.annotation.JsonFormat;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;
@Document(collection = "account")
public class AccountData {
        private String accountNo;
        private String accountStatus;
        private Long balance;
        @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
        private Date createDate;

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
