package com.powerbridge.bssp.sas.service.impl;


import com.baomidou.mybatisplus.plugins.Page;
import com.powerbridge.bssp.base.service.impl.BaseServiceImpl;
import com.powerbridge.bssp.sas.dao.SasVehicleCusMapper;
import com.powerbridge.bssp.sas.entity.SasVehicleCus;
import com.powerbridge.bssp.sas.service.ISasVehicleCusService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 项目名称：bssp Maven Webapp
 * 类名称：SasVehicleCusServiceImpl
 * 类描述：车辆信息备案正式表业务逻辑层接口 实现类
 * 创建人：xuzuotao
 * 创建时间：2017年5月19日 下午10:55:23
 */
@Service("sasVehicleCusService")
public class SasVehicleCusServiceImpl extends BaseServiceImpl<SasVehicleCusMapper, SasVehicleCus> implements ISasVehicleCusService {

    @Autowired
    private SasVehicleCusMapper sasVehicleCusMapper;

    /**
     * 查询车辆备案信息申请表/分页
     *
     * @param page          分页
     * @param sasVehicleCus
     * @return Page<SasVehicleCus>
     */
    public Page<SasVehicleCus> selectByVehicleCus(Page<SasVehicleCus> page, SasVehicleCus sasVehicleCus) {
        page.setRecords(sasVehicleCusMapper.selectByVehicleCus(page, sasVehicleCus));
        return page;
    }

    @Override
    public SasVehicleCus getBusinessData(String str) {
        return sasVehicleCusMapper.getBusinessData(str);
    }
}
