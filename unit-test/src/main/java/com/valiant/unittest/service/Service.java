package com.valiant.unittest.service;

/**
 * @author : yuanqi
 * @since : 2020/5/21
 */
public interface Service {
    String generateString();

    String generateStringWithPublicMethod();

    String generateStringWithPrivateMethod();

    String generateStringWithStaticMethod();

    String generateStringWithPrivateStaticMethod();
}
