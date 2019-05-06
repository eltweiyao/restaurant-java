package com.eltweiyao.restaurant.inteceptor;

import ch.qos.logback.core.util.ContentTypeUtil;
import com.eltweiyao.restaurant.context.RestaurantRequestContext;
import com.eltweiyao.restaurant.dto.Account;
import com.eltweiyao.restaurant.util.ResultUtil;
import com.sun.xml.internal.ws.encoding.ContentType;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.CharSet;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;
import sun.misc.CharacterEncoder;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author weiyao
 * @date 2019/5/4.
 */
@Slf4j
public class RestaurantInteceptor implements HandlerInterceptor {

    private Logger logger = LoggerFactory.getLogger(RestaurantInteceptor.class);

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
            throws Exception{
        String pkCompany = String.valueOf(request.getSession().getAttribute("pkCompany"));
        Integer accountType = (Integer)request.getSession().getAttribute("accountType");
        String pkStore = String.valueOf(request.getSession().getAttribute("pkStore"));
        if (StringUtils.isBlank(pkCompany) || accountType == null || ( accountType == 2 && StringUtils.isBlank(pkStore))) {
            response.sendError(302, "未获取到当前登录人权限信息");
        }
        log.info("商户号{}，门店{}， 账户类型{}", pkCompany, pkStore, accountType);
        Account account = new Account();
        account.setPkCompany(pkCompany);
        account.setPkStore(pkStore);
        account.setAccountType(accountType);
        RestaurantRequestContext.setAccount(account);
        return true;//返回true往下执行
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
                           ModelAndView modelAndView) throws Exception{
    }

    @Override
    public void afterCompletion(HttpServletRequest request,HttpServletResponse response,Object handler,
                                Exception ex) throws Exception{

        RestaurantRequestContext.remove();
    }
}
