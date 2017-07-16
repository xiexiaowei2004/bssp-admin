package com.powerbridge.bssp.inv.service;
import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.IService;
import com.powerbridge.bssp.inv.entity.InvDecDt;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author zsl
 * @since 2017-06-09
 */
public interface IInvDecDtService extends IService<InvDecDt> {

    Page<InvDecDt> selectByDeclare(Page<InvDecDt> page,InvDecDt invDecDt);
}
