package com.eltweiyao.restaurant.service.impl;

import com.eltweiyao.restaurant.dao.RecipeMapper;
import com.eltweiyao.restaurant.pojo.Material;
import com.eltweiyao.restaurant.pojo.Recipe;
import com.eltweiyao.restaurant.service.RecipeService;
import com.eltweiyao.restaurant.util.CodeHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

/**
 * @author weiyao
 * @date 2019/3/16.
 */
@Service
public class RecipeServiceImpl implements RecipeService {

    @Autowired
    private RecipeMapper recipeMapper;

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void saveRecipe(Recipe recipe, String pkCompany) throws Exception {
        recipe.setPkRecipe(CodeHelper.createUUID());
        recipeMapper.saveRecipeInfo(recipe, pkCompany);
        recipeMapper.saveRecipeMaterials(recipe.getPkRecipe(), recipe.getMaterials(), pkCompany);
    }

    @Override
    public List<Recipe> listRecipe(String recipeName, String materialName, String pkCompany) {

        return Optional.ofNullable(recipeMapper.listRecipe(recipeName, materialName, pkCompany)).orElse(new ArrayList<>());
    }

    @Override
    public boolean checkRecipeExist(String pkRecipe, String recipeName, String pkCompany) {
        if (recipeMapper.checkRecipeExist(pkRecipe, recipeName, pkCompany) > 0) {
            return true;
        }
        return false;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void addRecipeMaterial(String pkRecipe, List<Material> materials, String pkCompany) throws Exception {
        Iterator<Material> it = materials.iterator();
        while (it.hasNext()) {
            Material material = it.next();
            if (recipeMapper.checkRecipeMaterialExist(pkRecipe, material.getPkMaterial(), pkCompany) > 0) {
                recipeMapper.updateRecipeMaterialCount(pkRecipe, material.getPkMaterial(),
                        material.getMaterialCount(), pkCompany);
                it.remove();
            }
        }
        if (materials != null && materials.size() > 0) {
            recipeMapper.saveRecipeMaterials(pkRecipe, materials, pkCompany);
        }
    }

    @Override
    public void updateRecipeInfo(String pkRecipe, String pkCategory, String recipeName, double recipePrice, String pkCompany) {
        recipeMapper.updateRecipeInfo(pkRecipe, pkCategory, recipeName, recipePrice, pkCompany);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void updateRecipeMaterial(String pkRecipe, String oldPkMaterial, String pkMaterial,
                                     double materialCount, String pkCompany) throws Exception {
        if (!oldPkMaterial.equals(pkMaterial) && recipeMapper.checkRecipeMaterialExist(pkRecipe, pkMaterial, pkCompany) > 0) {
            recipeMapper.deleteRecipeMaterial(pkRecipe, oldPkMaterial, pkCompany);
            recipeMapper.updateRecipeMaterialCount(pkRecipe, pkMaterial, materialCount, pkCompany);
        } else {
            recipeMapper.updateRecipeMaterial(pkRecipe, oldPkMaterial, pkMaterial, materialCount, pkCompany);
        }
    }

    @Override
    public void deleteRecipeMaterial(String pkRecipe, String pkMaterial, String pkCompany) {
        recipeMapper.deleteRecipeMaterial(pkRecipe, pkMaterial, pkCompany);
    }

}