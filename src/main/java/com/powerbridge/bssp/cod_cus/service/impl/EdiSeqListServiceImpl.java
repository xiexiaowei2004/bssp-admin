package com.powerbridge.bssp.cod_cus.service.impl;

import com.powerbridge.bssp.base.service.impl.BaseServiceImpl;
import com.powerbridge.bssp.cod_cus.dao.EdiSeqListMapper;
import com.powerbridge.bssp.cod_cus.entity.EdiSeqList;
import com.powerbridge.bssp.cod_cus.service.IEdiSeqListService;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * 项目名称：bssp Maven Webapp
 * 类名称：IEdiSeqListService
 * 类描述：EdiSeqList 表业务逻辑层接口
 * 创建人：haihuihuang
 * 创建时间：2017年4月27日 下午10:12:17
 * @version
 */
@Service("ediSeqListService")
public class EdiSeqListServiceImpl extends BaseServiceImpl<EdiSeqListMapper,EdiSeqList> implements IEdiSeqListService {
	@Autowired
	private EdiSeqListMapper ediSeqListMapper;

	/*public List<EdiSeqList> selectRedisDropDown() {
		return ediSeqListMapper.selectRedisDropDown();

	}*/
}
