package com.powerbridge.bssp.cod_cus.service.impl;

import com.powerbridge.bssp.base.service.impl.BaseServiceImpl;
import com.powerbridge.bssp.cod_cus.dao.CodCusMftecodeMapper;
import com.powerbridge.bssp.cod_cus.entity.CodCusMftecode;
import com.powerbridge.bssp.cod_cus.service.ICodCusMftecodeService;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 项目名称：bssp-admin
 * 类名称：CodCusMftecodeServiceImpl
 * 类描述：CodCusMftecode 表业务逻辑层接口实现
 * 创建人：willChen
 * 创建时间：2017/5/5 09:39
 * 修改人：willChen
 * 修改时间：2017/5/5 09:39
 */
@Service("codCusMftecodeService")
public class CodCusMftecodeServiceImpl extends BaseServiceImpl<CodCusMftecodeMapper,CodCusMftecode> implements ICodCusMftecodeService {

    private static Logger logger = LoggerFactory.getLogger(CodCusMftecodeServiceImpl.class);
    @Autowired
    private CodCusMftecodeMapper codCusMftecodeMapper;

	public List<CodCusMftecode> selectRedisDropDown() {
		return codCusMftecodeMapper.selectRedisDropDown();  
		    
	}

}
