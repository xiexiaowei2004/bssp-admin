package com.powerbridge.bssp.cod_cus.service.impl;

import com.powerbridge.bssp.base.service.impl.BaseServiceImpl;
import com.powerbridge.bssp.cod_cus.dao.CodCusInvclassMapper;
import com.powerbridge.bssp.cod_cus.entity.CodCusInvclass;
import com.powerbridge.bssp.cod_cus.service.ICodCusInvclassService;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 项目名称：bssp-admin
 * 类名称：CodCusInvclassServiceImpl
 * 类描述：CodCusInvclass 表业务逻辑层接口实现
 * 创建人：willChen
 * 创建时间：2017/5/5 09:39
 * 修改人：willChen
 * 修改时间：2017/5/5 09:39
 */
@Service("codCusInvclassService")
public class CodCusInvclassServiceImpl extends BaseServiceImpl<CodCusInvclassMapper,CodCusInvclass> implements ICodCusInvclassService {

    private static Logger logger = LoggerFactory.getLogger(CodCusInvclassServiceImpl.class);
    @Autowired
    private CodCusInvclassMapper codCusInvclassMapper;
	
	public List<CodCusInvclass> selectRedisDropDown() {
		return codCusInvclassMapper.selectRedisDropDown();  
		    
	}

}
