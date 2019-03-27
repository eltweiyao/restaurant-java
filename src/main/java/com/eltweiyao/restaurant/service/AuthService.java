package com.eltweiyao.restaurant.service;

import org.springframework.stereotype.Service;

import java.util.Map;

/**
 * @author weiyao
 * @date 2019/3/24.
 */
public interface AuthService {

    /**
     * 登录
     * @param account
     * @param password
     * @return
     */
    Map<String, String> login(String account, String password);

}
