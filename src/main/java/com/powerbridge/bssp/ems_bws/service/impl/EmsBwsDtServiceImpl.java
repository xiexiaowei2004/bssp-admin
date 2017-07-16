package com.powerbridge.bssp.ems_bws.service.impl;

import com.powerbridge.bssp.ems_bws.entity.EmsBwsDt;
import com.powerbridge.bssp.ems_bws.dao.EmsBwsDtMapper;
import com.powerbridge.bssp.ems_bws.service.IEmsBwsDtService;
import com.baomidou.mybatisplus.plugins.Page;
import com.powerbridge.bssp.base.service.impl.BaseServiceImpl;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 物流账册备案申请明细 服务实现类
 * </p>
 *
 * @author huanliu
 * @since 2017-06-09
 */
@Service("emsBwsDtService")
public class EmsBwsDtServiceImpl extends BaseServiceImpl<EmsBwsDtMapper, EmsBwsDt> implements IEmsBwsDtService {
	
	@Autowired
    private EmsBwsDtMapper emsBwsDtMapper;
	@Override
	public Page<EmsBwsDt> selectEmsBwsDtList(Page<EmsBwsDt> page, EmsBwsDt emsBwsDt) throws Exception {
		 
		page.setRecords(emsBwsDtMapper.selectEmsBwsDtList(page, emsBwsDt));
	        return page;
	    }
	
}
