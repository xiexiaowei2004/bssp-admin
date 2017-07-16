package com.powerbridge.bssp.sas.service.impl;


import com.baomidou.mybatisplus.plugins.Page;
import com.powerbridge.bssp.base.service.impl.BaseServiceImpl;
import com.powerbridge.bssp.dcr.entity.DcrChgoffBsc;
import com.powerbridge.bssp.sas.dao.SasVehicleBscMapper;
import com.powerbridge.bssp.sas.entity.SasVehicleBsc;
import com.powerbridge.bssp.sas.service.ISasVehicleBscService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 项目名称：bssp Maven Webapp
 * 类名称：SasVehicleBscServiceImpl
 * 类描述：车辆信息备案申请表业务逻辑层接口 实现类
 * 创建人：xuzuotao
 * 创建时间：2017年5月19日 下午10:55:23
 */
@Service("sasVehicleBscService")
public class SasVehicleBscServiceImpl extends BaseServiceImpl<SasVehicleBscMapper, SasVehicleBsc> implements ISasVehicleBscService {

    @Autowired
    private SasVehicleBscMapper sasVehicleBscMapper;
    /**
     * 查询车辆备案信息申请表/分页
     *
     * @param page 分页
     * @param sasVehicleBsc
     * @return Page<SasVehicleBsc>
     */
    public Page<SasVehicleBsc> selectByVehicleBsc(Page<SasVehicleBsc> page, SasVehicleBsc sasVehicleBsc){
        page.setRecords(sasVehicleBscMapper.selectByVehicleBsc(page, sasVehicleBsc));
        return page;
    }
	@Override
	public SasVehicleBsc getBusinessData(String str) {
		return sasVehicleBscMapper.getBusinessData(str);
	}
}
