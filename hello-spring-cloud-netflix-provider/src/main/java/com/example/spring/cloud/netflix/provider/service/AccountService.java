package com.example.spring.cloud.netflix.provider.service;

import com.example.spring.cloud.netflix.provider.domain.Account;

public interface AccountService {
    public Account selectById(Long id);
}
