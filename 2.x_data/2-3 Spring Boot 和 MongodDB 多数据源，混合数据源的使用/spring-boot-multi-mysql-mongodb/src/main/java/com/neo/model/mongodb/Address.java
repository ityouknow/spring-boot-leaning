package com.neo.model.mongodb;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.persistence.Id;
import java.io.Serializable;

@Document
public class Address implements Serializable {
        private static final long serialVersionUID = -3258839839160856613L;
        @Id
        private String  id;
        private String province;
        private String city;

        public Address(String province, String city) {
                this.province = province;
                this.city = city;
        }

        public String getId() {
                return id;
        }

        public void setId(String id) {
                this.id = id;
        }

        public String getProvince() {
                return province;
        }

        public void setProvince(String province) {
                this.province = province;
        }

        public String getCity() {
                return city;
        }

        public void setCity(String city) {
                this.city = city;
        }

        @Override
        public String toString() {
                return ToStringBuilder.reflectionToString(this);
        }
}
