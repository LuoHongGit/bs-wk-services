package com.wk.manage_cms.service;

import com.wk.framework.domain.system.SysDictionary;
import com.wk.manage_cms.dao.SysDictionaryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SysDictionaryService {
    @Autowired
    private SysDictionaryRepository sysDictionaryRepository;

    public SysDictionary getByType(String type){
        return sysDictionaryRepository.findByDType(type);
    }
}
