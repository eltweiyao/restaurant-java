package com.eltweiyao.restaurant.controller;

import com.eltweiyao.restaurant.vo.Result;
import com.eltweiyao.restaurant.service.AuthService;
import com.eltweiyao.restaurant.util.ResultUtil;
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
@RestController
@RequestMapping("api/auth")
public class AuthController {

    @Autowired
    private AuthService authService;

    @PostMapping("/login")
    public Result login(@RequestParam("account") String account,
                        @RequestParam("password") String password,
                        HttpSession session){
        Map<String, String> map = authService.login(account, password);
        if (map != null && map.get("pkCompany") != null){
            session.setAttribute("pkCompany", map.get("pkCompany"));
            return ResultUtil.success(map);
        }
        return ResultUtil.error("账户名密码错误");
    }

    @PostMapping("/logout")
    public Result logout(HttpSession session){
        session.removeAttribute("pkCompany");
        return ResultUtil.success();
    }

}
