package com.demo.demo01.utils;

public class BaseResponse {
    public BaseResponse()
    {
        this.code=0;
        this.msg="success";
    }
    public BaseResponse(Integer code,String msg) {
        this.code = code;
        this.msg=msg;
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    private Integer code;

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    private String msg;

    private Object object;

    public Object getObject() {
        return object;
    }

    public void setObject(Object object) {
        this.object = object;
    }

    public BaseResponse(Integer code,String msg,Object object) {
        this.code = code;
        this.msg =msg;
        this.object = object;
    }

    @Override
    public String toString() {
        return "BaseResponse{" +
                "code=" + code +
                ", msg='" + msg + '\'' +
                ", object=" + object +
                '}';
    }
}
