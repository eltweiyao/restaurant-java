package com.eltweiyao.restaurant.controller;

import com.eltweiyao.restaurant.vo.Result;
import com.eltweiyao.restaurant.constant.WebPage;
import com.eltweiyao.restaurant.pojo.Material;
import com.eltweiyao.restaurant.pojo.Recipe;
import com.eltweiyao.restaurant.service.RecipeService;
import com.eltweiyao.restaurant.util.ResultUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * @author weiyao
 * @date 2019/3/16.
 */
@RestController
@RequestMapping("api/recipe")
public class RecipeController {

    private Logger logger = LoggerFactory.getLogger(RecipeController.class);

    @Autowired
    private RecipeService recipeService;

    @PostMapping("/saveRecipe")
    public Result saveRecipe(@RequestBody Recipe recipe, HttpServletRequest request) {
        String pkCompany = String.valueOf(request.getSession().getAttribute("pkCompany"));
        if (pkCompany == null || pkCompany.equals("") || pkCompany.equals("null")) {
            return ResultUtil.error("未获取到当前登录人权限信息");
        }
        try {
            if (recipeService.checkRecipeExist(recipe.getPkRecipe(), recipe.getRecipeName(), pkCompany)) {
                return ResultUtil.error("配方已存在");
            }
            recipeService.saveRecipe(recipe, pkCompany);
        } catch (Exception e) {
            logger.error("保存配方出错, errMsg = {}, stack info =", e.getMessage(), e);
            return ResultUtil.error();
        }
        return ResultUtil.success("保存成功");
    }

    @PostMapping("/listRecipe")
    public Result listRecipes(@RequestParam(value = "pageno", defaultValue = "1") Integer pageNum,
                              @RequestParam(value = "rowcount", defaultValue = "10") Integer pageSize,
                              @RequestParam(required = false) String recipeName,
                              @RequestParam(required = false) String materialName,
                              HttpServletRequest request) {
        String pkCompany = String.valueOf(request.getSession().getAttribute("pkCompany"));
        if (pkCompany == null || pkCompany.equals("") || pkCompany.equals("null")) {
            return ResultUtil.error("未获取到当前登录人权限信息");
        }
        List<Recipe> recipeList = recipeService.listRecipe(recipeName, materialName, pkCompany);
        for (Recipe recipe:recipeList){
            double price = 0d;
            List<Material> materials = recipe.getMaterials();
            if (materials != null && materials.size() > 0) {
                for (Material material : materials) {
                    price += material.getMaterialPrice() * material.getMaterialCount();
                }
            }
            recipe.setOriginalPrice(price);
        }
        WebPage webPage = new WebPage(pageNum, pageSize, recipeList.size());
        return ResultUtil.successPage(recipeList, webPage);
    }

    @PostMapping("/addRecipeMaterial")
    public Result addRecipeMaterial(@RequestParam("pkRecipe") String pkRecipe,
                                    @RequestBody List<Material> materials,
                                    HttpServletRequest request) {
        String pkCompany = String.valueOf(request.getSession().getAttribute("pkCompany"));
        if (pkCompany == null || pkCompany.equals("") || pkCompany.equals("null")) {
            return ResultUtil.error("未获取到当前登录人权限信息");
        }
        try {
            recipeService.addRecipeMaterial(pkRecipe, materials, pkCompany);
        } catch (Exception e) {
            logger.error("保存原料出错, errMsg = {}, stack info =", e.getMessage(), e);
            return ResultUtil.error();
        }
        return ResultUtil.success();
    }

    @PostMapping("/updateRecipeInfo")
    public Result updateRecipeInfo(@RequestParam("pkRecipe") String pkRecipe,
                                   @RequestParam("recipeName") String recipeName,
                                   @RequestParam("pkCategory") String pkCategory,
                                   @RequestParam("recipePrice") double recipePrice,
                                   HttpServletRequest request) {
        String pkCompany = String.valueOf(request.getSession().getAttribute("pkCompany"));
        if (pkCompany == null || pkCompany.equals("") || pkCompany.equals("null")) {
            return ResultUtil.error("未获取到当前登录人权限信息");
        }
        try {
            recipeService.updateRecipeInfo(pkRecipe, pkCategory, recipeName, recipePrice, pkCompany);
        } catch (Exception e) {
            logger.error("保存原料出错, errMsg = {}, stack info =", e.getMessage(), e);
            return ResultUtil.error();
        }
        return ResultUtil.success();
    }

    @PostMapping("/updateRecipeMaterial")
    public Result updateRecipeMaterial(@RequestParam("pkRecipe") String pkRecipe, @RequestParam("oldPkMaterial") String oldPkMaterial,
                                       @RequestParam("pkMaterial") String pkMaterial, @RequestParam("materialCount") double materialCount,
                                       HttpServletRequest request) {
        String pkCompany = String.valueOf(request.getSession().getAttribute("pkCompany"));
        if (pkCompany == null || pkCompany.equals("") || pkCompany.equals("null")) {
            return ResultUtil.error("未获取到当前登录人权限信息");
        }
        try {
            recipeService.updateRecipeMaterial(pkRecipe, oldPkMaterial, pkMaterial, materialCount, pkCompany);
        } catch (Exception e) {
            logger.error("保存原料出错, errMsg = {}, stack info =", e.getMessage(), e);
            return ResultUtil.error();
        }
        return ResultUtil.success();
    }

    @PostMapping("/deleteRecipeMaterial")
    public Result deleteRecipeMaterial(@RequestParam("pkRecipe") String pkRecipe,
                                       @RequestParam(value = "pkMaterial", required = false) String pkMaterial,
                                       HttpServletRequest request) {
        String pkCompany = String.valueOf(request.getSession().getAttribute("pkCompany"));
        if (pkCompany == null || pkCompany.equals("") || pkCompany.equals("null")) {
            return ResultUtil.error("未获取到当前登录人权限信息");
        }
        try {
            recipeService.deleteRecipeMaterial(pkRecipe, pkMaterial, pkCompany);
        } catch (Exception e) {
            logger.error("保存原料出错, errMsg = {}, stack info =", e.getMessage(), e);
            return ResultUtil.error();
        }
        return ResultUtil.success();
    }




}
