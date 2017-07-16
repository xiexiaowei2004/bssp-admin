package com.powerbridge.bssp.cod_cus.service.impl;

import com.powerbridge.bssp.base.service.impl.BaseServiceImpl;
import com.powerbridge.bssp.cod_cus.dao.CodCusApplyInterfaceMappingMapper;
import com.powerbridge.bssp.cod_cus.entity.CodCusApplyInterfaceMapping;
import com.powerbridge.bssp.cod_cus.service.ICodCusApplyInterfaceService;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 项目名称：bssp-admin
 * 类名称：CodCusApplyInterfaceServiceImpl
 * 类描述：CodCusApplyInterfaceMapping 表业务逻辑层接口 实现类
 * 创建人：willChen
 * 创建时间：2017/5/6 12:03
 * 修改人：willChen
 * 修改时间：2017/5/6 12:03
 */
@Service("codCusApplyInterfaceService")
public class CodCusApplyInterfaceServiceImpl extends BaseServiceImpl<CodCusApplyInterfaceMappingMapper,CodCusApplyInterfaceMapping> implements ICodCusApplyInterfaceService {

	/* @Autowired
	private CodCusApplyInterfaceMappingMapper codCusApplyInterfaceMappingMapper;
	 
	public List<CodCusApplyInterfaceMapping> selectRedisDropDown() {
		return codCusApplyInterfaceMappingMapper.selectRedisDropDown();  
		    
	}*/
}
