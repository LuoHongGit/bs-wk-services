package com.wk.ucenter.dao;

import com.wk.framework.domain.ucenter.XcCompanyUser;
import org.springframework.data.jpa.repository.JpaRepository;

public interface XcCompanyUserRepository extends JpaRepository<XcCompanyUser, String> {
    XcCompanyUser findXcCompanyUserByUserId(String userId);
}