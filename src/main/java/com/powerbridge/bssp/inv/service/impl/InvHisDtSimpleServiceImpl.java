package com.powerbridge.bssp.inv.service.impl;


import com.baomidou.mybatisplus.plugins.Page;
import com.powerbridge.bssp.base.service.impl.BaseServiceImpl;
import com.powerbridge.bssp.inv.dao.InvHisDtSimpleMapper;
import com.powerbridge.bssp.inv.entity.InvHisDtSimple;
import com.powerbridge.bssp.inv.service.IInvHisDtSimpleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 简单加工成品明细 服务实现类
 * </p>
 *
 * @author zsl
 * @since 2017-07-05
 */
@Service("invHisDtSimpleService")
public class InvHisDtSimpleServiceImpl extends BaseServiceImpl<InvHisDtSimpleMapper, InvHisDtSimple> implements IInvHisDtSimpleService {
    @Autowired
    private  InvHisDtSimpleMapper invHisDtSimpleMapper;
    public Page<InvHisDtSimple> selectByInvHisDtSimple(Page<InvHisDtSimple> page, InvHisDtSimple invHisDtSimple) {
        page.setRecords(invHisDtSimpleMapper.selectByInvHisDt(page,invHisDtSimple));
        return page;
    }
}
