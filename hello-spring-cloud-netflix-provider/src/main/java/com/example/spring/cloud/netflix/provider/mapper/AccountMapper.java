package com.example.spring.cloud.netflix.provider.mapper;

import com.example.spring.cloud.netflix.provider.domain.Account;
import org.apache.ibatis.annotations.Param;
import tk.mybatis.mapper.MyMapper;

public interface AccountMapper extends MyMapper<Account> {
    Account selectById(@Param("id") Long id);
}