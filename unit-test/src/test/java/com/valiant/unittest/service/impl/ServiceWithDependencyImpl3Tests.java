package com.valiant.unittest.service.impl;

import static org.mockito.Mockito.when;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Answers;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.Spy;

class ServiceWithDependencyImpl3Tests {

    ServiceWithDependencyImpl3 serviceWithDependencyImpl3;

    @Mock(answer = Answers.RETURNS_DEEP_STUBS)
//    @Mock
    DependentServiceImpl dependentServiceMock;

    @Spy
    DependentServiceImpl dependentServiceSpy;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        serviceWithDependencyImpl3 = new ServiceWithDependencyImpl3(dependentServiceMock,
            dependentServiceSpy,
            "valiant");
    }

    @Test
    void generateStringTest() {
        String expected = "mock spy valiant world";
        when(dependentServiceSpy.getValues()).thenReturn("spy ");
        when(dependentServiceMock.getValues()).thenReturn("mock ");
        String actual = serviceWithDependencyImpl3.generateStringWithDependentPublicMethod();
        Assertions.assertEquals(expected, actual, "与预期不符");
    }


    @Test
    public void generateStringWithContinuousMethodCall() {
        when(dependentServiceMock.getRequestUser().get()).thenReturn("hello");
        Assertions.assertEquals("hello", serviceWithDependencyImpl3.generateStringWithContinuousMethodCall());
    }
}