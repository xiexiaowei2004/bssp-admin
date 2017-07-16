package com.powerbridge.bssp.ems.service.impl;

import com.baomidou.mybatisplus.plugins.Page;
import com.powerbridge.bssp.base.service.impl.BaseServiceImpl;
import com.powerbridge.bssp.ems.dao.EmsHisAcmpFormDtMapper;
import com.powerbridge.bssp.ems.entity.EmsHisAcmpFormDt;
import com.powerbridge.bssp.ems.service.IEmsHisAcmpFormDtService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 项目名称：bssp-admin
 * 类名称：EmsHisAcmpFormDtServiceImpl
 * 类描述：账册历史表随附单证服务实现
 * 创建人：jokylao
 * 创建时间：2017/7/5
 */
@Service("emsHisAcmpFormDtService")
public class EmsHisAcmpFormDtServiceImpl extends BaseServiceImpl<EmsHisAcmpFormDtMapper, EmsHisAcmpFormDt> implements IEmsHisAcmpFormDtService {
    @Autowired
    private EmsHisAcmpFormDtMapper emsHisAcmpFormDtMapper;
    /**
     * 查询加工账册历史表随附单证/分页
     *
     * @param page 分页
     * @param emsHisAcmpFormDt
     * @return Page<SasVehicleBsc>
     */
    public Page<EmsHisAcmpFormDt> selectEmsHisAcmpFormDtList(Page<EmsHisAcmpFormDt> page, EmsHisAcmpFormDt emsHisAcmpFormDt){
        page.setRecords(emsHisAcmpFormDtMapper.selectEmsHisAcmpFormDtList(page, emsHisAcmpFormDt));
        return page;
    }
}
