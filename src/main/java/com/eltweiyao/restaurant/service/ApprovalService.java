package com.eltweiyao.restaurant.service;

import com.eltweiyao.restaurant.dto.Account;

import java.util.List;

/**
 * @author weiyao
 * @date 2019/5/25.
 */
public interface ApprovalService {

    List<Account> listAccount();


    /**
     * 授权
     * @param pkCompany
     */
    void agree(String pkCompany);

    /**
     * 删除授权
     * @param pkCompany
     */
    void reject(String pkCompany);
}
