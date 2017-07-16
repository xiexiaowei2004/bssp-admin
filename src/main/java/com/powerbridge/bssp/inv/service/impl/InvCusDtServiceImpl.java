package com.powerbridge.bssp.inv.service.impl;


import com.baomidou.mybatisplus.plugins.Page;
import com.powerbridge.bssp.base.service.impl.BaseServiceImpl;
import com.powerbridge.bssp.inv.dao.InvCusDtMapper;
import com.powerbridge.bssp.inv.entity.InvCusDt;
import com.powerbridge.bssp.inv.service.IInvCusDtService;
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
@Service("invCusDtService")
public class InvCusDtServiceImpl extends BaseServiceImpl<InvCusDtMapper, InvCusDt> implements IInvCusDtService {
	@Autowired
    private InvCusDtMapper invCusDtMapper;

    public Page<InvCusDt> selectByInvCusDt(Page<InvCusDt> page, InvCusDt invCusDt) {
        page.setRecords(invCusDtMapper.selectByInvCusDt(page,invCusDt));
        return page;
    }
}
