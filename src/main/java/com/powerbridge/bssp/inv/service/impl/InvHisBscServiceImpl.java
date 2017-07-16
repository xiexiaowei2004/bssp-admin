package com.powerbridge.bssp.inv.service.impl;


import com.baomidou.mybatisplus.plugins.Page;
import com.powerbridge.bssp.base.service.impl.BaseServiceImpl;
import com.powerbridge.bssp.inv.dao.InvHisBscMapper;
import com.powerbridge.bssp.inv.entity.InvHisBsc;
import com.powerbridge.bssp.inv.service.IInvHisBscService;
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
@Service("invHisBscService")
public class InvHisBscServiceImpl extends BaseServiceImpl<InvHisBscMapper, InvHisBsc> implements IInvHisBscService {
    @Autowired
    InvHisBscMapper invHisBscMapper;
    public Page<InvHisBsc> selectByInvHisBsc(Page<InvHisBsc> page, InvHisBsc invHisBsc) {
        page.setRecords(invHisBscMapper.selectByInvHisBsc(page,invHisBsc));
        return page;
    }
}
