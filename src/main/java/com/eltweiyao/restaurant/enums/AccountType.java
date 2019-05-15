package com.eltweiyao.restaurant.enums;

import lombok.Getter;

import java.util.HashMap;
import java.util.Map;

/**
 * @author weiyao
 * @date 2019/5/4.
 */
@Getter
public enum AccountType {

    ADMIN(1, "管理员"),
    STORE(2, "门店");

    private Integer code;
    private String desc;

    AccountType(Integer code, String desc) {
        this.code = code;
        this.desc = desc;
    }

    static Map<Integer, AccountType> map = new HashMap<>();

    static {
        for (AccountType e : AccountType.values()) {
            map.put(e.getCode(), e);
        }
    }

    public static boolean isAdmin(Integer i) {
        if (map.get(i) == ADMIN) {
            return true;
        }
        return false;
    }


}
