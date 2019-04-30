package com.tomcat.doman;

import java.util.List;

public class PageBean {
    private List<Contact> list;//当前页的联系人集合
    private Long totalSize;//总数据条数
    private Integer pageSize;//每页的数据条数
    private Long totalPage;//总页数
    private Long curPage;//当前页数

    public PageBean() {
    }

    public PageBean(List<Contact> list, Long totalSize, Integer pageSize, Long totalPage, Long curPage) {
        this.list = list;
        this.totalSize = totalSize;
        this.pageSize = pageSize;
        this.totalPage = totalPage;
        this.curPage = curPage;
    }

    @Override
    public String toString() {
        return "PageBean{" +
                "list=" + list +
                ", totalSize=" + totalSize +
                ", pageSize=" + pageSize +
                ", totalPage=" + totalPage +
                ", curPage=" + curPage +
                '}';
    }

    public List<Contact> getList() {
        return list;
    }

    public void setList(List<Contact> list) {
        this.list = list;
    }

    public Long getTotalSize() {
        return totalSize;
    }

    public void setTotalSize(long totalSize) {
        this.totalSize = totalSize;
    }

    public Integer getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    public Long getTotalPage() {
        return totalPage;
    }

    public void setTotalPage(long totalPage) {
        this.totalPage = totalPage;
    }

    public Long getCurPage() {
        return curPage;
    }

    public void setCurPage(long curPage) {
        this.curPage = curPage;
    }
}
