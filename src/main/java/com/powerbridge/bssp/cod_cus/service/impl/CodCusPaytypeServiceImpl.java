package com.powerbridge.bssp.cod_cus.service.impl;

import com.powerbridge.bssp.base.service.impl.BaseServiceImpl;
import com.powerbridge.bssp.cod_cus.dao.CodCusPaytypeMapper;
import com.powerbridge.bssp.cod_cus.entity.CodCusPaytype;
import com.powerbridge.bssp.cod_cus.service.ICodCusPaytypeService;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 项目名称：bssp-admin
 * 类名称：CodCusPaytypeServiceImpl
 * 类描述：CodCusPaytype 表业务逻辑层接口实现
 * 创建人：willChen
 * 创建时间：2017/5/4 15:39
 * 修改人：willChen
 * 修改时间：2017/5/4 15:39
 */
@Service("codCusPaytypeService")
public class CodCusPaytypeServiceImpl extends BaseServiceImpl<CodCusPaytypeMapper,CodCusPaytype> implements ICodCusPaytypeService {

    private static Logger logger = LoggerFactory.getLogger(CodCusPaytypeServiceImpl.class);
    @Autowired
    private CodCusPaytypeMapper codCusPaytypeMapper;

	public List<CodCusPaytype> selectRedisDropDown() {
		return codCusPaytypeMapper.selectRedisDropDown();  
		    
	}

}
