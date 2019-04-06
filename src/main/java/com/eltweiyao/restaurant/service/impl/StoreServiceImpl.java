package com.eltweiyao.restaurant.service.impl;

import com.eltweiyao.restaurant.dao.AuthMapper;
import com.eltweiyao.restaurant.dao.MenuMapper;
import com.eltweiyao.restaurant.dao.StoreMapper;
import com.eltweiyao.restaurant.dto.Menu;
import com.eltweiyao.restaurant.dto.Recipe;
import com.eltweiyao.restaurant.dto.Store;
import com.eltweiyao.restaurant.service.CategoryService;
import com.eltweiyao.restaurant.service.MenuService;
import com.eltweiyao.restaurant.service.StoreService;
import com.eltweiyao.restaurant.util.CodeHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * @author weiyao
 * @date 2019/3/25.
 */
@Service
public class StoreServiceImpl implements StoreService {

    @Autowired
    private StoreMapper storeMapper;
    @Autowired
    private AuthMapper authMapper;


    @Override
    public List<Store> listStore(String storeName, String storePoisition,  String pkCompany) {
        List<Store> storeList = Optional.ofNullable(storeMapper.listStore(storeName, storePoisition, pkCompany)).orElse(new ArrayList<>());
        return storeList;
    }

    @Override
    public boolean checkStoreExist(String pkStore, String storeName, String pkCompany) {
        if (storeMapper.checkStoreExist(pkStore, storeName, pkCompany) >0){
            return true;
        }
        return false;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void saveStore(Store store) throws Exception {
        String pkStore = CodeHelper.createUUID();
        store.setPkStore(pkStore);
        storeMapper.saveStore(store);
        authMapper.saveStoreAccount(store);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void updateStore(Store store) throws Exception {
        storeMapper.updateStore(store);
        authMapper.updateStoreAccount(store);
    }

    @Override
    public void deleteStore(String pkStore, String pkCompany) {
        storeMapper.deleteStore(pkStore, pkCompany);
    }

}
