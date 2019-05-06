package com.eltweiyao.restaurant.controller;

import com.eltweiyao.restaurant.context.RestaurantRequestContext;
import com.eltweiyao.restaurant.vo.Result;
import com.eltweiyao.restaurant.dto.WebPage;
import com.eltweiyao.restaurant.dto.Recipe;
import com.eltweiyao.restaurant.service.CategoryService;
import com.eltweiyao.restaurant.util.ResultUtil;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * @author weiyao
 * @date 2019/3/24.
 */
@RestController
@RequestMapping("/api/category")
public class CategoryController {

    private Logger logger = LoggerFactory.getLogger(CategoryController.class);

    @Autowired
    private CategoryService categoryService;

    @PostMapping("/saveCategory")
    public Result saveCategory(@RequestParam String categoryName) {

        if (categoryService.checkCategoryExist(null, categoryName, RestaurantRequestContext.getPkCompany())) {
            return ResultUtil.error("类别已存在");
        }
        try {
            categoryService.saveCategory(categoryName, RestaurantRequestContext.getPkCompany());
        } catch (Exception e) {
            logger.error("保存类别出错, errMsg = {} , stack info =", e.getMessage(), e);
            return ResultUtil.error();
        }
        return ResultUtil.success();
    }

    @PostMapping("/updateCategory")
    public Result updateCategory(@RequestParam String pkCategory, @RequestParam String categoryName) {

        if (categoryService.checkCategoryExist(pkCategory, categoryName, RestaurantRequestContext.getPkCompany())) {
            return ResultUtil.error("类别已存在");
        }
        try {
            categoryService.updateCategory(pkCategory, categoryName, RestaurantRequestContext.getPkCompany());
        } catch (Exception e) {
            logger.error("修改类别出错, errMsg = {}, stack info =", e.getMessage(), e);
            return ResultUtil.error("修改失败");
        }
        return ResultUtil.success("修改成功");
    }
    @PostMapping("/listCategory")
    public Result listCategory(@RequestParam(value = "pageno", defaultValue = "1") Integer pageNum,
                                   @RequestParam(value = "rowcount", defaultValue = "10") Integer pageSize,
                                   @RequestParam(required = false) String categoryName) {

        PageHelper.startPage(pageNum, pageSize);
        List<Recipe> categories = categoryService.listCategory(categoryName, RestaurantRequestContext.getPkCompany());
        PageInfo pageInfo = new PageInfo(categories);
        WebPage webPage = new WebPage(pageNum, pageSize, (int) pageInfo.getTotal());
        return ResultUtil.successPage(categories, webPage);
    }

    /**
     * 获取所有单位信息 不分页
     *
     * @return
     */
    @PostMapping("/listCategoryAll")
    public Result listMaterialUnit() {

        return ResultUtil.success(categoryService.listCategory(null, RestaurantRequestContext.getPkCompany()));
    }

}
