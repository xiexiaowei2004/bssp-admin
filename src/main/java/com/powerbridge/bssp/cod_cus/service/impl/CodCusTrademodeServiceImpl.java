package com.powerbridge.bssp.cod_cus.service.impl;

import com.powerbridge.bssp.base.service.impl.BaseServiceImpl;
import com.powerbridge.bssp.cod_cus.dao.CodCusTrademodeMapper;
import com.powerbridge.bssp.cod_cus.entity.CodCusTrademode;
import com.powerbridge.bssp.cod_cus.service.ICodCusTrademodeService;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 项目名称：bssp-admin
 * 类名称：CodCusTrademodeServiceImpl
 * 类描述：CodCusTrademode 表业务逻辑层接口实现
 * 创建人：willChen
 * 创建时间：2017/5/4 15:39
 * 修改人：willChen
 * 修改时间：2017/5/4 15:39
 */
@Service("codCusTrademodeService")
public class CodCusTrademodeServiceImpl extends BaseServiceImpl<CodCusTrademodeMapper,CodCusTrademode> implements ICodCusTrademodeService {

    private static Logger logger = LoggerFactory.getLogger(CodCusTrademodeServiceImpl.class);
    @Autowired
    private CodCusTrademodeMapper codCusTrademodeMapper;


	public List<CodCusTrademode> selectRedisDropDown() {
		return codCusTrademodeMapper.selectRedisDropDown();  
		    
	}

}
