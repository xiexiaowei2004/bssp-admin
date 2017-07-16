package com.powerbridge.bssp.inv.service.impl;


import com.baomidou.mybatisplus.plugins.Page;
import com.powerbridge.bssp.base.service.impl.BaseServiceImpl;
import com.powerbridge.bssp.inv.dao.InvHisDtMapper;
import com.powerbridge.bssp.inv.entity.InvHisDt;
import com.powerbridge.bssp.inv.service.IInvHisDtService;
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
@Service("invHisDtService")
public class InvHisDtServiceImpl extends BaseServiceImpl<InvHisDtMapper, InvHisDt> implements IInvHisDtService {

    @Autowired
    InvHisDtMapper invHisDtMapper;
    public Page<InvHisDt> selectByInvHisDt(Page<InvHisDt> page, InvHisDt invHisDt) {
        page.setRecords(invHisDtMapper.selectByInvHisDt(page,invHisDt));
        return page;
    }
}
