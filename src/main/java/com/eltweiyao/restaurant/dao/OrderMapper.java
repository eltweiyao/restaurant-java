package com.eltweiyao.restaurant.dao;

import com.eltweiyao.restaurant.dto.Category;
import com.eltweiyao.restaurant.dto.Order;
import com.eltweiyao.restaurant.dto.Recipe;
import org.apache.ibatis.annotations.Param;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

/**
 * @author weiyao
 * @date 2019/4/5.
 */
public interface OrderMapper {

    /**
     * 查询菜品机器类别
     *
     * @param pkStore
     * @param pkCompany
     * @return
     */
    List<Recipe> listCategory(@Param("pkStore") String pkStore, @Param("pkCompany") String pkCompany);

    void createOrder(@Param("orders")List<Order> orders, @Param("pkStore") String pkStore, @Param("pkCompany") String pkCompany, @Param("createTime") String createTime);

    List<Order> listOrder(@Param("pkCompany") String pkCompany, @Param("pkStore") String pkStore);

    Map<String, String> getTurnoverReport(@Param("startDate") LocalDateTime startDate, @Param("endDate") LocalDateTime endDate, @Param("pkCompany") String pkCompany);

    List<Map<String, String>> getDishFanReport(@Param("startDate") LocalDateTime startDate, @Param("endDate") LocalDateTime endDate, @Param("pkCompany") String pkCompany);

    int getDishCount(@Param("startDate") LocalDateTime startDate, @Param("endDate") LocalDateTime endDate, @Param("pkCompany") String pkCompany);

    List<Map<String, String>> getStoreFanReport(@Param("startDate") LocalDateTime startDate, @Param("endDate") LocalDateTime endDate, @Param("pkCompany") String pkCompany);

    double getStoreAmount(@Param("startDate") LocalDateTime startDate, @Param("endDate") LocalDateTime endDate, @Param("pkCompany") String pkCompany);

}
