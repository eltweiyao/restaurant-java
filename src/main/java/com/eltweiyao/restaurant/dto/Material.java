package com.eltweiyao.restaurant.dto;

import lombok.Data;

/**
 * @author weiyao
 * @date 2019/3/16.
 */
@Data
public class Material {

    //物料主键
    private String pkMaterial;
    //物料名称
    private String materialName;
    //物料单位
    private String pkUnit;
    //物料单价
    private double materialPrice;
    //物料单位名称
    private String unitName;
    //物料数量
    private double materialCount;
}
