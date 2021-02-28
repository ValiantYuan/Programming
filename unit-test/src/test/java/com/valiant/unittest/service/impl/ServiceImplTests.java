package com.valiant.unittest.service.impl;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

//@ExtendWith(MockitoExtension.class)
public class ServiceImplTests {

//    @InjectMocks
    @Mock
    ServiceImpl service;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void generateStringWithPublicMethod() {
        when(service.publicString("hello", "world")).thenReturn("modified public");
        String actual = service.generateStringWithPublicMethod();
        Assertions.assertEquals("ddd", actual, "xxx");
    }


    @Test
    public void generateStringWithStaticMethod() {
        when(service.staticString()).thenReturn("modified static");
        String actual = service.generateStringWithStaticMethod();
        Assertions.assertEquals("ddd", actual, "xxx");
    }
}