package com.wk.auth.client;

import com.wk.api.ucenter.UcenterControllerApi;
import com.wk.framework.domain.ucenter.ext.XcUserExt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@FeignClient("wk-service-ucenter")
public interface UcenterControllerClient{
    @GetMapping("/ucenter/getuserext")
    public XcUserExt getUserext(@RequestParam("username") String username);
}