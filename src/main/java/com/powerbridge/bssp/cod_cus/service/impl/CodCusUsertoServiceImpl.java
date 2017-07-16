package com.powerbridge.bssp.cod_cus.service.impl;

import com.powerbridge.bssp.base.service.impl.BaseServiceImpl;
import com.powerbridge.bssp.cod_cus.dao.CodCusUsertoMapper;
import com.powerbridge.bssp.cod_cus.entity.CodCusUserto;
import com.powerbridge.bssp.cod_cus.service.ICodCusUsertoService;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 项目名称：bssp-admin
 * 类名称：CodCusUsertoServiceImpl
 * 类描述：CodCusUserto 表业务逻辑层接口实现
 * 创建人：willChen
 * 创建时间：2017/5/5 09:39
 * 修改人：willChen
 * 修改时间：2017/5/5 09:39
 */
@Service("codCusUsertoService")
public class CodCusUsertoServiceImpl extends BaseServiceImpl<CodCusUsertoMapper,CodCusUserto> implements ICodCusUsertoService {

    private static Logger logger = LoggerFactory.getLogger(CodCusUsertoServiceImpl.class);
    @Autowired
    private CodCusUsertoMapper codCusUsertoMapper;
	
	public List<CodCusUserto> selectRedisDropDown() {
		return codCusUsertoMapper.selectRedisDropDown();  
		    
	}

}
