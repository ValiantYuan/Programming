package com.valiant.unittest.service.impl;

import com.valiant.unittest.service.Service;

/**
 * @author : yuanqi
 * @since : 2020/5/21
 */
public class ServiceImpl implements Service {


    private RequestUser requestUser;





    @Override
    public String generateString() {
        return "hello world";
    }

    @Override
    public String generateStringWithPrivateMethod() {
        RequestUserHolder.get();
        return "this is from " + privateString();
//        return "this is from " + privateObject();
    }

    @Override
    public String generateStringWithPublicMethod() {
        doNothing();
        return "this is from " + publicString("", "");
    }

    @Override
    public String generateStringWithStaticMethod() {
        return "this is from " + staticString();
    }

    @Override
    public String generateStringWithPrivateStaticMethod() {
        return null;
    }

    public String publicString(String a, String b) {
        System.out.println("public");
        return "public";
    }

    private String privateString() {
        System.out.println("privateString is invoked");
        return "hello";
    }

    private Object privateObject() {
        return new Object();
    }

    public static String staticString() {
        return "static";
    }

    public void doNothing() {
        System.out.println("do nothing");
        return;
    }


    private static String privateStaticString() {
        return "private Static";
    }
}
