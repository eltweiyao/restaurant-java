package com.eltweiyao.restaurant.dao;

import com.eltweiyao.restaurant.dto.Recipe;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author weiyao
 * @date 2019/3/24.
 */
public interface CategoryMapper {


    void saveCategory(@Param("pkCategory") String pkCategory, @Param("categoryName") String categoryName,
                      @Param("pkCompany") String pkCompany);

    List<Recipe> listCategory(@Param("categoryName") String categoryName, @Param("pkCompany") String pkCompany);

    void updateCategory(@Param("pkCategory") String pkCategory, @Param("categoryName") String categoryName,
                        @Param("pkCompany") String pkCompany);

    int checkCategoryExist(@Param("pkCompany") String pkCompany, @Param("pkCategory") String pkCategory,
                              @Param("categoryName") String categoryName);

    }
