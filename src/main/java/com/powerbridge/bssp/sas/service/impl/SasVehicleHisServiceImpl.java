package com.powerbridge.bssp.sas.service.impl;


import com.baomidou.mybatisplus.plugins.Page;
import com.powerbridge.bssp.base.service.impl.BaseServiceImpl;
import com.powerbridge.bssp.sas.dao.SasVehicleHisMapper;
import com.powerbridge.bssp.sas.entity.SasVehicleHis;
import com.powerbridge.bssp.sas.service.ISasVehicleHisService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 项目名称：bssp Maven Webapp
 * 类名称：SasVehicleHisServiceImpl
 * 类描述：车辆信息备案历史记录表业务逻辑层接口 实现类
 * 创建人：xuzuotao
 * 创建时间：2017年5月19日 下午10:55:23
 */
@Service("sasVehicleHisService")
public class SasVehicleHisServiceImpl extends BaseServiceImpl<SasVehicleHisMapper, SasVehicleHis> implements ISasVehicleHisService {

    @Autowired
    private SasVehicleHisMapper sasVehicleHisMapper;

    /**
     * 查询车辆备案信息申请表/分页
     *
     * @param page          分页
     * @param sasVehicleHis
     * @return Page<SasVehicleHis>
     */
    public Page<SasVehicleHis> selectByVehicleHis(Page<SasVehicleHis> page, SasVehicleHis sasVehicleHis) {
        page.setRecords(sasVehicleHisMapper.selectByVehicleHis(page, sasVehicleHis));
        return page;
    }

    @Override
    public SasVehicleHis getBusinessData(String str) {
        return sasVehicleHisMapper.getBusinessData(str);
    }
}
