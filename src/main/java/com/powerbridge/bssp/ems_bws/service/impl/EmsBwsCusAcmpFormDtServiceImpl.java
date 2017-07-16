package com.powerbridge.bssp.ems_bws.service.impl;


import com.powerbridge.bssp.ems_bws.entity.EmsBwsCusAcmpFormDt;

import com.powerbridge.bssp.ems_bws.dao.EmsBwsCusAcmpFormDtMapper;
import com.powerbridge.bssp.ems_bws.service.IEmsBwsCusAcmpFormDtService;
import com.baomidou.mybatisplus.plugins.Page;
import com.powerbridge.bssp.base.service.impl.BaseServiceImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 物流账册随附单证明细 服务实现类
 * </p>
 *
 * @author huanliu
 * @since 2017-06-09
 */
@Service("emsBwsCusAcmpFormDtService")
public class EmsBwsCusAcmpFormDtServiceImpl extends BaseServiceImpl<EmsBwsCusAcmpFormDtMapper, EmsBwsCusAcmpFormDt> implements IEmsBwsCusAcmpFormDtService {

	 @Autowired
	    private EmsBwsCusAcmpFormDtMapper emsBwsCusAcmpFormDtMapper;
	
	
	@Override
	public Page<EmsBwsCusAcmpFormDt> selectEmsBwsCusAcmpFormDtList(Page<EmsBwsCusAcmpFormDt> page,EmsBwsCusAcmpFormDt EmsBwsCusAcmpFormDt){
   
		page.setRecords(emsBwsCusAcmpFormDtMapper.selectEmsBwsCusAcmpFormDtList(page, EmsBwsCusAcmpFormDt));
        return page;
    }
}
