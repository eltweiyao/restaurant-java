package com.eltweiyao.restaurant.pojo;

import lombok.Data;

import java.util.List;

/**
 * @author weiyao
 * @date 2019/3/25.
 */
@Data
public class Menu {

    private String pkMenu;
    private String menuName;
    private String pkCompany;
    private Integer recipeCount;
    private String key;
    private String value;
    private List<Menu> children;
    private List<String> pkRecipes;
    private List<String> recipeNames;

}
