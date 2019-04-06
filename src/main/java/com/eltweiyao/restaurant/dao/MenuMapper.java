package com.eltweiyao.restaurant.dao;

import com.eltweiyao.restaurant.dto.Menu;
import com.eltweiyao.restaurant.dto.Recipe;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author weiyao
 * @date 2019/3/25.
 */
public interface MenuMapper {

    /**
     * 查询展示类别的菜品
     *
     * @param pkCategory
     * @param pkCompany
     * @return
     */
    List<Menu> listRecipeByCategory(@Param("pkCategory") String pkCategory, @Param("pkCompany") String pkCompany);

    /**
     * 查询菜谱
     *
     * @param menuName
     * @param pkCompany
     * @return
     */
    List<Menu> listMenu(@Param("menuName") String menuName, @Param("pkCompany") String pkCompany);

    /**
     * 查看菜谱是否存在
     *
     * @param pkMenu
     * @param menuName
     * @return
     */
    int checkMenuExist(@Param("pkMenu") String pkMenu, @Param("menuName") String menuName, @Param("pkCompany") String pkCompany);

    List<String> listPkRecipe(@Param("pkMenu") String pkMenu, @Param("pkCompany") String pkCompany);

    /**
     * 保存关联关系
     * @param pkMenu
     * @param pkCompany
     * @param recipes
     */
    void saveMenuRecipes(@Param("pkMenu") String pkMenu, @Param("pkCompany") String pkCompany, @Param("recipes") List<String> recipes);

    /**
     * 删除菜谱菜品关联关系
     * @param pkMenu
     * @param pkCompany
     */
    void deleteMenuRecipes(@Param("pkMenu") String pkMenu, @Param("pkCompany") String pkCompany);

    /**
     * 保存菜谱信息
     * @param pkMenu
     * @param menuName
     * @param pkCompany
     */
    void saveMenuInfo(@Param("pkMenu") String pkMenu, @Param("menuName") String menuName, @Param("pkCompany") String pkCompany);

    /**
     * 修改菜谱信息
     * @param pkMenu
     * @param menuName
     * @param pkCompany
     */
    void updateMenuInfo(@Param("pkMenu") String pkMenu, @Param("menuName") String menuName, @Param("pkCompany") String pkCompany);

    /**
     * 查询菜谱菜品信息
     * @param pkMenu
     * @param pkCompany
     * @return
     */
    List<Recipe> queryRecipeCount(@Param("pkMenu") String pkMenu, @Param("pkCompany") String pkCompany);

}
