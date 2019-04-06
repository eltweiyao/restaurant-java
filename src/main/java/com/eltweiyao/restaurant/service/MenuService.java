package com.eltweiyao.restaurant.service;

import com.eltweiyao.restaurant.dto.Menu;
import com.eltweiyao.restaurant.dto.Recipe;

import java.util.List;

/**
 * @author weiyao
 * @date 2019/3/25.
 */
public interface MenuService {

    /**
     * 查询类别下的菜品
     * @param pkCompany
     * @return
     */
    List<Menu> listMenuItem(String pkCompany);

    /**
     * 查询类别下的菜品
     * @param pkCompany
     * @return
     */
    List<Menu> listMenu(String menuName, String pkCompany);

    /**
     * 查看菜谱是否存在
     * @param pkMenu
     * @param menuName
     * @return
     */
    boolean checkMenuExist(String pkMenu, String menuName, String pkCompany);

    /**
     * 保存菜谱
     * @param menuName
     * @param recipes
     * @param pkCompany
     */
    void saveMenu(String menuName, List<String> recipes, String pkCompany) throws Exception;

    /**
     * 修改菜谱
     * @param menuName
     * @param recipes
     * @param pkCompany
     */
    void updateMenu(String pkMenu, String menuName, List<String> recipes, String pkCompany) throws Exception;

    /**
     * 查询菜品名称
     * @param pkMenu
     * @param pkCompany
     * @return
     */
    List<Recipe> queryRecipeCount(String pkMenu, String pkCompany);
}
