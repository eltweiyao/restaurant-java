package com.eltweiyao.restaurant.service;

import com.eltweiyao.restaurant.dto.Menu;
import com.eltweiyao.restaurant.dto.Recipe;
import com.eltweiyao.restaurant.dto.Store;

import java.util.List;

/**
 * @author weiyao
 * @date 2019/3/25.
 */
public interface StoreService {


    /**
     * 查询门店
     * @param pkCompany
     * @return
     */
    List<Store> listStore(String storeName, String storePoisition, String pkCompany);

    /**
     * 查看菜谱是否存在
     * @param pkStore
     * @param storeName
     * @return
     */
    boolean checkStoreExist(String pkStore, String storeName, String pkCompany);

    /**
     * 保存菜谱
     * @param store
     */
    void saveStore(Store store) throws Exception;

    /**
     * 修改菜谱
     * @param store
     */
    void updateStore(Store store) throws Exception;

    void deleteStore(String pkStore, String pkCompany);

}
