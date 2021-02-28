package com.valiant.unittest.service.impl;

import com.valiant.unittest.service.DependentService;
import com.valiant.unittest.service.Service;
import com.valiant.unittest.service.ServiceWithDependency;

/**
 * @author : yuanqi
 * @since : 2020/5/21
 */
public class ServiceWithDependencyImpl3 implements ServiceWithDependency {

    private DependentService dependentService;

    private DependentService dependentServiceOther;
    private String name;
    public ServiceWithDependencyImpl3(DependentService dependentService,
                                      DependentService dependentServiceOther,
                                      String name) {
        this.dependentService = dependentService;
        this.dependentServiceOther = dependentServiceOther;
        this.name = name;
    }

    public String generateStringWithContinuousMethodCall() {
        return dependentService.getRequestUser().get();
    }

    @Override
    public String generateStringWithDependentPublicMethod() {
        Object result = dependentService.getValues() + dependentServiceOther.getValues() + name;
        return result.toString() + " world";
    }

    @Override
    public String generateStringWithDependentPrivateMethod() {
        return null;
    }

    @Override
    public String generateStringWithDependentStaticMethod() {
        return null;
    }

    @Override
    public String generateStringWithDependentPrivateStaticMethod() {
        return null;
    }

    private static String privateStatic() {
        return "private Static";
    }
}
