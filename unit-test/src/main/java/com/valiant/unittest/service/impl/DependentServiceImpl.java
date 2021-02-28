package com.valiant.unittest.service.impl;

import com.valiant.unittest.service.DependentService;

/**
 * @author : yuanqi
 * @since : 2020/6/4
 */
public class DependentServiceImpl implements DependentService {
    @Override
    public String getValues() {
        System.out.println("I was invoked");
        return "haha";
    }

    @Override
    public RequestUser getRequestUser() {
        return new RequestUser();
    }
}
