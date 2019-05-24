package com.eltweiyao.restaurant.controller;

import com.eltweiyao.restaurant.service.StoreService;
import com.eltweiyao.restaurant.util.CodeHelper;
import com.eltweiyao.restaurant.vo.Result;
import com.eltweiyao.restaurant.service.AuthService;
import com.eltweiyao.restaurant.util.ResultUtil;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;
import java.util.Map;

/**
 * @author weiyao
 * @date 2019/3/24.
 */
@Slf4j
@RestController
@RequestMapping("api/auth")
public class AuthController {

    @Autowired
    private AuthService authService;

    @Autowired
    private StoreService storeService;

    @PostMapping("/login")
    public Result login(@RequestParam("account") String account,
                        @RequestParam("password") String password,
                        HttpSession session){
        //登录管理员账户
        Map<String, String> map = authService.login(account, password);
        if (map != null && map.get("pkCompany") != null){
            session.setAttribute("pkCompany", map.get("pkCompany"));
            session.setAttribute("pkStore", map.get("pkStore"));
            session.setAttribute("accountType", map.get("accountType"));
            return ResultUtil.success(map);
        }
        return ResultUtil.error("账户名密码错误");
    }

    @PostMapping("/logout")
    public Result logout(HttpSession session){
        session.removeAttribute("pkCompany");
        return ResultUtil.success();
    }

    @PostMapping("register")
    public Result register(@RequestParam("account") String account,
                           @RequestParam("password") String password,
                           @RequestParam("companyName") String companyName){

        try {

            if (storeService.checkoutAccountExist(null, account, null)){
                return ResultUtil.error("账户已存在");
            }
            String pkCompany = CodeHelper.createUUID();
            authService.register(account, password, companyName, pkCompany);
            return ResultUtil.success();
        }catch (Exception e){
            log.error("注册失败，errMsg={}, stack info=", e.getMessage(), e);
            return ResultUtil.error();
        }

    }

}
