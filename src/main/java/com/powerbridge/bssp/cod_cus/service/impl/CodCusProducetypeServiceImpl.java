package com.powerbridge.bssp.cod_cus.service.impl;

import com.powerbridge.bssp.base.service.impl.BaseServiceImpl;
import com.powerbridge.bssp.cod_cus.dao.CodCusProducetypeMapper;
import com.powerbridge.bssp.cod_cus.entity.CodCusProducetype;
import com.powerbridge.bssp.cod_cus.service.ICodCusProducetypeService;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 项目名称：bssp-admin
 * 类名称：CodCusProducetypeServiceImpl
 * 类描述：CodCusProducetype 表业务逻辑层接口实现
 * 创建人：willChen
 * 创建时间：2017/5/4 15:39
 * 修改人：willChen
 * 修改时间：2017/5/4 15:39
 */
@Service("codCusProducetypeService")
public class CodCusProducetypeServiceImpl extends BaseServiceImpl<CodCusProducetypeMapper,CodCusProducetype> implements ICodCusProducetypeService {

    private static Logger logger = LoggerFactory.getLogger(CodCusProducetypeServiceImpl.class);
    @Autowired
    private CodCusProducetypeMapper codCusProducetypeMapper;


	public List<CodCusProducetype> selectRedisDropDown() {
		return codCusProducetypeMapper.selectRedisDropDown();  
		    
	}

}
