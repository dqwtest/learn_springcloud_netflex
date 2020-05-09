package com.example.spring.cloud.netflix.provider.service.impl;

import com.example.spring.cloud.netflix.provider.domain.Account;
import com.example.spring.cloud.netflix.provider.mapper.AccountMapper;
import com.example.spring.cloud.netflix.provider.service.AccountService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class AccountServiceImpl implements AccountService {

    @Resource
    private AccountMapper accountMapper;

    @Override
    public Account selectById(Long id) {
        return accountMapper.selectById(id);
    }
}
