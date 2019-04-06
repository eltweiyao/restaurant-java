package com.eltweiyao.restaurant.controller;

import com.eltweiyao.restaurant.dto.Order;
import com.eltweiyao.restaurant.dto.Recipe;
import com.eltweiyao.restaurant.service.OrderService;
import com.eltweiyao.restaurant.util.ResultUtil;
import com.eltweiyao.restaurant.vo.Result;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * @author weiyao
 * @date 2019/4/5.
 */
@RestController
@RequestMapping("/api/order")
public class OrderController {

    private Logger logger = LoggerFactory.getLogger(OrderController.class);

    @Autowired
    private OrderService orderService;

    @PostMapping("/listCategory")
    public Result listCategory(HttpServletRequest request) {
        String pkCompany = String.valueOf(request.getSession().getAttribute("pkCompany"));
        if (pkCompany == null || pkCompany.equals("") || pkCompany.equals("null")) {
            return ResultUtil.error("未获取到当前登录人权限信息");
        }
        String pkStore = String.valueOf(request.getSession().getAttribute("pkStore"));
        if (pkStore == null || pkStore.equals("") || pkStore.equals("null")) {
            return ResultUtil.error("未获取到当前登录人权限信息");
        }
        try {
            return ResultUtil.success(orderService.listCategory(pkStore, pkCompany));
        } catch (Exception e) {
            logger.error("查询菜品出错, errMsg = {}, stack info =", e.getMessage(), e);
            return ResultUtil.error();
        }
    }

    @PostMapping("/createOrder")
    public Result createOrder(@RequestBody List<Order> orderList,
                              HttpServletRequest request) {
        String pkCompany = String.valueOf(request.getSession().getAttribute("pkCompany"));
        if (pkCompany == null || pkCompany.equals("") || pkCompany.equals("null")) {
            return ResultUtil.error("未获取到当前登录人权限信息");
        }
        String pkStore = String.valueOf(request.getSession().getAttribute("pkStore"));
        if (pkStore == null || pkStore.equals("") || pkStore.equals("null")) {
            return ResultUtil.error("未获取到当前登录人权限信息");
        }
        try {
            orderService.createOrder(orderList, pkStore, pkCompany);
            orderService.listOrder(pkStore, pkCompany);
            return ResultUtil.success();
        } catch (Exception e) {
            logger.error("查询菜品出错, errMsg = {}, stack info =", e.getMessage(), e);
            return ResultUtil.error();
        }
    }
}
