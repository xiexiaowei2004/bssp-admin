package com.powerbridge.bssp.inv.service.impl;


import com.baomidou.mybatisplus.plugins.Page;
import com.powerbridge.bssp.base.service.impl.BaseServiceImpl;
import com.powerbridge.bssp.inv.dao.InvCusAcmpFormDtMapper;
import com.powerbridge.bssp.inv.entity.InvCusAcmpFormDt;
import com.powerbridge.bssp.inv.service.IInvCusAcmpFormDtService;
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
@Service("invCusAcmpFormDtService")
public class InvCusAcmpFormDtServiceImpl extends BaseServiceImpl<InvCusAcmpFormDtMapper, InvCusAcmpFormDt> implements IInvCusAcmpFormDtService {
    @Autowired
    InvCusAcmpFormDtMapper invCusAcmpFormDtMapper;
    public Page<InvCusAcmpFormDt> selectByInvCusAcmpList(Page<InvCusAcmpFormDt> page, String seqNo) {
        page.setRecords(invCusAcmpFormDtMapper.selectByList(page,seqNo));
        return page;
    }
}
