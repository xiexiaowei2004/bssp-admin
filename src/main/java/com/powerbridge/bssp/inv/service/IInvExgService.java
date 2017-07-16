package com.powerbridge.bssp.inv.service;

import com.baomidou.mybatisplus.service.IService;
import com.powerbridge.bssp.inv.entity.InvExg;


import java.util.List;

/**
 * <p>
 * 保税核注清单成品 服务类
 * </p>
 *
 * @author zsl
 * @since 2017-06-03
 */
public interface IInvExgService extends IService<InvExg> {


    /**
 * @param seqNo      String 单据编号
 * @param invExgList List<InvExg> 报关商品数据
 * @param orgFlag    String 归并方式
 * @return boolean
 * @throws
 * @Description: 用与生成报关商品数据
 */
boolean txInsertByInvExg(List<InvExg> invExgList, String seqNo, String orgFlag);

    /**
     * @param map   String
     * @return List<InvImg>
     * @throws
     * @Description:自动合并调整报关单序号
     */
    List<InvExg> autoGetInvExgList();

}
