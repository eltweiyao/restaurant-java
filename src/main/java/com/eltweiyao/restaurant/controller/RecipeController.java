package com.eltweiyao.restaurant.controller;

import com.eltweiyao.restaurant.context.RestaurantRequestContext;
import com.eltweiyao.restaurant.enums.AccountType;
import com.eltweiyao.restaurant.vo.Result;
import com.eltweiyao.restaurant.dto.WebPage;
import com.eltweiyao.restaurant.dto.Material;
import com.eltweiyao.restaurant.dto.Recipe;
import com.eltweiyao.restaurant.service.RecipeService;
import com.eltweiyao.restaurant.util.ResultUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.net.Inet4Address;
import java.net.InetAddress;
import java.net.UnknownHostException;
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

    @Value("${upload.path}")
    private String uploadPath;
    @Value("${server.port}")
    private String port;
    @Value("${host.address}")
    private String ip;


    @PostMapping("/saveRecipe")
    public Result saveRecipe(@RequestBody Recipe recipe, HttpServletRequest request) {

        try {
            if (recipeService.checkRecipeExist(recipe.getPkRecipe(), recipe.getRecipeName(), RestaurantRequestContext.getPkCompany())) {
                return ResultUtil.error("配方已存在");
            }
            recipeService.saveRecipe(recipe, RestaurantRequestContext.getPkCompany());
        } catch (Exception e) {
            logger.error("保存配方出错, errMsg = {}, stack info =", e.getMessage(), e);
            return ResultUtil.error();
        }
        return ResultUtil.success("保存成功");
    }

    @PostMapping("/listRecipe")
    public Result listRecipe(@RequestParam(value = "pageno", defaultValue = "1") Integer pageNum,
                             @RequestParam(value = "rowcount", defaultValue = "10") Integer pageSize,
                             @RequestParam(required = false) String recipeName,
                             @RequestParam(required = false) String materialName) {

        List<Recipe> recipeList = recipeService.listRecipe(recipeName, materialName, RestaurantRequestContext.getPkCompany());
        for (Recipe recipe : recipeList) {
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

    @PostMapping("/listRecipeByPkStore")
    public Result listRecipeByPkStore(@RequestParam(value = "pageno", defaultValue = "1") Integer pageNum,
                                      @RequestParam(value = "rowcount", defaultValue = "10") Integer pageSize,
                                      @RequestParam(required = false) String recipeName,
                                      @RequestParam(required = false) String materialName) {
        List<Recipe> recipeList = recipeService.listRecipe(recipeName, materialName, RestaurantRequestContext.getPkStore(), RestaurantRequestContext.getPkCompany());
        for (Recipe recipe : recipeList) {
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
    public Result addRecipeMaterial(@RequestParam("pkRecipe") String pkRecipe, @RequestBody List<Material> materials) {

        try {
            recipeService.addRecipeMaterial(pkRecipe, materials, RestaurantRequestContext.getPkCompany());
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
                                   @RequestParam(value = "imageUrl", required = false) String imageUrl,
                                   @RequestParam(value = "remark", required = false) String remark) {

        try {
            recipeService.updateRecipeInfo(pkRecipe, pkCategory, recipeName, recipePrice, RestaurantRequestContext.getPkCompany(), imageUrl, remark);
            } catch (Exception e) {
            logger.error("保存原料出错, errMsg = {}, stack info =", e.getMessage(), e);
            return ResultUtil.error();
        }
        return ResultUtil.success();
    }

    @PostMapping("/updateRecipeMaterial")
    public Result updateRecipeMaterial(@RequestParam("pkRecipe") String pkRecipe, @RequestParam("oldPkMaterial") String oldPkMaterial,
                                       @RequestParam("pkMaterial") String pkMaterial, @RequestParam("materialCount") double materialCount) {

        try {
            recipeService.updateRecipeMaterial(pkRecipe, oldPkMaterial, pkMaterial, materialCount, RestaurantRequestContext.getPkCompany());
        } catch (Exception e) {
            logger.error("保存原料出错, errMsg = {}, stack info =", e.getMessage(), e);
            return ResultUtil.error();
        }
        return ResultUtil.success();
    }

    @PostMapping("/deleteRecipeMaterial")
    public Result deleteRecipeMaterial(@RequestParam("pkRecipe") String pkRecipe,
                                       @RequestParam(value = "pkMaterial", required = false) String pkMaterial) {

        try {
            recipeService.deleteRecipeMaterial(pkRecipe, pkMaterial, RestaurantRequestContext.getPkCompany());
        } catch (Exception e) {
            logger.error("保存原料出错, errMsg = {}, stack info =", e.getMessage(), e);
            return ResultUtil.error();
        }
        return ResultUtil.success();
    }

    @PostMapping("/deleteRecipe")
    public Result deleteRecipe(@RequestParam("pkRecipe") String pkRecipe,
                               HttpServletRequest request) {
        String pkCompany = String.valueOf(request.getSession().getAttribute("pkCompany"));
        if (pkCompany == null || pkCompany.equals("") || pkCompany.equals("null")) {
            return ResultUtil.error("未获取到当前登录人权限信息");
        }
        try {
            recipeService.deleteRecipe(pkRecipe, pkCompany);
        } catch (Exception e) {
            logger.error("删除配方原料出错, errMsg = {}, stack info =", e.getMessage(), e);
            return ResultUtil.error();
        }
        return ResultUtil.success();
    }

    /**
     * @param file 上传你的文件
     * @return Map<String, Object> 图片的url
     * @throws Exception
     */
    @RequestMapping(path = "/uploadPic", method = {RequestMethod.POST})
    public Result upLoadPic(@RequestParam("pic") MultipartFile file) throws Exception {
        //检验file是否为空
        if (file != null) {
            String[] strings = {"GIF", "PNG", "JPG"};
            String fileName = file.getOriginalFilename();
            String type = fileName.indexOf(".") != -1 ? fileName.substring(fileName.lastIndexOf(".") + 1, fileName.length()) : null;
            //检验文件有没有后缀名
            if (type != null) {
                //检验图片格式
                if (strings[0].equals(type.toUpperCase()) || strings[1].equals(type.toUpperCase()) || strings[2].equals(type.toUpperCase())) {
                    // 项目在容器中实际发布运行的根路径
                    // 自定义的文件名称
                    String trueFileName = System.currentTimeMillis() + "." + type;
                    // 设置存放图片文件的路径
                    String path = uploadPath + System.getProperty("file.separator") + trueFileName;
                    // 转存文件到指定的路径
                    file.transferTo(new File(path));
                    if (ip == null || ip == "") {
                        InetAddress localHost = null;
                        try {
                            localHost = Inet4Address.getLocalHost();
                        } catch (UnknownHostException e) {
                            logger.error(e.getMessage(), e);
                        }
                        ip = localHost.getHostAddress();  // 返回格式为：xxx.xxx.xxx

                    }

                    return ResultUtil.success("http://" + ip + ":" + port + "/image/" + trueFileName);
                }
            }
        }
        return ResultUtil.error("图片不符合要求请重新上传");
    }

}
