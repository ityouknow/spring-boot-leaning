package com.neo.entity;

import org.apache.commons.lang3.builder.ToStringBuilder;

import java.io.Serializable;


public class UserEntity implements Serializable {
        private static final long serialVersionUID = -3258839839160856613L;
        private Long id;
        private String userName;
        private String passWord;

        public Long getId() {
                return id;
        }

        public void setId(Long id) {
                this.id = id;
        }

        public String getUserName() {
                return userName;
        }

        public void setUserName(String userName) {
                this.userName = userName;
        }

        public String getPassWord() {
                return passWord;
        }

        public void setPassWord(String passWord) {
                this.passWord = passWord;
        }


        @Override
        public String toString() {
                return ToStringBuilder.reflectionToString(this);
        }
}
