package com.powerbridge.bssp.ems_bws.service.impl;


import com.baomidou.mybatisplus.plugins.Page;
import com.powerbridge.bssp.base.service.impl.BaseServiceImpl;
import com.powerbridge.bssp.ems_bws.dao.EmsBwsAcmpFormDtMapper;
import com.powerbridge.bssp.ems_bws.entity.EmsBwsAcmpFormDt;
import com.powerbridge.bssp.ems_bws.service.IEmsBwsAcmpFormDtService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 账册备案正式表随附单证 服务实现类
 * </p>
 *
 * @author huanliu
 * @since 2017-05-25
 */
@Service("emsBwsAcmpFormDtService")
public class EmsBwsAcmpFormDtServiceImpl extends BaseServiceImpl<EmsBwsAcmpFormDtMapper, EmsBwsAcmpFormDt> implements IEmsBwsAcmpFormDtService {
	
	 @Autowired
	    private EmsBwsAcmpFormDtMapper emsBwsAcmpFormDtMapper;
	    /**
	     * 查询加工账册备案随单附证/分页
	     *
	     * @param page 分页
	     * @param emsBwsAcmpFormDt
	     * @return Page<SasVehicleBsc>
	     */
	    public Page<EmsBwsAcmpFormDt> selectEmsBwsAcmpFormDtList(Page<EmsBwsAcmpFormDt> page, EmsBwsAcmpFormDt emsBwsAcmpFormDt){
	        page.setRecords(emsBwsAcmpFormDtMapper.selectEmsBwsAcmpFormDtList(page, emsBwsAcmpFormDt));
	        return page;
	    }
}
