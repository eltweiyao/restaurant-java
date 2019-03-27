package com.eltweiyao.restaurant.constant;

/**
 * @author weiyao
 * @date 2019/3/16.
 */
public enum ResponseCode {

    SUCCESS("200", "SUCCESS"),
    ERROR("500", "ERROR");


    private final String code;
    private final String desc;

    ResponseCode(String code, String desc) {
        this.code = code;
        this.desc = desc;
    }
    public String getCode() {
        return code;
    }
    public String getDesc() {
        return desc;
    }

}
