package com.eltweiyao.restaurant.controller;

import com.eltweiyao.restaurant.vo.Result;
import com.eltweiyao.restaurant.constant.WebPage;
import com.eltweiyao.restaurant.pojo.Menu;
import com.eltweiyao.restaurant.service.MenuService;
import com.eltweiyao.restaurant.util.ResultUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * @author weiyao
 * @date 2019/3/25.
 */
@RestController
@RequestMapping("/api/dishMenu")
public class MenuController {

    private Logger logger = LoggerFactory.getLogger(MenuController.class);

    @Autowired
    private MenuService menuService;

    @PostMapping("/listMenuItem")
    public Result listMenuItem(HttpServletRequest request){
        String pkCompany = String.valueOf(request.getSession().getAttribute("pkCompany"));
        if (pkCompany == null || pkCompany.equals("") || pkCompany.equals("null")) {
            return ResultUtil.error("未获取到当前登录人权限信息");
        }
        return ResultUtil.success(menuService.listMenuItem(pkCompany));
    }

    @PostMapping("listMenu")
    public Result listMenu(@RequestParam(value = "pageno", defaultValue = "1") Integer pageNum,
                           @RequestParam(value = "rowcount", defaultValue = "10") Integer pageSize,
                           @RequestParam(required = false) String menuName,
                           HttpServletRequest request){
        String pkCompany = String.valueOf(request.getSession().getAttribute("pkCompany"));
        if (pkCompany == null || pkCompany.equals("") || pkCompany.equals("null")) {
            return ResultUtil.error("未获取到当前登录人权限信息");
        }
        List<Menu> menuList = menuService.listMenu(menuName, pkCompany);
        WebPage webPage = new WebPage(pageNum, pageSize, menuList.size());
        return ResultUtil.successPage(menuList, webPage);
    }

    @PostMapping("/saveMenu")
    public Result saveRecipe(@RequestBody Menu menu, HttpServletRequest request) {
        String pkCompany = String.valueOf(request.getSession().getAttribute("pkCompany"));
        if (pkCompany == null || pkCompany.equals("") || pkCompany.equals("null")) {
            return ResultUtil.error("未获取到当前登录人权限信息");
        }
        try {
            if (menuService.checkMenuExist(menu.getPkMenu(), menu.getMenuName(), pkCompany)) {
                return ResultUtil.error("菜谱已存在");
            }
            if (menu.getPkRecipes() == null && menu.getPkRecipes().size() == 0){
                return ResultUtil.error("请选择菜品");
            }
            menuService.saveMenu(menu.getMenuName(), menu.getPkRecipes(), pkCompany);
        } catch (Exception e) {
            logger.error("保存菜谱出错, errMsg = {}, stack info =", e.getMessage(), e);
            return ResultUtil.error();
        }
        return ResultUtil.success("保存成功");
    }

    @PostMapping("/updateMenu")
    public Result updateMenu(@RequestBody Menu menu, HttpServletRequest request) {
        String pkCompany = String.valueOf(request.getSession().getAttribute("pkCompany"));
        if (pkCompany == null || pkCompany.equals("") || pkCompany.equals("null")) {
            return ResultUtil.error("未获取到当前登录人权限信息");
        }
        try {
            if (menuService.checkMenuExist(menu.getPkMenu(), menu.getMenuName(), pkCompany)) {
                return ResultUtil.error("菜谱已存在");
            }
            if (menu.getPkRecipes() == null && menu.getPkRecipes().size() == 0){
                return ResultUtil.error("请选择菜品");
            }
            menuService.updateMenu(menu.getPkMenu(), menu.getMenuName(), menu.getPkRecipes(), pkCompany);
        } catch (Exception e) {
            logger.error("保存菜谱出错, errMsg = {}, stack info =", e.getMessage(), e);
            return ResultUtil.error();
        }
        return ResultUtil.success("保存成功");
    }

    @PostMapping("/queryRecipeCount")
    public Result queryRecipeCount(@RequestParam String pkMenu, HttpServletRequest request) {
        String pkCompany = String.valueOf(request.getSession().getAttribute("pkCompany"));
        if (pkCompany == null || pkCompany.equals("") || pkCompany.equals("null")) {
            return ResultUtil.error("未获取到当前登录人权限信息");
        }
        try {
            return ResultUtil.success(menuService.queryRecipeCount(pkMenu, pkCompany));
        } catch (Exception e) {
            logger.error("保存菜谱出错, errMsg = {}, stack info =", e.getMessage(), e);
            return ResultUtil.error();
        }
    }
}
