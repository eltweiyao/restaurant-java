package com.eltweiyao.restaurant.service.impl;

import com.eltweiyao.restaurant.dao.OrderMapper;
import com.eltweiyao.restaurant.dto.Category;
import com.eltweiyao.restaurant.dto.Order;
import com.eltweiyao.restaurant.dto.Recipe;
import com.eltweiyao.restaurant.service.OrderService;
import com.eltweiyao.restaurant.service.RecipeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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

    @Override
    public List<Category> listCategory(String pkStore, String pkCompany) {
        List<Recipe> recipes = Optional.ofNullable(orderMapper.listCategory(pkStore, pkCompany)).orElse(new ArrayList<>());
        Map<String, Category> categoryMap = new HashMap<>();
        for (Recipe recipe : recipes) {
            Category category = categoryMap.get(recipe.getPkCategory());
            List<Recipe> recipeList;
            if (category == null){
                category = new Category();
                category.setCategoryName(recipe.getCategoryName());
                category.setPkCategory(recipe.getPkCategory());
                recipeList = new ArrayList<>();
                category.setRecipes(recipeList);
                recipeList.add(recipe);

            }else {
                recipeList = category.getRecipes();
                recipeList.add(recipe);
            }
            categoryMap.put(category.getPkCategory(), category);
        }

        return new ArrayList<>(categoryMap.values());
    }

    @Override
    public void createOrder(List<Order> orderList, String pkStore, String pkCompany) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd hh:mm:ss");
        orderMapper.createOrder(orderList, pkStore, pkCompany, LocalDateTime.now().format(formatter));
    }

    @Override
    public List<Order> listOrder(String pkStore, String pkCompany) {
        return Optional.ofNullable(orderMapper.listOrder(pkCompany, pkStore)).orElse(new ArrayList<>());
    }
}
