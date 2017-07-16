package com.powerbridge.bssp.inv.service.impl;


import com.baomidou.mybatisplus.plugins.Page;
import com.powerbridge.bssp.base.service.impl.BaseServiceImpl;
import com.powerbridge.bssp.inv.dao.InvCusDecDtMapper;
import com.powerbridge.bssp.inv.entity.InvCusDecDt;
import com.powerbridge.bssp.inv.service.IInvCusDecDtService;
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
@Service("invCusDecDtService")
public class InvCusDecDtServiceImpl extends BaseServiceImpl<InvCusDecDtMapper, InvCusDecDt> implements IInvCusDecDtService {
    @Autowired
    InvCusDecDtMapper invCusDecDtMapper;
    public Page<InvCusDecDt> selectByCusDeclare(Page<InvCusDecDt> page, InvCusDecDt invCusDecDt) {
        page.setRecords(invCusDecDtMapper.selectByInvCusDeclare(page,invCusDecDt));
        return page;
    }
}
