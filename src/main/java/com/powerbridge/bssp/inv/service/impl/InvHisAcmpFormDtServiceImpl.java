package com.powerbridge.bssp.inv.service.impl;


import com.baomidou.mybatisplus.plugins.Page;
import com.powerbridge.bssp.base.service.impl.BaseServiceImpl;
import com.powerbridge.bssp.inv.dao.InvAcmpFormDtMapper;
import com.powerbridge.bssp.inv.dao.InvHisAcmpFormDtMapper;
import com.powerbridge.bssp.inv.entity.InvCusAcmpFormDt;
import com.powerbridge.bssp.inv.entity.InvHisAcmpFormDt;
import com.powerbridge.bssp.inv.service.IInvHisAcmpFormDtService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author zsl
 * @since 2017-07-05
 */
@Service("invHisAcmpFormDtService")
public class InvHisAcmpFormDtServiceImpl extends BaseServiceImpl<InvHisAcmpFormDtMapper, InvHisAcmpFormDt> implements IInvHisAcmpFormDtService {
    @Autowired
    InvHisAcmpFormDtMapper invHisAcmpFormDtMapper;
    public Page<InvHisAcmpFormDt> selectByInvHisAcmpList(Page<InvHisAcmpFormDt> page, String seqNo) {
        page.setRecords(invHisAcmpFormDtMapper.selectByList(page,seqNo));
        return page;
    }
}
