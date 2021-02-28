package com.valiant.unittest.service.impl;

import com.valiant.unittest.service.DependentService;
import com.valiant.unittest.service.ServiceWithDependency;

/**
 * @author : yuanqi
 * @since : 2020/5/21
 */
public class ServiceWithDependencyImpl1 implements ServiceWithDependency {

    public void setDependentService(DependentService dependentService) {
        this.dependentService = dependentService;
    }

    private DependentService dependentService;

//    private ServiceWithDependencyImpl1(DependentService dependentService) {
//        System.out.println("constructor");
//        System.out.println(dependentService.getClass());
//        this.dependentService = dependentService;
//    }

    private ServiceWithDependencyImpl1() {
        System.out.println("constructor");
        System.out.println(dependentService.getClass());
//        this.dependentService = dependentService;
    }

    public String generateStringWithContinuousMethodCall() {
        return dependentService.getRequestUser().get();
    }

    @Override
    public String generateStringWithDependentPublicMethod() {
        return dependentService.getValues();
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
}
