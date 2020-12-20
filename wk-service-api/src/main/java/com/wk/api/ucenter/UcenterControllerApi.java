package com.wk.api.ucenter;

import com.wk.framework.domain.ucenter.ext.XcUserExt;
import io.swagger.annotations.Api;

@Api(value = "用户中心",description = "用户中心管理")
public interface UcenterControllerApi {
    //根据用户名查询用户
    XcUserExt getUserext(String username);
}