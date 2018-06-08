package com.neo.result;

import org.apache.commons.lang3.builder.ToStringBuilder;

public class OrderTotal {

   private String _id;
   private long total;
   private long sumTotal;

    public String get_id() {
        return _id;
    }

    public void set_id(String _id) {
        this._id = _id;
    }

    public long getTotal() {
        return total;
    }

    public void setTotal(long total) {
        this.total = total;
    }

    public long getSumTotal() {
        return sumTotal;
    }

    public void setSumTotal(long sumTotal) {
        this.sumTotal = sumTotal;
    }

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this);
    }
}
