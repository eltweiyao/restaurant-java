package com.eltweiyao.restaurant.service;

import com.eltweiyao.restaurant.pojo.Recipe;

import java.util.List;

/**
 * @author weiyao
 * @date 2019/3/24.
 */
public interface CategoryService {

    /**
     * 存储菜品类别
     * @param categoryName
     * @param pkCompany
     */
    void saveCategory(String categoryName, String pkCompany);

    /**
     * 查看类别是否存在
     * @param pkCategory
     * @param categoryName
     * @param pkCompany
     * @return
     */
    boolean checkCategoryExist(String pkCategory, String categoryName, String pkCompany);

    /**
     * 查询类别
     * @param pkCategory
     * @param categoryName
     * @param pkCompany
     * @return
     */
    List<Recipe> listCategory(String categoryName, String pkCompany);

    /**
     * 修改类别
     * @param pkCategory
     * @param categoryName
     * @param pkCompany
     */
    void updateCategory(String pkCategory, String categoryName, String pkCompany);


}
