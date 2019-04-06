package com.eltweiyao.restaurant.service.impl;

import com.eltweiyao.restaurant.dao.CategoryMapper;
import com.eltweiyao.restaurant.dto.Recipe;
import com.eltweiyao.restaurant.service.CategoryService;
import com.eltweiyao.restaurant.util.CodeHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * @author weiyao
 * @date 2019/3/24.
 */
@Service
public class CategoryServiceImpl implements CategoryService {

    @Autowired
    private CategoryMapper categoryMapper;

    @Override
    public void saveCategory(String categoryName, String pkCompany) {
        categoryMapper.saveCategory(CodeHelper.createUUID(), categoryName, pkCompany);
    }

    @Override
    public boolean checkCategoryExist(String pkCategory, String categoryName, String pkCompany) {
        if (categoryMapper.checkCategoryExist(pkCompany, categoryName, pkCategory ) > 0){
            return true;
        }
        return false;
    }

    @Override
    public List<Recipe> listCategory(String categoryName, String pkCompany) {
        return Optional.ofNullable(categoryMapper.listCategory(categoryName, pkCompany)).orElse(new ArrayList<>());
    }

    @Override
    public void updateCategory(String pkCategory, String categoryName, String pkCompany) {
        categoryMapper.updateCategory(pkCategory, categoryName, pkCompany);

    }
}
