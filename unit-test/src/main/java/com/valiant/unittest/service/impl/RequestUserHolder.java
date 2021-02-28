package com.valiant.unittest.service.impl;

import org.springframework.core.NamedThreadLocal;

public class RequestUserHolder {
    private static final ThreadLocal<RequestUser> userHolder = new NamedThreadLocal("Request user");

    private RequestUserHolder() {
    }

    public static void set(RequestUser user) {
        userHolder.set(user);
    }

    public static RequestUser get() {
        return (RequestUser)userHolder.get();
    }

    public static void clear() {
        userHolder.remove();
    }
}