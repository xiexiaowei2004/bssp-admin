package com.powerbridge.bssp.cod_cus.service.impl;

import java.util.List;

import com.baomidou.mybatisplus.plugins.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.powerbridge.bssp.base.service.impl.BaseServiceImpl;
import com.powerbridge.bssp.cod_cus.dao.CodCusComplexMapper;
import com.powerbridge.bssp.cod_cus.entity.CodCusComplex;
import com.powerbridge.bssp.cod_cus.service.ICodCusComplexService;

@Service("codCusComplexService")
public class CodCusComplexServiceImpl extends BaseServiceImpl<CodCusComplexMapper, CodCusComplex> implements ICodCusComplexService {
    @Autowired
    private CodCusComplexMapper codCusComplexMapper;

    public List<CodCusComplex> selectRedisDropDown() {
        return codCusComplexMapper.selectRedisDropDown();

    }
    /**
     * 查询表表/分页
     *
     * @param page 分页
     * @param codCusComplex
     * @return List<EmsCusBsc>
     */
    public Page<CodCusComplex> selectComplexList(Page<CodCusComplex> page, CodCusComplex codCusComplex){
        page.setRecords(codCusComplexMapper.selectComplexList(page, codCusComplex));
        return page;
    }
    /**
     * 查询商品编码是否存在
     * @param gdecd
     * @return
     */
    @Override
    public String selectCountByGdecd(String gdecd){
        return codCusComplexMapper.selectCountByGdecd(gdecd);
    }
}
