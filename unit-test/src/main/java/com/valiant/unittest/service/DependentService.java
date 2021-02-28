package com.valiant.unittest.service;

import com.valiant.unittest.service.impl.RequestUser;

import org.springframework.stereotype.Service;

/**
 * @author : yuanqi
 * @since : 2020/5/21
 */
@Service
public interface DependentService {
    String getValues();
    RequestUser getRequestUser();
}
