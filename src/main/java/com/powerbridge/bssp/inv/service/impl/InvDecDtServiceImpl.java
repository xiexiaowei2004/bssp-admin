package com.powerbridge.bssp.inv.service.impl;

import com.baomidou.mybatisplus.plugins.Page;
import com.powerbridge.bssp.base.service.impl.BaseServiceImpl;
import com.powerbridge.bssp.inv.dao.InvDecDtMapper;
import com.powerbridge.bssp.inv.entity.InvDecDt;
import com.powerbridge.bssp.inv.service.IInvDecDtService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author zsl
 * @since 2017-06-09
 */
@Service("invDecDtService")
public class InvDecDtServiceImpl extends BaseServiceImpl<InvDecDtMapper, InvDecDt> implements IInvDecDtService {
    @Autowired
    private InvDecDtMapper invDecDtMapper;

    public Page<InvDecDt> selectByDeclare(Page<InvDecDt> page, InvDecDt invDecDt){
        page.setRecords(invDecDtMapper.selectByInvDeclare(page,invDecDt));
        return page;
    }
}
