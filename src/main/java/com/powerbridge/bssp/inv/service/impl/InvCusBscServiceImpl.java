package com.powerbridge.bssp.inv.service.impl;


import com.baomidou.mybatisplus.plugins.Page;
import com.powerbridge.bssp.base.service.impl.BaseServiceImpl;
import com.powerbridge.bssp.inv.dao.InvCusBscMapper;
import com.powerbridge.bssp.inv.entity.InvCusBsc;
import com.powerbridge.bssp.inv.service.IInvCusBscService;
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
@Service("invCusBscService")
public class InvCusBscServiceImpl extends BaseServiceImpl<InvCusBscMapper, InvCusBsc> implements IInvCusBscService {
    @Autowired
    InvCusBscMapper invCusBscMapper;
    public Page<InvCusBsc> selectByInvCusBsc(Page<InvCusBsc> page, InvCusBsc invCusBsc) {
        page.setRecords(invCusBscMapper.selectByInvCusBsc(page,invCusBsc));
        return page;
    }
}
