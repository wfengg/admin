package com.demo.demo01.bean;

public class Image {
    private Integer id;

    private Integer uid;

    private String imgurl;

    private Long imgsize;

    private String imgdate;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getUid() {
        return uid;
    }

    public void setUid(Integer uid) {
        this.uid = uid;
    }

    public String getImgurl() {
        return imgurl;
    }

    public void setImgurl(String imgurl) {
        this.imgurl = imgurl == null ? null : imgurl.trim();
    }

    public Long getImgsize() {
        return imgsize;
    }

    public void setImgsize(Long imgsize) {
        this.imgsize = imgsize;
    }

    public String getImgdate() {
        return imgdate;
    }

    public void setImgdate(String imgdate) {
        this.imgdate = imgdate == null ? null : imgdate.trim();
    }

    public Image(Integer uid, String imgurl, Long imgsize, String imgdate) {
        this.uid = uid;
        this.imgurl = imgurl;
        this.imgsize = imgsize;
        this.imgdate = imgdate;
    }

    public Image() {
    }
}