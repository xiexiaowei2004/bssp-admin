package com.powerbridge.bssp.cod_cus.service.impl;

import com.powerbridge.bssp.base.service.impl.BaseServiceImpl;
import com.powerbridge.bssp.cod_cus.dao.CodCusPaymodeMapper;
import com.powerbridge.bssp.cod_cus.entity.CodCusPaymode;
import com.powerbridge.bssp.cod_cus.service.ICodCusPaymodeService;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 项目名称：bssp-admin
 * 类名称：CodCusPaymodeServiceImpl
 * 类描述：CodCusPaymode 表业务逻辑层接口实现
 * 创建人：willChen
 * 创建时间：2017/5/4 15:39
 * 修改人：willChen
 * 修改时间：2017/5/4 15:39
 */
@Service("codCusPaymodeService")
public class CodCusPaymodeServiceImpl extends BaseServiceImpl<CodCusPaymodeMapper,CodCusPaymode> implements ICodCusPaymodeService {

    private static Logger logger = LoggerFactory.getLogger(CodCusPaymodeServiceImpl.class);
    @Autowired
    private CodCusPaymodeMapper codCusPaymodeMapper;


	public List<CodCusPaymode> selectRedisDropDown() {
		return codCusPaymodeMapper.selectRedisDropDown();  
		    
	}

}
