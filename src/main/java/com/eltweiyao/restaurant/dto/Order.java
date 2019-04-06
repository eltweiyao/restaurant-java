package com.eltweiyao.restaurant.dto;

import lombok.Data;

import java.sql.Date;
import java.sql.Time;

/**
 * @author weiyao
 * @date 2019/4/6.
 */
@Data
public class Order {

    private String pkStore;
    private String pkCompany;
    private String pkRecipe;
    private double recipePrice;
    private String recipeCount;
    private Time createTime;

}
