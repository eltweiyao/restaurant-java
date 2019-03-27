package com.eltweiyao.restaurant.controller;

import com.eltweiyao.restaurant.constant.Result;
import com.eltweiyao.restaurant.constant.WebPage;
import com.eltweiyao.restaurant.pojo.Material;
import com.eltweiyao.restaurant.service.MaterialService;
import com.eltweiyao.restaurant.util.ResultUtil;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
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
@RequestMapping("/api/material")
public class MaterialController {

    private final Logger logger = LoggerFactory.getLogger(MaterialController.class);
    @Autowired
    private MaterialService materialService;

    @PostMapping("/saveMaterialUnit")
    public Result saveMaterialUnit(@RequestParam String unitName,
                                   HttpServletRequest request) {
        String pkCompany = String.valueOf(request.getSession().getAttribute("pkCompany"));
        if (pkCompany == null || pkCompany.equals("") || pkCompany.equals("null")) {
            return ResultUtil.error("未获取到当前登录人权限信息");
        }

        if (materialService.checkMaterialUnitExist(null, unitName, pkCompany)) {
            return ResultUtil.error("单位名称已存在");
        }
        try {
            materialService.saveMaterialUnit(unitName, pkCompany);
        } catch (Exception e) {
            logger.error("保存原料单位出错, errMsg = {} , stack info =", e.getMessage(), e);
            return ResultUtil.error();
        }
        return ResultUtil.success();
    }

    @PostMapping("/updateMaterialUnit")
    public Result updateMaterialUnit(@RequestParam String pkUnit, @RequestParam String unitName,
                                     HttpServletRequest request) {
        String pkCompany = String.valueOf(request.getSession().getAttribute("pkCompany"));
        if (pkCompany == null || pkCompany.equals("") || pkCompany.equals("null")) {
            return ResultUtil.error("未获取到当前登录人权限信息");
        }
        if (materialService.checkMaterialUnitExist(pkUnit, unitName, pkCompany)) {
            return ResultUtil.error("单位名称已存在");
        }
        try {
            materialService.updateMaterialUnit(pkUnit, unitName, pkCompany);
        } catch (Exception e) {
            logger.error("修改原料单位出错, errMsg = {}, stack info =", e.getMessage(), e);
            return ResultUtil.error();
        }
        return ResultUtil.success();
    }

    @PostMapping("/listMaterialUnit")
    public Result listMaterialUnit(@RequestParam(value = "pageno", defaultValue = "1") Integer pageNum,
                                   @RequestParam(value = "rowcount", defaultValue = "10") Integer pageSize,
                                   @RequestParam(required = false) String unitName,
                                   HttpServletRequest request) {
        String pkCompany = String.valueOf(request.getSession().getAttribute("pkCompany"));
        if (pkCompany == null || pkCompany.equals("") || pkCompany.equals("null")) {
            return ResultUtil.error("未获取到当前登录人权限信息");
        }
        PageHelper.startPage(pageNum, pageSize);
        List<Material> materials = materialService.listMaterialUnit(unitName, pkCompany);
        PageInfo pageInfo = new PageInfo(materials);
        WebPage webPage = new WebPage(pageNum, pageSize, (int) pageInfo.getTotal());
        return ResultUtil.successPage(materials, webPage);
    }

    /**
     * 获取所有单位信息 不分页
     *
     * @return
     */
    @PostMapping("/listMaterialUnitAll")
    public Result listMaterialUnit(
            HttpServletRequest request) {
        String pkCompany = String.valueOf(request.getSession().getAttribute("pkCompany"));
        if (pkCompany == null || pkCompany.equals("") || pkCompany.equals("null")) {
            return ResultUtil.error("未获取到当前登录人权限信息");
        }
        return ResultUtil.success(materialService.listMaterialUnit(null, pkCompany));
    }

    @PostMapping("/listMaterial")
    public Result listMaterial(@RequestParam(value = "pageno", defaultValue = "1") Integer pageNum,
                               @RequestParam(value = "rowcount", defaultValue = "10") Integer pageSize,
                               @RequestParam(required = false) String materialName,
                               HttpServletRequest request) {
        String pkCompany = String.valueOf(request.getSession().getAttribute("pkCompany"));
        if (pkCompany == null || pkCompany.equals("") || pkCompany.equals("null")) {
            return ResultUtil.error("未获取到当前登录人权限信息");
        }
        PageHelper.startPage(pageNum, pageSize);
        List<Material> materials = materialService.listMaterial(materialName, pkCompany);
        PageInfo pageInfo = new PageInfo(materials);
        WebPage webPage = new WebPage(pageNum, pageSize, (int) pageInfo.getTotal());
        return ResultUtil.successPage(materials, webPage);
    }

    @PostMapping("/listMaterialAll")
    public Result listMaterialAll(
            HttpServletRequest request) {
        String pkCompany = String.valueOf(request.getSession().getAttribute("pkCompany"));
        if (pkCompany == null || pkCompany.equals("") || pkCompany.equals("null")) {
            return ResultUtil.error("未获取到当前登录人权限信息");
        }
        return ResultUtil.success(materialService.listMaterial(null, pkCompany));
    }

    @PostMapping("/saveMaterial")
    public Result saveMaterial(@RequestBody Material material,
                               HttpServletRequest request) {
        String pkCompany = String.valueOf(request.getSession().getAttribute("pkCompany"));
        if (pkCompany == null || pkCompany.equals("") || pkCompany.equals("null")) {
            return ResultUtil.error("未获取到当前登录人权限信息");
        }
        if (materialService.checkMaterialExist(material.getPkMaterial(), material.getMaterialName(), pkCompany)) {
            return ResultUtil.error("单位名称已存在");
        }
        try {
            materialService.saveMaterial(material, pkCompany);
        } catch (Exception e) {
            logger.error("保存原料出错, errMsg = {}, stack info =", e.getMessage(), e);
            return ResultUtil.error();
        }
        return ResultUtil.success();
    }

    @PostMapping("/updateMaterial")
    public Result updateMaterial(@RequestBody Material material,
                                 HttpServletRequest request) {
        String pkCompany = String.valueOf(request.getSession().getAttribute("pkCompany"));
        if (pkCompany == null || pkCompany.equals("") || pkCompany.equals("null")) {
            return ResultUtil.error("未获取到当前登录人权限信息");
        }
        try {
            materialService.updateMaterial(material, pkCompany);
        } catch (Exception e) {
            logger.error("修改原料出错, errMsg = {}, stack info =", e.getMessage(), e);
            return ResultUtil.error();
        }
        return ResultUtil.success();
    }
}
