package com.eltweiyao.restaurant.service;

import com.eltweiyao.restaurant.pojo.Material;
import com.eltweiyao.restaurant.pojo.Recipe;

import java.util.List;

/**
 * @author weiyao
 * @date 2019/3/20.
 */
public interface RecipeService {

    /**
     * 保存配方
     * @param recipe
     */
    void saveRecipe(Recipe recipe, String pkCompany) throws Exception;

    /**
     * 查询所有原料
     * @param recipeName
     * @param materialName
     * @return
     */
    List<Recipe> listRecipe(String recipeName, String materialName, String pkCompany);

    /**
     * 查看配方是否存在
     * @param pkRecipe
     * @param recipeName
     * @return
     */
    boolean checkRecipeExist(String pkRecipe, String recipeName, String pkCompany);

    /**
     * 新增物料
     * @param pkRecipe
     * @param materials
     */
    void addRecipeMaterial(String pkRecipe, List<Material> materials, String pkCompany) throws Exception;

    /**
     * 修改配方信息
     * @param pkRecipe
     * @param recipeName
     */
    void updateRecipeInfo(String pkRecipe, String pkCategory, String recipeName, double recipePrice, String pkCompany);

    /**
     * 修改物料信息
     * @param pkRecipe
     * @param oldPkMaterial
     * @param pkMaterial
     */
    void updateRecipeMaterial(String pkRecipe, String oldPkMaterial, String pkMaterial, double materialCount, String pkCompany) throws Exception;

    /**
     * 删除配方物料
     * @param pkRecipe
     * @param pkMaterial
     */
    void deleteRecipeMaterial(String pkRecipe, String pkMaterial, String pkCompany);
}
