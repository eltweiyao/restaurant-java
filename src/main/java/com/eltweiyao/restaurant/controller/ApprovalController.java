package com.eltweiyao.restaurant.controller;

import com.eltweiyao.restaurant.dto.Account;
import com.eltweiyao.restaurant.dto.WebPage;
import com.eltweiyao.restaurant.service.ApprovalService;
import com.eltweiyao.restaurant.util.ResultUtil;
import com.eltweiyao.restaurant.vo.Result;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author weiyao
 * @date 2019/5/25.
 */
@Slf4j
@RequestMapping("api/approval")
@RestController
public class ApprovalController {

    @Autowired
    private ApprovalService approvalService;

    @PostMapping("listAccount")
    public Result listAccount(@RequestParam(value = "pageno", defaultValue = "1") Integer pageNum,
                              @RequestParam(value = "rowcount", defaultValue = "10") Integer pageSize){
        PageHelper.startPage(pageNum, pageSize);

        List<Account> accounts = approvalService.listAccount();
        PageInfo pageInfo = new PageInfo(accounts);
        WebPage webPage = new WebPage(pageNum, pageSize, (int) pageInfo.getTotal());
        return ResultUtil.successPage(accounts, webPage);
    }

    @PostMapping("/agree")
    public Result agree(@RequestParam("pkCompany") String pkCompany){
        try {
            approvalService.agree(pkCompany);
        }catch (Exception e){
            log.error("同意授权出错， errMsg = {}, stack info=", e.getMessage(), e);
            return ResultUtil.error();
        }
        return ResultUtil.success();
    }

    @PostMapping("/reject")
    public Result reject(@RequestParam("pkCompany") String pkCompany){

        try {
            approvalService.reject(pkCompany);
        }catch (Exception e){
            log.error("删除账户出错， errMsg = {}, stack info=", e.getMessage(), e);
            return ResultUtil.error();
        }
        return ResultUtil.success();
    }



}
