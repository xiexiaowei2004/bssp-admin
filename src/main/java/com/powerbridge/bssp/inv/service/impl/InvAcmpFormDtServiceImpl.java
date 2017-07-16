package com.powerbridge.bssp.inv.service.impl;


import com.baomidou.mybatisplus.plugins.Page;
import com.powerbridge.bssp.base.service.impl.BaseServiceImpl;
import com.powerbridge.bssp.inv.dao.InvAcmpFormDtMapper;
import com.powerbridge.bssp.inv.entity.InvAcmpFormDt;
import com.powerbridge.bssp.inv.service.IInvAcmpFormDtService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 保税核注清单随附单证明细表 服务实现类
 * </p>
 *
 * @author zsl
 * @since 2017-06-20
 */
@Service("invAcmpFormDtService")
public class InvAcmpFormDtServiceImpl extends BaseServiceImpl<InvAcmpFormDtMapper, InvAcmpFormDt> implements IInvAcmpFormDtService {
    @Autowired
    private InvAcmpFormDtMapper invAcmpFormDtMapper;

    public Page<InvAcmpFormDt> selectByInvAcmpList(Page<InvAcmpFormDt>page, String seqNo){
        page.setRecords(invAcmpFormDtMapper.selectByList(page,seqNo));
        return page;
    }
	
}
