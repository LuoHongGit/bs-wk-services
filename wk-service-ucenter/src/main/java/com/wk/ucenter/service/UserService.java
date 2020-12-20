package com.wk.ucenter.service;

import com.wk.framework.domain.ucenter.XcCompanyUser;
import com.wk.framework.domain.ucenter.XcUser;
import com.wk.framework.domain.ucenter.ext.XcUserExt;
import com.wk.ucenter.dao.XcCompanyUserRepository;
import com.wk.ucenter.dao.XcUserRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * 用户业务层
 */
@Service
public class UserService {
    @Resource
    private XcUserRepository xcUserRepository;

    @Resource
    private XcCompanyUserRepository xcCompanyUserRepository;

    //根据用户账号查询用户信息
    public XcUser findXcUserByUsername(String username) {
        return xcUserRepository.findXcUserByUsername(username);
    }
    //根据账号查询用户的信息，返回用户扩展信息

    public XcUserExt getUserExt(String username) {
        XcUser xcUser = this.findXcUserByUsername(username);
        if (xcUser == null) {
            return null;
        }
        XcUserExt xcUserExt = new XcUserExt();
        BeanUtils.copyProperties(xcUser, xcUserExt);
        //用户id
        String userId = xcUserExt.getId();
        //查询用户所属公司
        XcCompanyUser xcCompanyUser = xcCompanyUserRepository.findXcCompanyUserByUserId(userId);
        if (xcCompanyUser != null) {
            String companyId = xcCompanyUser.getCompanyId();
            xcUserExt.setCompanyId(companyId);
        }
        return xcUserExt;
    }
}