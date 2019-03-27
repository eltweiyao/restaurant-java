package com.eltweiyao.restaurant.util;

import com.eltweiyao.restaurant.constant.ResponseCode;
import com.eltweiyao.restaurant.vo.Result;
import com.eltweiyao.restaurant.constant.WebPage;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

/**
 * @author weiyao
 * @date 2019/3/16.
 */
@JsonSerialize(include = JsonSerialize.Inclusion.NON_NULL)
public class ResultUtil<T> {

    public static <T> Result success() {
        return new Result(ResponseCode.SUCCESS.getCode(), "");
    }

    public static <T> Result success(T data) {
        return new Result(ResponseCode.SUCCESS.getCode(), "", data);
    }

    public static <T> Result successPage(T data, WebPage page) {
        return new Result(ResponseCode.SUCCESS.getCode(), "", data, page);
    }

    public static <T> Result error() {
        return new Result(ResponseCode.ERROR.getCode(), "");
    }

    public static <T> Result error(String msg) {
        return new Result(ResponseCode.ERROR.getCode(), msg);
    }
}
