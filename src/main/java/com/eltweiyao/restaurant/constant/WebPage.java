package com.eltweiyao.restaurant.constant;

import java.util.Map;

/**
 * @classDesc: 功能描述:(分页相关)
 * @Author: lizhiqiang
 * @createTime: 2017-12-25 11:23
 * @version: v1.0
 * @copyright: 北京辰森
 * @email: lzq@choicesoft.com.cn
 */
public class WebPage {
    private static final String PAGENO = "pageno";
    private static final String ROWCOUNT = "rowcount";
    private static final String ORDERBY = "orderby";

    public WebPage(){

    }
    /**
     * 页码
     */
    private int pageno;
    /**
     * 每页显示行数
     */
    private int rowcount;

    private Map<String,Integer> orderby;

    /**
     * 总记录数
     */
    private int total;

    /**
     * 排序 eg. orderby={username:1,status:-1}
     */

    public WebPage(int pageno, int rowcount){
        this.pageno = pageno;
        this.rowcount = rowcount;
    }

    public WebPage(int pageno, int rowcount, int total) {
        this.pageno = pageno;
        this.rowcount = rowcount;
        this.total = total;
    }

    public int getPageno() {
        return pageno;
    }

    public void setPageno(int pageno) {
        this.pageno = pageno;
    }

    public int getRowcount() {
        return rowcount;
    }

    public void setRowcount(int rowcount) {
        this.rowcount = rowcount;
    }

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public Map<String, Integer> getOrderby() {
        return orderby;
    }

    public void setOrderby(Map<String, Integer> orderby) {
        this.orderby = orderby;
    }

    @Override
    public String toString() {
        return "WebPage{" +
                "pageno=" + pageno +
                ", rowcount=" + rowcount +
                ", orderby=" + orderby +
                ", total=" + total +
                '}';
    }
}
