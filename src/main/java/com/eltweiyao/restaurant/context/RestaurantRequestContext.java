package com.eltweiyao.restaurant.context;

import com.eltweiyao.restaurant.dto.Account;
import lombok.Data;

/**
 * @author weiyao
 * @date 2019/5/4.
 */
public final class RestaurantRequestContext {

    private static ThreadLocal<Account> threadLocal = new ThreadLocal<Account>();

    public static void setAccount(Account account){
        threadLocal.set(account);
    }

    public static String getPkCompany(){
        return threadLocal.get().getPkCompany();
    }

    public static String getPkStore(){
        return threadLocal.get().getPkStore();
    }

    public static Integer getAccountType(){
        return threadLocal.get().getAccountType();
    }

    public static void remove(){
        threadLocal.remove();
    }

}
