package com.eltweiyao.restaurant.dto;

import lombok.Data;

import java.util.List;

/**
 * @author weiyao
 * @date 2019/4/5.
 */
@Data
public class Category {

    private String pkCategory;
    private String categoryName;
    private List<Recipe> recipes;
}
