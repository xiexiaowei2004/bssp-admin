package com.powerbridge.bssp.cod_cus.service.impl;

import com.powerbridge.bssp.base.service.impl.BaseServiceImpl;
import com.powerbridge.bssp.cod_cus.dao.CodCusTradeMapper;
import com.powerbridge.bssp.cod_cus.entity.CodCusTrade;
import com.powerbridge.bssp.cod_cus.service.ICodCusTradeService;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 项目名称：bssp-admin
 * 类名称：CodCusTradeServiceImpl
 * 类描述：CodCusTrade 表业务逻辑层接口实现
 * 创建人：willChen
 * 创建时间：2017/5/4 15:39
 * 修改人：willChen
 * 修改时间：2017/5/4 15:39
 */
@Service("codCusTradeService")
public class CodCusTradeServiceImpl extends BaseServiceImpl<CodCusTradeMapper,CodCusTrade> implements ICodCusTradeService {

    private static Logger logger = LoggerFactory.getLogger(CodCusTradeServiceImpl.class);
    @Autowired
    private CodCusTradeMapper codCusTradeMapper;


	public List<CodCusTrade> selectRedisDropDown() {
		return codCusTradeMapper.selectRedisDropDown();  
		    
	}

}
