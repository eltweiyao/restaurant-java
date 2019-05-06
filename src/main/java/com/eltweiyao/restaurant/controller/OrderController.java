package com.eltweiyao.restaurant.controller;

import com.eltweiyao.restaurant.context.RestaurantRequestContext;
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

        try {
            return ResultUtil.success(orderService.listCategory(RestaurantRequestContext.getPkStore(), RestaurantRequestContext.getPkCompany()));
        } catch (Exception e) {
            logger.error("查询菜品出错, errMsg = {}, stack info =", e.getMessage(), e);
            return ResultUtil.error();
        }
    }

    @PostMapping("/createOrder")
    public Result createOrder(@RequestBody List<Order> orderList) {

        try {
            orderService.createOrder(orderList, RestaurantRequestContext.getPkStore(), RestaurantRequestContext.getPkCompany());
            return ResultUtil.success();
        } catch (Exception e) {
            logger.error("查询菜品出错, errMsg = {}, stack info =", e.getMessage(), e);
            return ResultUtil.error();
        }
    }

    @PostMapping("/getTurnoverReport")
    public Result getTurnoverReport(@RequestParam String dateLevel) {

        try {
            return ResultUtil.success(orderService.getTurnoverReport(dateLevel, RestaurantRequestContext.getPkCompany()));
        } catch (Exception e) {
            logger.error("查询报表数据出错, errMsg = {}, stack info =", e.getMessage(), e);
            return ResultUtil.error();
        }
    }

    @PostMapping("/getDiagramReport")
    public Result getDiagramReport(@RequestParam String dateLevel) {

        try {
            return ResultUtil.success(orderService.getDiagramReport(dateLevel, RestaurantRequestContext.getPkCompany()));
        } catch (Exception e) {
            logger.error("查询报表数据出错, errMsg = {}, stack info =", e.getMessage(), e);
            return ResultUtil.error();
        }
    }

    @PostMapping("/getDishFanReport")
    public Result getDishFanReport(@RequestParam String dateLevel) {

        try {
            return ResultUtil.success(orderService.getDishFanReport(dateLevel, RestaurantRequestContext.getPkCompany()));
        } catch (Exception e) {
            logger.error("查询报表数据出错, errMsg = {}, stack info =", e.getMessage(), e);
            return ResultUtil.error();
        }
    }

    @PostMapping("/getStoreFanReport")
    public Result getStoreFanReport(@RequestParam String dateLevel) {

        try {
            orderService.getStoreFanReport(dateLevel, RestaurantRequestContext.getPkCompany());
            return ResultUtil.success(orderService.getStoreFanReport(dateLevel, RestaurantRequestContext.getPkCompany()));
        } catch (Exception e) {
            logger.error("查询报表数据出错, errMsg = {}, stack info =", e.getMessage(), e);
            return ResultUtil.error();
        }
    }
}
