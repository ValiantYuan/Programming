package com.valiant.unittest.service;

/**
 * @author : yuanqi
 * @since : 2020/5/21
 */
public interface ServiceWithDependency {
    String generateStringWithDependentPublicMethod();

    String generateStringWithDependentPrivateMethod();

    String generateStringWithDependentStaticMethod();

    String generateStringWithDependentPrivateStaticMethod();

}
