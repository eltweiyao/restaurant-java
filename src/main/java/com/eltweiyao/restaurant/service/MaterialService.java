package com.eltweiyao.restaurant.service;

import com.eltweiyao.restaurant.pojo.Material;

import java.util.List;

/**
 * @author weiyao
 * @date 2019/3/16.
 */
public interface MaterialService {

    /**
     * 保存原料名称
     *
     * @param unitName
     */
    void saveMaterialUnit(String unitName, String pkCompany);

    /**
     * 查看原料单位是否存在
     *
     * @param unitName
     * @return
     */
    boolean checkMaterialUnitExist(String pkUnit, String unitName, String pkCompany);

    /**
     * 查看原料是否存在
     *
     * @param unitName
     * @return
     */
    boolean checkMaterialExist(String pkUnit, String unitName, String pkCompany);

    /**
     * 修改原料单位
     *
     * @param pkUnit
     * @param unitName
     */
    void updateMaterialUnit(String pkUnit, String unitName, String pkCompany);

    /**
     * 根据单位名称查询
     *
     * @param unitName
     */
    List<Material> listMaterialUnit(String unitName, String pkCompany);

    /**
     * 根据原料名称查询
     *
     * @param materialName
     */
    List<Material> listMaterial(String materialName, String pkCompany);

    /**
     * 修改原料
     *
     * @param material
     */
    void updateMaterial(Material material, String pkCompany);

    /**
     * 添加原料
     *
     * @param material
     */
    void saveMaterial(Material material, String pkCompany);
}
