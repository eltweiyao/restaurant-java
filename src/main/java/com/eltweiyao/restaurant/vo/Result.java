package com.eltweiyao.restaurant.vo;

import com.eltweiyao.restaurant.dto.WebPage;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

/**
 * @author weiyao
 * @date 2019/3/16.
 */
@JsonSerialize(include = JsonSerialize.Inclusion.NON_NULL)
public class Result<T> {

    private String code;
    private String msg;
    private T data;
    private WebPage page;

    public Result(String code, String msg, T data, WebPage page) {
        this.code = code;
        this.msg = msg;
        this.data = data;
        this.page = page;
    }

    public Result(String code, String msg, T data) {
        this.code = code;
        this.msg = msg;
        this.data = data;
    }

    public Result(String code, String msg) {
        this.code = code;
        this.msg = msg;
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

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public WebPage getPage() {
        return page;
    }

    public void setPage(WebPage page) {
        this.page = page;
    }
}
