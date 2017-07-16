package com.powerbridge.bssp.cod_cus.service.impl;

import com.powerbridge.bssp.base.service.impl.BaseServiceImpl;
import com.powerbridge.bssp.cod_cus.dao.CodCusMfteccodeMapper;
import com.powerbridge.bssp.cod_cus.entity.CodCusMfteccode;
import com.powerbridge.bssp.cod_cus.service.ICodCusMfteccodeService;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 项目名称：bssp-admin
 * 类名称：CodCusMfteccodeServiceImpl
 * 类描述：CodCusMfteccode 表业务逻辑层接口实现
 * 创建人：willChen
 * 创建时间：2017/5/4 15:39
 * 修改人：willChen
 * 修改时间：2017/5/4 15:39
 */
@Service("codCusMfteccodeService")
public class CodCusMfteccodeServiceImpl extends BaseServiceImpl<CodCusMfteccodeMapper,CodCusMfteccode> implements ICodCusMfteccodeService {

    private static Logger logger = LoggerFactory.getLogger(CodCusMfteccodeServiceImpl.class);
    @Autowired
    private CodCusMfteccodeMapper codCusMfteccodeMapper;

    public List<CodCusMfteccode> selectRedisDropDown() {
		return codCusMfteccodeMapper.selectRedisDropDown();  
		    
	}

}
