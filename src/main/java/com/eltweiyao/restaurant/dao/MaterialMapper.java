package com.eltweiyao.restaurant.dao;


import com.eltweiyao.restaurant.pojo.Material;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author weiyao
 * @date 2019/3/16.
 */
public interface MaterialMapper {

    /**
     * 插入菜品单位名称
     *
     * @param pkUnit
     * @param unitName
     */
    void saveMaterialUnit(@Param("pkUnit") String pkUnit, @Param("unitName") String unitName,
                          @Param("pkCompany") String pkCompany);

    /**
     * 保存菜品原料
     *
     * @param pkMaterial
     * @param materialName
     * @param pkUnit
     * @param materialPrice
     */
    void saveMaterial(@Param("pkMaterial") String pkMaterial, @Param("materialName") String materialName,
                      @Param("pkUnit") String pkUnit, @Param("materialPrice") Double materialPrice,
                      @Param("pkCompany") String pkCompany);

    /**
     * 查看原料单位名称是否存在
     *
     * @param unitName
     * @return
     */
    int checkMaterialUnitExist(@Param("pkUnit") String pkUnit, @Param("unitName") String unitName,
                               @Param("pkCompany") String pkCompany);

    /**
     * 查看原料名称是否存在
     *
     * @param materialName
     * @return
     */
    int checkMaterialExist(@Param("pkMaterial") String pkMaterial, @Param("materialName") String materialName,
                           @Param("pkCompany") String pkCompany);

    /**
     * 修改原料单位
     *
     * @param pkUnit
     * @param unitName
     */
    void updateMaterialUnit(@Param("pkUnit") String pkUnit, @Param("unitName") String unitName, @Param("pkCompany") String pkCompany);

    /**
     * @param unitName
     * @return
     */
    List<Material> listMaterialUnit(@Param("unitName") String unitName, @Param("pkCompany") String pkCompany);

    /**
     * 关联查询原料
     *
     * @param materialName
     * @return
     */
    List<Material> listMaterial(@Param("materialName") String materialName, @Param("pkCompany") String pkCompany);

    /**
     * 修改原料
     *
     * @param material
     */
    void updateMaterial(@Param("material") Material material, @Param("pkCompany") String pkCompany);

    /**
     * 修改原料
     *
     * @param material
     */
    void saveMaterial(@Param("material") Material material, @Param("pkCompany") String pkCompany);

}
