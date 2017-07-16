package com.powerbridge.bssp.cod_std.service.impl;

import com.powerbridge.bssp.base.service.impl.BaseServiceImpl;
import com.powerbridge.bssp.cod_std.dao.CodStdSprocesstypeMapper;
import com.powerbridge.bssp.cod_std.entity.CodStdSprocesstype;
import com.powerbridge.bssp.cod_std.service.ICodStdSprocesstypeService;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 项目名称：bssp-admin
 * 类名称：CodStdSprocesstypeServiceImpl
 * 类描述：CodStdSprocesstype 表业务逻辑层接口实现
 * 创建人：willChen
 * 创建时间：2017/5/10 20:01
 * 修改人：willChen
 * 修改时间：2017/5/10 20:01
 */
@Service("codStdSprocesstypeService")
public class CodStdSprocesstypeServiceImpl extends BaseServiceImpl<CodStdSprocesstypeMapper,CodStdSprocesstype> implements ICodStdSprocesstypeService {

    private static Logger logger = LoggerFactory.getLogger(CodStdSprocesstypeServiceImpl.class);
    @Autowired
    private CodStdSprocesstypeMapper codStdSprocesstypeMapper;
	@Override
	public List<CodStdSprocesstype> selectRedisDropDown() {
		return codStdSprocesstypeMapper.selectRedisDropDown();  
		    
	}

}
