package com.eltweiyao.restaurant.dao;

import com.eltweiyao.restaurant.dto.Store;
import org.apache.ibatis.annotations.Param;

import java.util.Map;

/**
 * @author weiyao
 * @date 2019/3/24.
 */
public interface AuthMapper {

    Map<String, String> login(@Param("account") String account, @Param("password") String password);

    void saveStoreAccount(Store store);

    void updateStoreAccount(Store store);

}
