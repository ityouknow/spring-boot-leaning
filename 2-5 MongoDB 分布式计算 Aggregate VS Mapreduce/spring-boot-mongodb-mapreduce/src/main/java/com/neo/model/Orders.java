package com.neo.model;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.springframework.data.mongodb.core.mapping.Field;

public class Orders {
        @Field("cust_id")
        private String custId;
        private long amount;
        private String status;

        public Orders(String custId, long amount, String status) {
                this.custId = custId;
                this.amount = amount;
                this.status = status;
        }

        public String getCustId() {
                return custId;
        }

        public void setCustId(String custId) {
                this.custId = custId;
        }

        public long getAmount() {
                return amount;
        }

        public void setAmount(long amount) {
                this.amount = amount;
        }

        public String getStatus() {
                return status;
        }

        public void setStatus(String status) {
                this.status = status;
        }

        @Override
        public String toString() {
                return ToStringBuilder.reflectionToString(this);
        }
}
