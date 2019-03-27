package com.eltweiyao.restaurant.dao;

import org.apache.ibatis.annotations.Param;

import java.util.Map;

/**
 * @author weiyao
 * @date 2019/3/24.
 */
public interface AuthMapper {

    Map<String, String> login(@Param("account") String account, @Param("password") String password);

}
