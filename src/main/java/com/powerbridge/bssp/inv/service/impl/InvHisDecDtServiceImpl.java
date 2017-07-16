package com.powerbridge.bssp.inv.service.impl;


import com.baomidou.mybatisplus.plugins.Page;
import com.powerbridge.bssp.base.service.impl.BaseServiceImpl;
import com.powerbridge.bssp.inv.dao.InvHisDecDtMapper;
import com.powerbridge.bssp.inv.entity.InvHisDecDt;
import com.powerbridge.bssp.inv.service.IInvHisDecDtService;
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
@Service("invHisDecDtService")
public class InvHisDecDtServiceImpl extends BaseServiceImpl<InvHisDecDtMapper, InvHisDecDt> implements IInvHisDecDtService {
    @Autowired
    InvHisDecDtMapper invHisDecDtMapper;
    public Page<InvHisDecDt> selectByHisDeclare(Page<InvHisDecDt> page, InvHisDecDt invHisDecDt) {
        page.setRecords(invHisDecDtMapper.selectByInvHisDeclare(page,invHisDecDt));
        return page;
    }
}
