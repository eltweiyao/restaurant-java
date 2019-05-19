package com.eltweiyao.restaurant.dao;

import com.eltweiyao.restaurant.dto.Menu;
import com.eltweiyao.restaurant.dto.Recipe;
import com.eltweiyao.restaurant.dto.Store;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author weiyao
 * @date 2019/3/25.
 */
@Repository
public interface StoreMapper {


    /**
     * 查询门店
     * @param storeName
     * @param storePosition
     * @param pkCompany
     * @return
     */
    List<Store> listStore(@Param("storeName") String storeName, @Param("storePosition") String storePosition, @Param("pkCompany") String pkCompany);

    /**
     * 查看菜谱是否存在
     *
     * @param pkStore
     * @param storeName
     * @return
     */
    int checkStoreExist(@Param("pkStore") String pkStore, @Param("storeName") String storeName, @Param("pkCompany") String pkCompany);

    /**
     * 删除菜谱菜品关联关系
     * @param pkStore
     * @param pkCompany
     */
    void deleteStore(@Param("pkStore") String pkStore, @Param("pkCompany") String pkCompany);

    /**
     * 保存门店信息
     * @param store
     */
    void saveStore(Store store);

    /**
     * 修改菜谱信息
     * @param store
     */
    void updateStore(Store store);



}
