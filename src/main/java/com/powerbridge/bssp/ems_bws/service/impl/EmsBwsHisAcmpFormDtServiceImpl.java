package com.powerbridge.bssp.ems_bws.service.impl;

import com.baomidou.mybatisplus.plugins.Page;
import com.powerbridge.bssp.base.service.impl.BaseServiceImpl;
import com.powerbridge.bssp.ems_bws.dao.EmsBwsHisAcmpFormDtMapper;
import com.powerbridge.bssp.ems_bws.entity.EmsBwsHisAcmpFormDt;
import com.powerbridge.bssp.ems_bws.service.IEmsBwsHisAcmpFormDtService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 物流账册历史表随附单证明细 服务实现类
 * </p>
 *
 * @author jokylao
 * @since 2017-07-06
 */
@Service("emsBwsHisAcmpFormDtService")
public class EmsBwsHisAcmpFormDtServiceImpl extends BaseServiceImpl<EmsBwsHisAcmpFormDtMapper, EmsBwsHisAcmpFormDt> implements IEmsBwsHisAcmpFormDtService {

	 @Autowired
	    private EmsBwsHisAcmpFormDtMapper emsBwsHisAcmpFormDtMapper;
	
	
	@Override
	public Page<EmsBwsHisAcmpFormDt> selectEmsBwsHisAcmpFormDtList(Page<EmsBwsHisAcmpFormDt> page,EmsBwsHisAcmpFormDt EmsBwsHisAcmpFormDt){
   
		page.setRecords(emsBwsHisAcmpFormDtMapper.selectEmsBwsHisAcmpFormDtList(page, EmsBwsHisAcmpFormDt));
        return page;
    }
}
