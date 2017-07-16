package com.powerbridge.bssp.cod_cus.service.impl;

import com.powerbridge.bssp.base.service.impl.BaseServiceImpl;
import com.powerbridge.bssp.cod_cus.dao.CodCusInvestmodeMapper;
import com.powerbridge.bssp.cod_cus.entity.CodCusInvestmode;
import com.powerbridge.bssp.cod_cus.service.ICodCusInvestmodeService;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 项目名称：bssp-admin
 * 类名称：CodCusInvestmodeServiceImpl
 * 类描述：CodCusInvestmode 表业务逻辑层接口实现
 * 创建人：willChen
 * 创建时间：2017/5/5 09:39
 * 修改人：willChen
 * 修改时间：2017/5/5 09:39
 */
@Service("codCusInvestmodeService")
public class CodCusInvestmodeServiceImpl extends BaseServiceImpl<CodCusInvestmodeMapper,CodCusInvestmode> implements ICodCusInvestmodeService {

    private static Logger logger = LoggerFactory.getLogger(CodCusInvestmodeServiceImpl.class);
    @Autowired
    private CodCusInvestmodeMapper codCusInvestmodeMapper;
    
    
	public List<CodCusInvestmode> selectRedisDropDown() {
		return codCusInvestmodeMapper.selectRedisDropDown();  
		    
	}

}
