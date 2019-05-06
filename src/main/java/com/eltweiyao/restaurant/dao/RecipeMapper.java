package com.eltweiyao.restaurant.dao;

import com.eltweiyao.restaurant.dto.Material;
import com.eltweiyao.restaurant.dto.Recipe;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author weiyao
 * @date 2019/3/20.
 */
public interface RecipeMapper {

    /**
     * 保存配方信息
     *
     * @param recipe
     */
    void saveRecipeInfo(@Param("recipe") Recipe recipe, @Param("pkCompany") String pkCompany);

    /**
     * 保存配方原料信息
     *
     * @param materials
     */
    void saveRecipeMaterials(@Param("pkRecipe") String pkRecipe, @Param("materials") List<Material> materials, @Param("pkCompany") String pkCompany);

    /**
     * 查看物料是否存在
     *
     * @param pkRecipe
     * @param pkMaterial
     */
    int checkRecipeMaterialExist(@Param("pkRecipe") String pkRecipe, @Param("pkMaterial") String pkMaterial, @Param("pkCompany") String pkCompany);

    /**
     * 查询配方
     *
     * @param recipeName
     * @param materialName
     * @return
     */
    List<Recipe> listRecipe(@Param("recipeName") String recipeName, @Param("materialName") String materialName, @Param("pkStore") String pkStore, @Param("pkCompany") String pkCompany);

    /**
     * 查看配方是否存在
     *
     * @param pkRecipe
     * @param recipeName
     * @return
     */
    int checkRecipeExist(@Param("pkRecipe") String pkRecipe, @Param("recipeName") String recipeName, @Param("pkCompany") String pkCompany);

    /**
     * 修改配方信息
     *
     * @param pkRecipe
     * @param recipeName
     */
    void updateRecipeInfo(@Param("pkRecipe") String pkRecipe, @Param("pkCategory") String pkCategory, @Param("recipeName") String recipeName,
                          @Param("recipePrice") double recipePrice, @Param("pkCompany") String pkCompany, @Param("imageUrl") String imageUrl);

    /**
     * 修改物料
     *
     * @param pkRecipe
     * @param oldPkMaterial
     * @param pkMaterial
     */
    void updateRecipeMaterial(@Param("pkRecipe") String pkRecipe, @Param("oldPkMaterial") String oldPkMaterial,
                              @Param("pkMaterial") String pkMaterial, @Param("materialCount") double materialCount, @Param("pkCompany") String pkCompany);

    /**
     * 修改物料数量
     *
     * @param pkRecipe
     * @param pkMaterial
     * @param materialCount
     */
    void updateRecipeMaterialCount(@Param("pkRecipe") String pkRecipe, @Param("pkMaterial") String pkMaterial,
                                   @Param("materialCount") double materialCount, @Param("pkCompany") String pkCompany);

    /**
     * 删除物料
     *
     * @param pkRecipe
     * @param pkMaterial
     */
    void deleteRecipeMaterial(@Param("pkRecipe") String pkRecipe, @Param("pkMaterial") String pkMaterial, @Param("pkCompany") String pkCompany);

    void deleteRecipe(@Param("pkRecipe") String pkRecipe, @Param("pkCompany") String pkCompany);

    List<Material> listRecipeMaterial(@Param("pkRecipe") String pkRecipe, @Param("pkCompany") String company);
}
