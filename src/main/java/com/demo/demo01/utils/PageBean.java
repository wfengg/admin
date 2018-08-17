package com.demo.demo01.utils;

import java.util.List;

public class PageBean<T> {
    private int pageNumber;  //当前页
    private int pageSize;    //每页显示个数
    private int totalRecord; //总记录数
    private int totalPage;   //总分页数
    private int startIndex;  //起始索引
    private List<T> data; //分页数据
    private List<String> username;

    public PageBean(int pageNumber,int pageSize,int totalRecord) {
        this.pageNumber = pageNumber;
        this.pageSize = pageSize;
        this.totalRecord = totalRecord;
        this.totalPage = (totalRecord%pageSize)==0?(totalRecord/pageSize):(totalRecord/pageSize+1) ;
        this.startIndex = (pageNumber-1)*pageSize;
    }

    public int getPageNumber() {
        return pageNumber;
    }

    public void setPageNumber(int pageNumber) {
        this.pageNumber = pageNumber;
    }

    public int getPageSize() {
        return pageSize;
    }

    public int getTotalRecord() {
        return totalRecord;
    }

    public void setTotalRecord(int totalRecord) {
        this.totalRecord = totalRecord;
    }

    public int getTotalPage() {
        return totalPage;
    }

    public void setTotalPage(int totalPage) {
        this.totalPage = totalPage;
    }

    public int getStartIndex() {
        return startIndex;
    }

    public void setStartIndex(int startIndex) {
        this.startIndex = startIndex;
    }

    public List<T> getData() {
        return data;
    }

    public void setData(List<T> data) {
        this.data = data;
    }

    public List<String> getUsername() {
        return username;
    }

    public void setUsername(List<String> username) {
        this.username = username;
    }

    @Override
    public String toString() {
        return "PageBean{" +
                "pageNumber=" + pageNumber +
                ", pageSize=" + pageSize +
                ", totalRecord=" + totalRecord +
                ", totalPage=" + totalPage +
                ", startIndex=" + startIndex +
                ", data=" + data +
                '}';
    }

    public PageBean() {
    }

}
