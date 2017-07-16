package com.powerbridge.bssp.cod_cus.service.impl;

import com.powerbridge.bssp.base.service.impl.BaseServiceImpl;
import com.powerbridge.bssp.cod_cus.dao.CodCusInterfaceMapper;
import com.powerbridge.bssp.cod_cus.entity.CodCusInterface;
import com.powerbridge.bssp.cod_cus.service.ICodCusInterfaceService;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 项目名称：bssp-admin
 * 类名称：CodCusInterfaceInterfaceServiceImpl
 * 类描述：CodCusInterface 表业务逻辑层接口 实现类
 * 创建人：willChen
 * 创建时间：2017/5/6 12:03
 * 修改人：willChen
 * 修改时间：2017/5/6 12:03
 */
@Service("codCusInterfaceService")
public class CodCusInterfaceServiceImpl extends BaseServiceImpl<CodCusInterfaceMapper,CodCusInterface> implements ICodCusInterfaceService {
	@Autowired
	private CodCusInterfaceMapper codCusInterfaceMapper;

	public List<CodCusInterface> selectRedisDropDown() {
		return codCusInterfaceMapper.selectRedisDropDown();

	}
}
