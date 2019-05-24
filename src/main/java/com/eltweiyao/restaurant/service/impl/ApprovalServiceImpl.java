package com.eltweiyao.restaurant.service.impl;

import com.eltweiyao.restaurant.dao.AuthMapper;
import com.eltweiyao.restaurant.dto.Account;
import com.eltweiyao.restaurant.service.ApprovalService;
import com.eltweiyao.restaurant.service.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author weiyao
 * @date 2019/5/25.
 */
@Service
public class ApprovalServiceImpl implements ApprovalService {

    @Autowired
    private AuthMapper authMapper;

    @Override
    public List<Account> listAccount() {
        return authMapper.listAccount();
    }

    @Override
    public void agree(String pkCompany) {
        authMapper.agree(pkCompany);
    }

    @Override
    public void reject(String pkCompany) {
        authMapper.reject(pkCompany);
    }
}
