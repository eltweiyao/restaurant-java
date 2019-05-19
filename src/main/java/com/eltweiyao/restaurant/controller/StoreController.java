package com.eltweiyao.restaurant.controller;

import com.eltweiyao.restaurant.context.RestaurantRequestContext;
import com.eltweiyao.restaurant.dto.WebPage;
import com.eltweiyao.restaurant.dto.Menu;
import com.eltweiyao.restaurant.dto.Store;
import com.eltweiyao.restaurant.service.MenuService;
import com.eltweiyao.restaurant.service.StoreService;
import com.eltweiyao.restaurant.util.ResultUtil;
import com.eltweiyao.restaurant.vo.Result;
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
@RequestMapping("/api/store")
public class StoreController {

    private Logger logger = LoggerFactory.getLogger(StoreController.class);

    @Autowired
    private StoreService storeService;
    @Autowired
    private MenuService menuService;

    @PostMapping("listStore")
    public Result listStore(@RequestParam(value = "pageno", defaultValue = "1") Integer pageNum,
                           @RequestParam(value = "rowcount", defaultValue = "10") Integer pageSize,
                           @RequestParam(required = false) String storeName,
                           @RequestParam(required = false) String storePosition){

        List<Store> storeList = storeService.listStore(storeName, storePosition, RestaurantRequestContext.getPkCompany());
        WebPage webPage = new WebPage(pageNum, pageSize, storeList.size());
        return ResultUtil.successPage(storeList, webPage);
    }

    @PostMapping("listMenu")
    public Result listMenu(){

        List<Menu> menuList = menuService.listMenu(null, RestaurantRequestContext.getPkCompany());
        return ResultUtil.success(menuList);
    }

    @PostMapping("/saveStore")
    public Result saveStore(@RequestBody Store store) {

        try {
            if (storeService.checkStoreExist(store.getPkStore(), store.getStoreName(), RestaurantRequestContext.getPkCompany())) {
                return ResultUtil.error("门店已存在");
            }
            if (storeService.checkoutAccountExist(store.getPkStore(), store.getAccount(), RestaurantRequestContext.getPkCompany())){
                return ResultUtil.error("账户已存在");
            }
            store.setPkCompany(RestaurantRequestContext.getPkCompany());
            storeService.saveStore(store);
        } catch (Exception e) {
            logger.error("保存菜谱出错, errMsg = {}, stack info =", e.getMessage(), e);
            return ResultUtil.error();
        }
        return ResultUtil.success("保存成功");
    }

    @PostMapping("/updateStore")
    public Result updateStore(@RequestBody Store store) {

        try {
            if (storeService.checkStoreExist(store.getPkStore(), store.getStoreName(), RestaurantRequestContext.getPkCompany())) {
                return ResultUtil.error("门店已存在");
            }
            if (storeService.checkoutAccountExist(store.getPkStore(), store.getAccount(), RestaurantRequestContext.getPkCompany())){
                return ResultUtil.error("账户已存在");
            }
            store.setPkCompany(RestaurantRequestContext.getPkCompany());
            storeService.updateStore(store);
        } catch (Exception e) {
            logger.error("保存菜谱出错, errMsg = {}, stack info =", e.getMessage(), e);
            return ResultUtil.error();
        }
        return ResultUtil.success("保存成功");
    }

    @PostMapping("/deleteStore")
    public Result deleteStore(@RequestParam String pkStore) {

        try {
            storeService.deleteStore(pkStore, RestaurantRequestContext.getPkCompany());
            return ResultUtil.success();
        } catch (Exception e) {
            logger.error("保存菜谱出错, errMsg = {}, stack info =", e.getMessage(), e);
            return ResultUtil.error();
        }
    }
}
