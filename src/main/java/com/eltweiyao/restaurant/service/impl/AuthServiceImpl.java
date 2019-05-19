package com.eltweiyao.restaurant.service.impl;

import com.eltweiyao.restaurant.dao.AuthMapper;
import com.eltweiyao.restaurant.service.AuthService;
import com.eltweiyao.restaurant.util.CodeHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

/**
 * @author weiyao
 * @date 2019/3/24.
 */
@Service
public class AuthServiceImpl implements AuthService {

    @Autowired
    private AuthMapper authMapper;

    @Override
    public Map<String, String> login(String account, String password) {
        return Optional.ofNullable(authMapper.login(account, password)).orElse(new HashMap<>());
    }


}
