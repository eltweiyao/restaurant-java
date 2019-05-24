package com.eltweiyao.restaurant.dao;

import com.eltweiyao.restaurant.dto.Account;
import com.eltweiyao.restaurant.dto.Store;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

/**
 * @author weiyao
 * @date 2019/3/24.
 */
@Repository
public interface AuthMapper {

    Map<String, String> login(@Param("account") String account, @Param("password") String password);

    int checkoutAccountExist(@Param("account") String account, @Param("pkStore") String pkStore,
                             @Param("pkCompany") String pkCompany);

    void saveStoreAccount(Store store);

    void updateStoreAccount(Store store);

    void register(@Param("account") String account, @Param("password") String password,
                  @Param("companyName") String companyName, @Param("pkCompany") String pkCompany);

    List<Account> listAccount();

    void agree(@Param("pkCompany") String pkCompany);

    void reject(@Param("pkCompany") String pkCompany);

}
