package com.powerbridge.bssp.cod_cus.service.impl;

import com.powerbridge.bssp.base.service.impl.BaseServiceImpl;
import com.powerbridge.bssp.cod_cus.dao.CodCusTransfMapper;
import com.powerbridge.bssp.cod_cus.entity.CodCusTransf;
import com.powerbridge.bssp.cod_cus.service.ICodCusTransfService;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 项目名称：bssp-admin
 * 类名称：CodCusTransfServiceImpl
 * 类描述：CodCusTransf 表业务逻辑层接口实现
 * 创建人：willChen
 * 创建时间：2017/5/4 15:39
 * 修改人：willChen
 * 修改时间：2017/5/4 15:39
 */
@Service("codCusTransfService")
public class CodCusTransfServiceImpl extends BaseServiceImpl<CodCusTransfMapper,CodCusTransf> implements ICodCusTransfService {

    private static Logger logger = LoggerFactory.getLogger(CodCusTransfServiceImpl.class);
    @Autowired
    private CodCusTransfMapper codCusTransfMapper;

	public List<CodCusTransf> selectRedisDropDown() {
		return codCusTransfMapper.selectRedisDropDown();  
		    
	}

}
