package com.valiant.unittest.service.impl;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class ServiceWithDependencyImpl1Tests {

    @InjectMocks
    ServiceWithDependencyImpl1 service;

    @Mock
    DependentServiceImpl dependentService;

    @Test
    void generateStringWithContinuousMethodCall() {
    }

    @Test
    void generateStringWithDependentPublicMethod() {
        when(dependentService.getValues()).thenReturn("hello");
        Assertions.assertEquals("hello", service.generateStringWithDependentPublicMethod());
    }

    @Test
    void generateStringWithDependentPrivateMethod() {
    }

    @Test
    void generateStringWithDependentStaticMethod() {
    }

    @Test
    void generateStringWithDependentPrivateStaticMethod() {
    }
}