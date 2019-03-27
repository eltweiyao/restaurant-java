package com.eltweiyao.restaurant.service.impl;

import com.eltweiyao.restaurant.dao.MenuMapper;
import com.eltweiyao.restaurant.pojo.Menu;
import com.eltweiyao.restaurant.pojo.Recipe;
import com.eltweiyao.restaurant.service.CategoryService;
import com.eltweiyao.restaurant.service.MenuService;
import com.eltweiyao.restaurant.util.CodeHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * @author weiyao
 * @date 2019/3/25.
 */
@Service
public class MenuServiceImpl implements MenuService {

    @Autowired
    private CategoryService categoryService;
    @Autowired
    private MenuMapper menuMapper;

    @Override
    public List<Menu> listMenuItem(String pkCompany) {
        List<Menu> menuItems = new ArrayList<>();
        List<Recipe> categories = categoryService.listCategory(null, pkCompany);
        for (Recipe category: categories){
            Menu menuItem = new Menu();
            List<Menu> recipes = menuMapper.listRecipeByCategory(category.getPkCategory(), pkCompany);
            if (recipes == null || recipes.size() == 0) {
                continue;
            }
            menuItem.setKey(category.getPkCategory());
            menuItem.setValue(category.getCategoryName());
            menuItem.setChildren(recipes);
            menuItems.add(menuItem);
        }
        return menuItems;
    }

    @Override
    public List<Menu> listMenu(String menuName, String pkCompany) {
        List<Menu> menuList = Optional.ofNullable(menuMapper.listMenu(menuName, pkCompany)).orElse(new ArrayList<>());
        for (Menu menu:menuList){
            menu.setPkRecipes(Optional.ofNullable(menuMapper.listPkRecipe(menu.getPkMenu(), pkCompany)).orElse(new ArrayList<>()));
            menu.setRecipeCount(menu.getPkRecipes().size());
            //menu.setRecipeNames(Optional.ofNullable(menuMapper.listRecipeName(menu.getPkMenu(), pkCompany)).orElse(new ArrayList<>()));
        }
        return menuList;
    }

    @Override
    public boolean checkMenuExist(String pkMenu, String menuName, String pkCompany) {
        if (menuMapper.checkMenuExist(pkMenu, menuName, pkCompany) >0){
            return true;
        }
        return false;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void saveMenu(String menuName, List<String> recipes, String pkCompany) throws Exception {
        String pkMenu = CodeHelper.createUUID();
        menuMapper.saveMenuInfo(pkMenu, menuName, pkCompany);
        menuMapper.saveMenuRecipes(pkMenu, pkCompany, recipes);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void updateMenu(String pkMenu, String menuName, List<String> recipes, String pkCompany) throws Exception {
        menuMapper.updateMenuInfo(pkMenu, menuName, pkCompany);
        menuMapper.deleteMenuRecipes(pkMenu, pkCompany);
        menuMapper.saveMenuRecipes(pkMenu, pkCompany, recipes);

    }

    @Override
    public List<Recipe> queryRecipeCount(String pkMenu, String pkCompany) {
        return Optional.ofNullable(menuMapper.queryRecipeCount(pkMenu, pkCompany)).orElse(new ArrayList<>());
    }
}
