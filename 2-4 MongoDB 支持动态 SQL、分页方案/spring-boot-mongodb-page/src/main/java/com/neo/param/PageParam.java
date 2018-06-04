package com.neo.param;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.springframework.data.mongodb.core.mapping.Field;

/**
 * 分页参数封装
 *
 */
public class PageParam {
    @Field("-")
    private Integer beginLine = 0;
    @Field("-")
    private Integer pageSize = 3;
    @Field("-")
    private Integer currentPage=1; 	   // 当前页
    @Field("-")
    private Integer endLine;		   // 结束行

    public Integer getBeginLine() {
        return beginLine;
    }

    public void setBeginLine(Integer beginLine) {
        this.beginLine = beginLine;
    }

    public Integer getPageSize() {
        return pageSize;
    }

    public void setPageSize(Integer pageSize) {
        this.pageSize = pageSize;
    }

    public Integer getCurrentPage() {
        return currentPage;
    }

    public void setCurrentPage(Integer currentPage) {
        this.currentPage = currentPage;
    }

    public Integer getEndLine() {
        return endLine;
    }

    public void setEndLine(Integer endLine) {
        this.endLine = endLine;
    }

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this);
    }
}
