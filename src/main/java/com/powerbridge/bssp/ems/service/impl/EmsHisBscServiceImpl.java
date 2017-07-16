package com.powerbridge.bssp.ems.service.impl;

import com.baomidou.mybatisplus.plugins.Page;
import com.powerbridge.bssp.base.service.impl.BaseServiceImpl;
import com.powerbridge.bssp.ems.dao.EmsHisBscMapper;
import com.powerbridge.bssp.ems.entity.EmsHisBsc;
import com.powerbridge.bssp.ems.service.IEmsHisBscService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 项目名称：bssp-admin
 * 类名称：EmsCusBscServiceImpl
 * 类描述：账册备案正式表服务实现
 * 创建人：willChen
 * 创建时间：2017/7/422:18
 */
@Service("emsHisBscService")
public class EmsHisBscServiceImpl extends BaseServiceImpl<EmsHisBscMapper, EmsHisBsc> implements IEmsHisBscService {
    @Autowired
    private EmsHisBscMapper emsHisBscMapper;
    /**
     * 查询车辆备案信息申请表/分页
     *
     * @param page 分页
     * @param emsHisBsc
     * @return Page<SasVehicleBsc>
     */
    public Page<EmsHisBsc> selectEmsHisBscList(Page<EmsHisBsc> page, EmsHisBsc emsHisBsc){
        page.setRecords(emsHisBscMapper.selectEmsHisBscList(page, emsHisBsc));
        return page;
    }
}
