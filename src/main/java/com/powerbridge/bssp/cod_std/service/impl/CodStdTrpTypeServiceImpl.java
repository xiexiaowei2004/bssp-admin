package com.powerbridge.bssp.cod_std.service.impl;

import com.powerbridge.bssp.base.service.impl.BaseServiceImpl;
import com.powerbridge.bssp.cod_std.dao.CodStdTrpTypeMapper;
import com.powerbridge.bssp.cod_std.entity.CodStdTrpType;
import com.powerbridge.bssp.cod_std.service.ICodStdTrpTypeService;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 项目名称：bssp-admin
 * 类名称：CodStdTransportTypeServiceImpl
 * 类描述：CodStdTransportType 表业务逻辑层接口 实现类
 * 创建人：zsl
 * 创建时间：2017/5/11 19:30
 * 修改人：zsl
 * 修改时间：2017/5/11 19:30
 */
@Service("codStdTrpTypeService")
public class CodStdTrpTypeServiceImpl extends BaseServiceImpl<CodStdTrpTypeMapper,CodStdTrpType> implements ICodStdTrpTypeService {
	@Autowired
	private CodStdTrpTypeMapper codStdTrpTypeMapper;

	@Override
	public List<CodStdTrpType> selectRedisDropDown() {
		return codStdTrpTypeMapper.selectRedisDropDown();

	}

}
