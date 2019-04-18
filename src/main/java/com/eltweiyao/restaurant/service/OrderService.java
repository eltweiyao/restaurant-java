package com.eltweiyao.restaurant.service;

import com.eltweiyao.restaurant.dto.Category;
import com.eltweiyao.restaurant.dto.Order;
import com.eltweiyao.restaurant.dto.Recipe;

import java.util.List;
import java.util.Map;

/**
 * @author weiyao
 * @date 2019/4/5.
 */
public interface OrderService {

    List<Category> listCategory(String pkStore, String pkCompany);

    void createOrder(List<Order> orderList,String pkStore, String pkCompany);

    List<Order> listOrder(String pkStore, String pkCompany);

    Map<String, String> getTurnoverReport(String level, String pkCompany);

    List<Map<String, String>> getDiagramReport(String level, String pkCompany);

    List<Map<String, String>> getDishFanReport(String level, String pkCompany);

    List<Map<String, String>> getStoreFanReport(String level, String pkCompany);

}
