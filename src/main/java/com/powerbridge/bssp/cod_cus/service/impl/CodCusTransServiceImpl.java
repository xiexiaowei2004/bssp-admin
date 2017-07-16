package com.powerbridge.bssp.cod_cus.service.impl;

import com.powerbridge.bssp.base.service.impl.BaseServiceImpl;
import com.powerbridge.bssp.cod_cus.dao.CodCusTransMapper;
import com.powerbridge.bssp.cod_cus.entity.CodCusTrans;
import com.powerbridge.bssp.cod_cus.service.ICodCusTransService;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 项目名称：bssp-admin
 * 类名称：CodCusTransServiceImpl
 * 类描述：CodCusTrans 表业务逻辑层接口实现
 * 创建人：willChen
 * 创建时间：2017/5/4 15:39
 * 修改人：willChen
 * 修改时间：2017/5/4 15:39
 */
@Service("codCusTransService")
public class CodCusTransServiceImpl extends BaseServiceImpl<CodCusTransMapper,CodCusTrans> implements ICodCusTransService {

    private static Logger logger = LoggerFactory.getLogger(CodCusTransServiceImpl.class);
    @Autowired
    private CodCusTransMapper codCusTransMapper;


	public List<CodCusTrans> selectRedisDropDown() {
		return codCusTransMapper.selectRedisDropDown();  
		    
	}

}
