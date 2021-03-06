package com.eltweiyao.restaurant.dto;

import lombok.Data;

import java.util.List;

/**
 * @author weiyao
 * @date 2019/3/16.
 */
@Data
public class Recipe {

    private String pkRecipe;
    private String recipeName;
    private double originalPrice;
    private double recipePrice;
    private String pkCategory;
    private String categoryName;
    private String imageUrl;
    private String remark;
    private List<Material> materials;

}
