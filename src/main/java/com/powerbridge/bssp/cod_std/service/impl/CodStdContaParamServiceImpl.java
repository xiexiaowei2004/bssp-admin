package com.powerbridge.bssp.cod_std.service.impl;


import com.powerbridge.bssp.base.service.impl.BaseServiceImpl;
import com.powerbridge.bssp.cod_std.dao.CodStdContaParamMapper;
import com.powerbridge.bssp.cod_std.entity.CodStdContaParam;
import com.powerbridge.bssp.cod_std.service.ICodStdContaParamService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("codStdContaParamService")
public class CodStdContaParamServiceImpl extends BaseServiceImpl<CodStdContaParamMapper, CodStdContaParam> implements ICodStdContaParamService {

    @Autowired
    private CodStdContaParamMapper codStdContaParamMapper;

    public List<CodStdContaParam> selectRedisDropDown() {
        return codStdContaParamMapper.selectRedisDropDown();
    }
}
