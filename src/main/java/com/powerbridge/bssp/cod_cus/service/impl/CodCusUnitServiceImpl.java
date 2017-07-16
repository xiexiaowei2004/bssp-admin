package com.powerbridge.bssp.cod_cus.service.impl;

import com.powerbridge.bssp.base.service.impl.BaseServiceImpl;
import com.powerbridge.bssp.cod_cus.dao.CodCusUnitMapper;
import com.powerbridge.bssp.cod_cus.entity.CodCusUnit;
import com.powerbridge.bssp.cod_cus.service.ICodCusUnitService;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 项目名称：bssp-admin
 * 类名称：CodCusUnitServiceImpl
 * 类描述：CodCusUnit 表业务逻辑层接口实现
 * 创建人：willChen
 * 创建时间：2017/5/5 09:39
 * 修改人：willChen
 * 修改时间：2017/5/5 09:39
 */
@Service("codCusUnitService")
public class CodCusUnitServiceImpl extends BaseServiceImpl<CodCusUnitMapper,CodCusUnit> implements ICodCusUnitService {

    private static Logger logger = LoggerFactory.getLogger(CodCusUnitServiceImpl.class);
    @Autowired
    private CodCusUnitMapper codCusUnitMapper;


	public List<CodCusUnit> selectRedisDropDown() {
		return codCusUnitMapper.selectRedisDropDown();  
		    
	}

}
