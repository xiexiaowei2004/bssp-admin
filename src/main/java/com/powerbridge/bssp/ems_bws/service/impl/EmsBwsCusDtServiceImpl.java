package com.powerbridge.bssp.ems_bws.service.impl;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.powerbridge.bssp.common.util.UUIDGenerator;
import com.powerbridge.bssp.ems_bws.entity.EmsBwsCusDt;
import com.powerbridge.bssp.ems_bws.dao.EmsBwsCusDtMapper;
import com.powerbridge.bssp.ems_bws.entity.EmsBwsDt;
import com.powerbridge.bssp.ems_bws.service.IEmsBwsCusDtService;
import com.baomidou.mybatisplus.plugins.Page;
import com.powerbridge.bssp.base.service.impl.BaseServiceImpl;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 * 物流账册正式表明细 服务实现类
 * </p>
 *
 * @author huanliu
 * @since 2017-06-09
 */
@Service("emsBwsCusDtService")
public class EmsBwsCusDtServiceImpl extends BaseServiceImpl<EmsBwsCusDtMapper, EmsBwsCusDt> implements IEmsBwsCusDtService {

	@Autowired
    private EmsBwsCusDtMapper emsBwsCusDtMapper;
	
	
	@Override
	public Page<EmsBwsCusDt> selectEmsBwsCusDtList(Page<EmsBwsCusDt> page, EmsBwsCusDt emsBwsCusDt) throws Exception {
		page.setRecords(emsBwsCusDtMapper.selectEmsBwsCusDtList(page, emsBwsCusDt));
        return page;
    }
	/**
	 * 根据账册编号，获取账册表体
	 * @param bwsNo
	 * @return
	 */
	@Override
	public List<EmsBwsDt> getEmsBwsDtChgData(String bwsNo) {
        EntityWrapper entityWrapper = new EntityWrapper();
        entityWrapper.eq("BWS_NO", bwsNo);
        List<EmsBwsDt> emsBwsDtList = emsBwsCusDtMapper.selectList(entityWrapper);
        if (emsBwsDtList.size() == 0)
            return emsBwsDtList;
        List<EmsBwsCusDt> emsBwsCusDtList = null;
        BeanUtils.copyProperties(emsBwsCusDtList, emsBwsDtList);
        for (EmsBwsDt emsBwsDt : emsBwsDtList) {
            String uId = UUIDGenerator.getUUID();
            emsBwsDt.setUid(uId);//主键
        }
        return emsBwsDtList;
    }
}
