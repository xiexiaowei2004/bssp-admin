package com.powerbridge.bssp.ems.service;

import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.IService;
import com.powerbridge.bssp.ems.entity.EmsPutrecAcmpFormDt;

import java.math.BigDecimal;

/**
 * 项目名称：bssp-admin
 * 类名称：IEmsPutrecAcmpFormDtService
 * 类描述：账册备案申请附件服务
 * 创建人：willChen
 * 创建时间：2017/5/16 22:00
 * 修改人：willChen
 * 修改时间：2017/5/16 22:00
 */
public interface IEmsPutrecAcmpFormDtService extends IService<EmsPutrecAcmpFormDt> {
    /**
     * 查询加工账册备案随单附证/分页
     *
     * @param page 分页
     * @param emsPutrecAcmpFormDt
     * @return List<EmsPutrecAcmpFormDt>
     */
    Page<EmsPutrecAcmpFormDt> selectEmsPutrecAcmpFormDtList(Page<EmsPutrecAcmpFormDt> page, EmsPutrecAcmpFormDt emsPutrecAcmpFormDt);
    /**
     * 获取最大的序号
     * @param seqNo 单据编号
     * @return
     */
    BigDecimal getMaxAcmpFormDtSeqno(String seqNo);
}
