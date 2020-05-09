package com.example.spring.cloud.netflix.provider.controller;

import com.example.spring.cloud.netflix.provider.domain.Account;
import com.example.spring.cloud.netflix.provider.mapper.AccountMapper;
import com.example.spring.cloud.netflix.provider.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CacheController {

    @Autowired
    private AccountService accountService;

    @GetMapping("/getUserIdByExtendCommand/{id}")
    public String getuserIdByExtendCommand(@PathVariable("id")Long id) {
        System.out.println(id);
        Account account = accountService.selectById(id);
        System.out.println(account);
        return account.toString();
    }
}
