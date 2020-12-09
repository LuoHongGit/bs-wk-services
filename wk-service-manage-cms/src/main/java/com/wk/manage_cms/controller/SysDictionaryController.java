package com.wk.manage_cms.controller;

import com.wk.api.sys.SysDictionaryControllerApi;
import com.wk.framework.domain.system.SysDictionary;
import com.wk.manage_cms.service.SysDictionaryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/sys/dictionary")
public class SysDictionaryController implements SysDictionaryControllerApi {
    @Autowired
    private SysDictionaryService sysDictionaryService;

    @GetMapping("/get/{type}")
    public SysDictionary getByType(@PathVariable("type")String type){
        return sysDictionaryService.getByType(type);
    }
}
