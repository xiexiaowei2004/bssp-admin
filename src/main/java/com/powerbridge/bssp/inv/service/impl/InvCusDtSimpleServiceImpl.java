package com.powerbridge.bssp.inv.service.impl;


import com.baomidou.mybatisplus.plugins.Page;
import com.powerbridge.bssp.base.service.impl.BaseServiceImpl;
import com.powerbridge.bssp.inv.dao.InvCusDtSimpleMapper;
import com.powerbridge.bssp.inv.entity.InvCusDtSimple;
import com.powerbridge.bssp.inv.service.IInvCusDtSimpleService;
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
@Service("invCusDtSimpleService")
public class InvCusDtSimpleServiceImpl extends BaseServiceImpl<InvCusDtSimpleMapper, InvCusDtSimple> implements IInvCusDtSimpleService {
    @Autowired
    private InvCusDtSimpleMapper invCusDtSimpleMapper;
    public Page<InvCusDtSimple> selectByInvCusDtSimple(Page<InvCusDtSimple> page, InvCusDtSimple invCusDtSimple) {
        page.setRecords(invCusDtSimpleMapper.selectByInvCusDt(page,invCusDtSimple));
        return page;
    }
}
