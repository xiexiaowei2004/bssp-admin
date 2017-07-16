package com.powerbridge.bssp.ems_bws.service.impl;

import com.baomidou.mybatisplus.plugins.Page;
import com.powerbridge.bssp.base.service.impl.BaseServiceImpl;
import com.powerbridge.bssp.ems_bws.dao.EmsBwsHisDtMapper;
import com.powerbridge.bssp.ems_bws.entity.EmsBwsHisDt;
import com.powerbridge.bssp.ems_bws.service.IEmsBwsHisDtService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 物流账册正式表明细 服务实现类
 * </p>
 *
 * @author jokylao
 * @since 2017-07-06
 */
@Service("emsBwsHisDtService")
public class EmsBwsHisDtServiceImpl extends BaseServiceImpl<EmsBwsHisDtMapper, EmsBwsHisDt> implements IEmsBwsHisDtService {

	@Autowired
    private EmsBwsHisDtMapper emsBwsHisDtMapper;
	
	
	@Override
	public Page<EmsBwsHisDt> selectEmsBwsHisDtList(Page<EmsBwsHisDt> page, EmsBwsHisDt emsBwsHisDt) throws Exception {
		page.setRecords(emsBwsHisDtMapper.selectEmsBwsHisDtList(page, emsBwsHisDt));
        return page;
    }
}
