package com.valiant.unittest.service.impl;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.mock;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;
import org.mockito.Spy;


/**
 * @author : yuanqi
 * @since : 2020/6/7
 */
public class TestImp {
    @InjectMocks
    @Spy
    ServiceImpl service;


    @BeforeEach
    public void before() {
        System.out.println("before() is invoked");
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void StaticTest() throws Exception {
        ServiceImpl mockServiceImpl = mock(ServiceImpl.class);


        String expected = "this is from modified private";
        String actual = mockServiceImpl.generateStringWithPrivateMethod();
        assertEquals("与预期不符", expected, actual);
    }
}
