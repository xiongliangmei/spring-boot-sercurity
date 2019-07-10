package com.xl.service;

import com.xl.mapper.SysRoleMapper;
import com.xl.model.SysRole;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SysRoleService {
    @Autowired
    private SysRoleMapper sysRoleMapper;

    public SysRole selectById(Integer id){

        return sysRoleMapper.selectById(id);
    }

    public  Integer selectByName(String name) {
        return sysRoleMapper.selectByName(name);
    }
}
