package com.powerbridge.bssp.cod_cus.service.impl;

import com.powerbridge.bssp.base.service.impl.BaseServiceImpl;
import com.powerbridge.bssp.cod_cus.dao.CodCusWrapMapper;
import com.powerbridge.bssp.cod_cus.entity.CodCusWrap;
import com.powerbridge.bssp.cod_cus.service.ICodCusWrapService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 项目名称：bssp-admin
 * 类名称：CodCusWrapServiceImpl
 * 类描述：CodCusWrap 表业务逻辑层接口实现
 * 创建人：willChen
 * 创建时间：2017/5/5 09:39
 * 修改人：willChen
 * 修改时间：2017/5/5 09:39
 */
@Service("codCusWrapService")
public class CodCusWrapServiceImpl extends BaseServiceImpl<CodCusWrapMapper, CodCusWrap> implements ICodCusWrapService {
    @Autowired
    private CodCusWrapMapper codCusWrapMapper;


    public List<CodCusWrap> selectRedisDropDown() {
        return codCusWrapMapper.selectRedisDropDown();

    }


}
