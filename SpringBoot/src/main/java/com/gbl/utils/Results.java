package com.gbl.utils;

/**
 *
 */
public class Results {

    private static final String SUCCESS = "0";
    private static final String ERROR = "-1";

    private String code;
    private String msg;
    private Object data;

    public static Results success() {
        Results result = new Results();
        result.setCode(SUCCESS);
        return result;
    }

    public static Results success(Object data) {
        Results result = new Results();
        result.setCode(SUCCESS);
        result.setData(data);
        return result;
    }

    public static Results error(String msg) {
        Results result = new Results();
        result.setCode(ERROR);
        result.setMsg(msg);
        return result;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }
}
