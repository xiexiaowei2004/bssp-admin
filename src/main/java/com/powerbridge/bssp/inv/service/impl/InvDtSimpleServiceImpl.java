package com.powerbridge.bssp.inv.service.impl;


import com.baomidou.mybatisplus.plugins.Page;
import com.powerbridge.bssp.base.service.impl.BaseServiceImpl;
import com.powerbridge.bssp.inv.dao.InvDtSimpleMapper;
import com.powerbridge.bssp.inv.entity.InvDtSimple;
import com.powerbridge.bssp.inv.service.IInvDtSimpleService;
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
@Service("invDtSimpleService")
public class InvDtSimpleServiceImpl extends BaseServiceImpl<InvDtSimpleMapper, InvDtSimple> implements IInvDtSimpleService {
    @Autowired
    private InvDtSimpleMapper invDtSimpleMapper;

    public Page<InvDtSimple> selectByInvDtSimple(Page<InvDtSimple>page, InvDtSimple invDtSimple){
        page.setRecords(invDtSimpleMapper.selectByInvDtSimple(page,invDtSimple));
        return  page;
    }
	
}
