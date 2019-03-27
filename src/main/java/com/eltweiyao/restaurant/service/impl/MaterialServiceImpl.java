package com.eltweiyao.restaurant.service.impl;

import com.eltweiyao.restaurant.dao.MaterialMapper;
import com.eltweiyao.restaurant.pojo.Material;
import com.eltweiyao.restaurant.service.MaterialService;
import com.eltweiyao.restaurant.util.CodeHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

/**
 * @author weiyao
 * @date 2019/3/16.
 */
@Service
public class MaterialServiceImpl implements MaterialService {

    @Autowired
    private MaterialMapper materialMapper;

    /**
     * 查看物料单位是否存在
     *
     * @param unitName
     * @return
     */
    @Override
    public boolean checkMaterialUnitExist(String pkUnit, String unitName, String pkCompany) {
        if (materialMapper.checkMaterialUnitExist(pkUnit, unitName, pkCompany) > 0) {
            return true;
        }
        return false;
    }

    @Override
    public boolean checkMaterialExist(String pkMaterial, String materialName, String pkCompany) {
        if (materialMapper.checkMaterialExist(pkMaterial, materialName, pkCompany) > 0) {
            return true;
        }
        return false;
    }

    @Override
    public void updateMaterialUnit(String pkUnit, String unitName, String pkCompany) {
        materialMapper.updateMaterialUnit(pkUnit, unitName, pkCompany);
    }

    @Override
    public void saveMaterialUnit(String unitName, String pkCompany) {
        String pkUnit = CodeHelper.createUUID();
        materialMapper.saveMaterialUnit(pkUnit, unitName, pkCompany);
    }

    @Override
    public List<Material> listMaterialUnit(String unitName, String pkCompany) {
        return Optional.ofNullable(materialMapper.listMaterialUnit(unitName, pkCompany)).orElse(new ArrayList<>());
    }

    @Override
    public List<Material> listMaterial(String materialName, String pkCompany) {
        return Optional.ofNullable(materialMapper.listMaterial(materialName, pkCompany)).orElse(new ArrayList<>());
    }

    @Override
    public void updateMaterial(Material material, String pkCompany) {
        materialMapper.updateMaterial(material, pkCompany);
    }

    @Override
    public void saveMaterial(Material material, String pkCompany) {
        material.setPkMaterial(CodeHelper.createUUID());
        materialMapper.saveMaterial(material, pkCompany);
    }


}
