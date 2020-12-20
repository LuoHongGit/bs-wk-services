package com.wk.ucenter.dao;

import com.wk.framework.domain.ucenter.XcMenu;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface XcMenuMapper {
    List<XcMenu> selectPermissionByUserId(String userid);
}