package com.demo.demo01.bean;

public class Msgboard {
    private Integer id;

    private String words;

    private Long tel;

    private Integer userid;

    private String time;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getWords() {
        return words;
    }

    public void setWords(String words) {
        this.words = words == null ? null : words.trim();
    }

    public Long getTel() {
        return tel;
    }

    public void setTel(Long tel) {
        this.tel = tel;
    }

    public Integer getUserid() {
        return userid;
    }

    public void setUserid(Integer userid) {
        this.userid = userid;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time == null ? null : time.trim();
    }

    @Override
    public String toString() {
        return "Msgboard{" +
                "id=" + id +
                ", words='" + words + '\'' +
                ", tel='" + tel + '\'' +
                ", userid=" + userid +
                ", time='" + time + '\'' +
                '}';
    }

    public Msgboard(String words, Long tel, Integer userid, String time) {
        this.words = words;
        this.tel = tel;
        this.userid = userid;
        this.time = time;
    }

    public Msgboard(String words, Long tel, String time) {
        this.words = words;
        this.tel = tel;
        this.time = time;
    }

    public Msgboard() {
    }
}