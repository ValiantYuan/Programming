package com.valiant.unittest.service.impl;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;

/**
 * @author : yuanqi
 * @since : 2020/6/10
 */
@RunWith(PowerMockRunner.class)
@PrepareForTest(ServiceImpl.class)
public class PowerMockTest {

    @InjectMocks
    ServiceImpl service;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        service = PowerMockito.spy(service);
    }

    @Test
    public void test() throws Exception {
        PowerMockito.when(service, "privateString").thenReturn("private");
        String result = service.generateStringWithPrivateMethod();
        Assert.assertEquals("hello", result);
    }

}
