package com.eltweiyao.restaurant.service.impl;

import com.eltweiyao.restaurant.constant.DateLevel;
import com.eltweiyao.restaurant.dao.OrderMapper;
import com.eltweiyao.restaurant.dao.RecipeMapper;
import com.eltweiyao.restaurant.dto.Category;
import com.eltweiyao.restaurant.dto.Material;
import com.eltweiyao.restaurant.dto.Order;
import com.eltweiyao.restaurant.dto.Recipe;
import com.eltweiyao.restaurant.service.OrderService;
import org.apache.tomcat.jni.Local;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.rmi.MarshalledObject;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;

/**
 * @author weiyao
 * @date 2019/4/5.
 */
@Service
public class OrderServiceImpl implements OrderService {

    @Autowired
    private OrderMapper orderMapper;
    @Autowired
    private RecipeMapper recipeMapper;

    @Override
    public List<Category> listCategory(String pkStore, String pkCompany) {
        List<Recipe> recipes = Optional.ofNullable(orderMapper.listCategory(pkStore, pkCompany)).orElse(new ArrayList<>());
        Map<String, Category> categoryMap = new HashMap<>();
        for (Recipe recipe : recipes) {
            Category category = categoryMap.get(recipe.getPkCategory());
            List<Recipe> recipeList;
            if (category == null) {
                category = new Category();
                category.setCategoryName(recipe.getCategoryName());
                category.setPkCategory(recipe.getPkCategory());
                recipeList = new ArrayList<>();
                category.setRecipes(recipeList);
                recipeList.add(recipe);

            } else {
                recipeList = category.getRecipes();
                recipeList.add(recipe);
            }
            categoryMap.put(category.getPkCategory(), category);
        }

        return new ArrayList<>(categoryMap.values());
    }

    @Override
    public void createOrder(List<Order> orderList, String pkStore, String pkCompany) {
        if (CollectionUtils.isEmpty(orderList)){
            return;
        }
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        for (Order order : orderList) {
            List<Material> materials = recipeMapper.listRecipeMaterial(order.getPkRecipe(), pkCompany);
            double originalPrice = 0;
            for (Material material : materials) {
                originalPrice += material.getMaterialPrice() * material.getMaterialCount();
            }
            order.setOriginalPrice(originalPrice);
        }
        orderMapper.createOrder(orderList, pkStore, pkCompany, LocalDateTime.now().format(formatter));
    }

    @Override
    public List<Order> listOrder(String pkStore, String pkCompany) {
        return Optional.ofNullable(orderMapper.listOrder(pkCompany, pkStore)).orElse(new ArrayList<>());
    }

    @Override
    public Map<String, String> getTurnoverReport(String level, String pkCompany) {
        LocalDateTime startDate = LocalDateTime.now();
        LocalDateTime endDate = LocalDateTime.now();
        switch (level) {
            case DateLevel.TODAY:
                startDate = startDate.withHour(0).withMinute(0).withSecond(0);
                break;
            case DateLevel.YESTARDAY:
                startDate = startDate.minusDays(1).withHour(0).withMinute(0).withSecond(0);
                endDate = endDate.minusDays(1).withHour(11).withMinute(59).withSecond(59);
                break;
            case DateLevel.RECENTWEEK:
                startDate = startDate.minusDays(1).minusWeeks(1).withHour(0).withMinute(0).withSecond(0);
                endDate = endDate.minusDays(1).withHour(11).withMinute(59).withSecond(59);
                break;
            case DateLevel.RECENTMONTH:
                startDate = startDate.minusDays(1).minusMonths(1).withHour(0).withMinute(0).withSecond(0);
                endDate = endDate.minusDays(1).withHour(11).withMinute(59).withSecond(59);
                break;

        }
        Map<String, String> turnoverReport = Optional.ofNullable(orderMapper.getTurnoverReport(startDate, endDate, pkCompany)).orElse(new HashMap<>());
        return turnoverReport;
    }

    @Override
    public List<Map<String, String>> getDiagramReport(String level, String pkCompany) {
        LocalDateTime startDate = LocalDateTime.now();
        LocalDateTime endDate = LocalDateTime.now();
        List<Map<String, String>> result = new ArrayList<>();
        switch (level) {
            case DateLevel.TODAY:
                startDate = startDate.withHour(0).withMinute(0).withSecond(0);
                endDate = endDate.plusDays(1).withHour(0).withMinute(0).withSecond(0);
                while (!startDate.isAfter(endDate)) {
                    Map<String, String> map = Optional.ofNullable(orderMapper.getTurnoverReport(startDate, startDate.plusHours(1), pkCompany)).orElse(new HashMap<>());
                    map.put("time", startDate.format(DateTimeFormatter.ofPattern("HH:mm")));
                    result.add(map);
                    startDate = startDate.plusHours(1);
                }
                break;
            case DateLevel.YESTARDAY:
                startDate = startDate.minusDays(1).withHour(0).withMinute(0).withSecond(0);
                endDate = endDate.withHour(0).withMinute(0).withSecond(0);
                while (!startDate.isAfter(endDate)) {
                    Map<String, String> map = Optional.ofNullable(orderMapper.getTurnoverReport(startDate, startDate.plusHours(1), pkCompany)).orElse(new HashMap<>());
                    map.put("time", startDate.format(DateTimeFormatter.ofPattern("HH:mm")));
                    result.add(map);
                    startDate = startDate.plusHours(1);
                }
                break;
            case DateLevel.RECENTWEEK:
                startDate = startDate.minusWeeks(1).withHour(0).withMinute(0).withSecond(0);
                endDate = endDate.minusDays(1).withHour(11).withMinute(59).withSecond(59);
                while (startDate.isBefore(endDate)) {
                    Map<String, String> map = Optional.ofNullable(orderMapper.getTurnoverReport(startDate, startDate.plusDays(1), pkCompany)).orElse(new HashMap<>());
                    map.put("time", startDate.format(DateTimeFormatter.ofPattern("MM/dd")));
                    result.add(map);
                    startDate = startDate.plusDays(1);
                }
                break;
            case DateLevel.RECENTMONTH:
                startDate = startDate.minusMonths(1).withHour(0).withMinute(0).withSecond(0);
                endDate = endDate.minusDays(1).withHour(11).withMinute(59).withSecond(59);
                while (startDate.isBefore(endDate)) {
                    Map<String, String> map = Optional.ofNullable(orderMapper.getTurnoverReport(startDate, startDate.plusDays(1), pkCompany)).orElse(new HashMap<>());
                    map.put("time", startDate.format(DateTimeFormatter.ofPattern("MM/dd")));
                    result.add(map);
                    startDate = startDate.plusDays(1);
                }
                break;

        }
        return result;
    }

    @Override
    public List<Map<String, String>> getDishFanReport(String level, String pkCompany) {
        LocalDateTime startDate = LocalDateTime.now();
        LocalDateTime endDate = LocalDateTime.now();
        int total;
        List<Map<String, String>> result = new ArrayList<>();
        switch (level) {
            case DateLevel.TODAY:
                startDate = startDate.withHour(0).withMinute(0).withSecond(0);
                result = Optional.ofNullable(orderMapper.getDishFanReport(startDate, endDate, pkCompany)).orElse(new ArrayList<>());
                total = orderMapper.getDishCount(startDate, endDate, pkCompany);
                break;
            case DateLevel.YESTARDAY:
                startDate = startDate.minusDays(1).withHour(0).withMinute(0).withSecond(0);
                endDate = endDate.minusDays(1).withHour(11).withMinute(59).withSecond(59);
                result = Optional.ofNullable(orderMapper.getDishFanReport(startDate, endDate, pkCompany)).orElse(new ArrayList<>());
                total = orderMapper.getDishCount(startDate, endDate, pkCompany);
                break;
            case DateLevel.RECENTWEEK:
                startDate = startDate.minusDays(1).withHour(0).withMinute(0).withSecond(0);
                endDate = endDate.minusDays(1).withHour(11).withMinute(59).withSecond(59);
                result = Optional.ofNullable(orderMapper.getDishFanReport(startDate, endDate, pkCompany)).orElse(new ArrayList<>());
                total = orderMapper.getDishCount(startDate, endDate, pkCompany);
                break;
            case DateLevel.RECENTMONTH:
                startDate = startDate.minusDays(1).minusMonths(1).withHour(0).withMinute(0).withSecond(0);
                endDate = endDate.minusDays(1).withHour(11).withMinute(59).withSecond(59);
                result = Optional.ofNullable(orderMapper.getDishFanReport(startDate, endDate, pkCompany)).orElse(new ArrayList<>());
                total = orderMapper.getDishCount(startDate, endDate, pkCompany);
                break;
            default:
                total = 1;
        }
        if (total < 0 || CollectionUtils.isEmpty(result)) {
            return new ArrayList<>();
        }
        for (Map<String, String> map : result) {
            Object count = map.get("number") != null ? map.get("number") : 0;
            map.put("rate", String.valueOf(Double.parseDouble(count.toString()) * 100 / total));
        }
        return result;
    }

    @Override
    public List<Map<String, String>> getStoreFanReport(String level, String pkCompany) {
        LocalDateTime startDate = LocalDateTime.now();
        LocalDateTime endDate = LocalDateTime.now();
        double total;
        List<Map<String, String>> result = new ArrayList<>();
        switch (level) {
            case DateLevel.TODAY:
                startDate = startDate.withHour(0).withMinute(0).withSecond(0);
                result = Optional.ofNullable(orderMapper.getStoreFanReport(startDate, endDate, pkCompany)).orElse(new ArrayList<>());
                total = orderMapper.getStoreAmount(startDate, endDate, pkCompany);
                break;
            case DateLevel.YESTARDAY:
                startDate = startDate.minusDays(1).withHour(0).withMinute(0).withSecond(0);
                endDate = endDate.minusDays(1).withHour(11).withMinute(59).withSecond(59);
                result = Optional.ofNullable(orderMapper.getStoreFanReport(startDate, endDate, pkCompany)).orElse(new ArrayList<>());
                total = orderMapper.getStoreAmount(startDate, endDate, pkCompany);
                break;
            case DateLevel.RECENTWEEK:
                startDate = startDate.minusDays(1).withHour(0).withMinute(0).withSecond(0);
                endDate = endDate.minusDays(1).withHour(11).withMinute(59).withSecond(59);
                result = Optional.ofNullable(orderMapper.getStoreFanReport(startDate, endDate, pkCompany)).orElse(new ArrayList<>());
                total = orderMapper.getStoreAmount(startDate, endDate, pkCompany);
                break;
            case DateLevel.RECENTMONTH:
                startDate = startDate.minusDays(1).minusMonths(1).withHour(0).withMinute(0).withSecond(0);
                endDate = endDate.minusDays(1).withHour(11).withMinute(59).withSecond(59);
                result = Optional.ofNullable(orderMapper.getStoreFanReport(startDate, endDate, pkCompany)).orElse(new ArrayList<>());
                total = orderMapper.getStoreAmount(startDate, endDate, pkCompany);
                break;
            default:
                total = 1;
        }
        if (total < 0 || CollectionUtils.isEmpty(result)) {
            return new ArrayList<>();
        }
        for (Map<String, String> map : result) {
            Object bonus = map.get("number") != null ? map.get("number") : "0";
            map.put("rate", String.valueOf(Double.parseDouble(bonus.toString()) * 100 / total));
        }
        return result;
    }
}
