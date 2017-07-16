package com.powerbridge.bssp.cod_cus.service.impl;

import com.powerbridge.bssp.base.service.impl.BaseServiceImpl;
import com.powerbridge.bssp.cod_cus.dao.EdiSeqInfoMapper;
import com.powerbridge.bssp.cod_cus.entity.EdiSeqInfo;
import com.baomidou.mybatisplus.plugins.Page;
import com.powerbridge.bssp.cod_cus.service.IEdiSeqInfoService;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * 项目名称：bssp Maven Webapp
 * 类名称：IEdiSeqInfoService
 * 类描述：EdiSeqInfo 表业务逻辑层接口
 * 创建人：haihuihuang
 * 创建时间：2017年4月27日 下午10:12:17
 * @version
 */

@Service("ediSeqInfoService")
public class EdiSeqInfoServiceImpl extends BaseServiceImpl<EdiSeqInfoMapper,EdiSeqInfo> implements IEdiSeqInfoService {
	@Autowired
	private EdiSeqInfoMapper ediSeqInfoMapper;

	@Override
	public Page<EdiSeqInfo> selectEdiSeqInfoList(Page<EdiSeqInfo> page, EdiSeqInfo emsBwsDt) throws Exception {

		page.setRecords(ediSeqInfoMapper.selectEdiSeqInfoList(page, emsBwsDt));
		return page;
	}
}
