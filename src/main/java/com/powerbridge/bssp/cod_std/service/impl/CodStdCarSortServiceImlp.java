package com.powerbridge.bssp.cod_std.service.impl;




import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.powerbridge.bssp.base.service.impl.BaseServiceImpl;
import com.powerbridge.bssp.cod_std.dao.CodStdCarSortMapper;
import com.powerbridge.bssp.cod_std.entity.CodStdCarSort;
import com.powerbridge.bssp.cod_std.service.ICodStdCarSortService;





/**
 *
 * 项目名称：bssp Maven Webapp
 * 类名称：CodCusBriefServiceImpl
 * 类描述：CodCusBrief 表业务逻辑层接口 实现类
 * 创建时间：2017年4月27日 下午10:12:17
 * @version
 */
@Service("codStdCarSortService")
public class CodStdCarSortServiceImlp extends BaseServiceImpl<CodStdCarSortMapper,CodStdCarSort> implements ICodStdCarSortService {

	@Autowired
	private CodStdCarSortMapper codStdCarSortfMapper;
	/*
	 * public List<CodStdCarSort> selectCarSortByList(){ return
	 * codStdCarSortfMapper.selectCarSortByList(); }
	 */

	@Override
	public List<CodStdCarSort> selectRedisDropDown() {
		return codStdCarSortfMapper.selectRedisDropDown();

	}
}
