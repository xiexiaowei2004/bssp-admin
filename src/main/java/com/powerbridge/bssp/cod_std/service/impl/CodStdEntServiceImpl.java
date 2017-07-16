package com.powerbridge.bssp.cod_std.service.impl;

import com.powerbridge.bssp.base.service.impl.BaseServiceImpl;
import com.powerbridge.bssp.cod_std.dao.CodStdEnttypeMapper;
import com.powerbridge.bssp.cod_std.entity.CodStdEnttype;
import com.powerbridge.bssp.cod_std.service.ICodStdEnttypeService;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 项目名称：bssp-admin
 * 类名称：CodStdEnttypeServiceImpl
 * 类描述：CodStdEnttype 表业务逻辑层接口实现
 * 创建人：willChen
 * 创建时间：2017/5/10 20:01
 * 修改人：willChen
 * 修改时间：2017/5/10 20:01
 */
@Service("codStdEnttypeService")
public class CodStdEntServiceImpl extends BaseServiceImpl<CodStdEnttypeMapper,CodStdEnttype> implements ICodStdEnttypeService {
//
//    private static Logger logger = LoggerFactory.getLogger(CodStdEntServiceImpl.class);
	@Autowired
	private CodStdEnttypeMapper codStdEnttypeMapper;

	@Override
	public List<CodStdEnttype> selectRedisDropDown() {
		return codStdEnttypeMapper.selectRedisDropDown();

	}

}
