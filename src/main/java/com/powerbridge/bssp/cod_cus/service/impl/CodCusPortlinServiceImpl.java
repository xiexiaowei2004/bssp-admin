package com.powerbridge.bssp.cod_cus.service.impl;

import com.powerbridge.bssp.base.service.impl.BaseServiceImpl;
import com.powerbridge.bssp.cod_cus.dao.CodCusPortlinMapper;
import com.powerbridge.bssp.cod_cus.entity.CodCusPortlin;
import com.powerbridge.bssp.cod_cus.service.ICodCusPortlinService;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 项目名称：bssp-admin
 * 类名称：CodCusPortlinServiceImpl
 * 类描述：CodCusPortlin 表业务逻辑层接口实现
 * 创建人：willChen
 * 创建时间：2017/5/4 15:39
 * 修改人：willChen
 * 修改时间：2017/5/4 15:39
 */
@Service("codCusPortlinService")
public class CodCusPortlinServiceImpl extends BaseServiceImpl<CodCusPortlinMapper,CodCusPortlin> implements ICodCusPortlinService {

    private static Logger logger = LoggerFactory.getLogger(CodCusPortlinServiceImpl.class);
    @Autowired
    private CodCusPortlinMapper codCusPortlinMapper;

	public List<CodCusPortlin> selectRedisDropDown() {
		return codCusPortlinMapper.selectRedisDropDown();  
		    
	}

}
