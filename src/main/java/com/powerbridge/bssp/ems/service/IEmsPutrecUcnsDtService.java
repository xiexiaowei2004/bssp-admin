package com.powerbridge.bssp.ems.service;

import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.IService;
import com.powerbridge.bssp.ems.entity.EmsPutrecUcnsDt;

import java.math.BigDecimal;
import java.util.List;

/**
 * 项目名称：bssp-admin
 * 类名称：IEmsPutrecUcnsDtService
 * 类描述：账册备案申请损耗服务
 * 创建人：willChen
 * 创建时间：2017/5/16 22:00
 * 修改人：willChen
 * 修改时间：2017/5/16 22:00
 */
public interface IEmsPutrecUcnsDtService extends IService<EmsPutrecUcnsDt> {
    /**
     * 查询加工账册备案单损耗/分页
     *
     * @param page            分页
     * @param emsPutrecUcnsDt
     * @return List<EmsPutrecUcnsDt>
     */
    Page<EmsPutrecUcnsDt> selectEmsPutrecUcnsDtList(Page<EmsPutrecUcnsDt> page, EmsPutrecUcnsDt emsPutrecUcnsDt);

    /**
     * 判断序号是否存在
     *
     * @param emsPutrecUcnsDt
     * @return Boolean
     */
    boolean isExistGdsSeqno(EmsPutrecUcnsDt emsPutrecUcnsDt);

    /**
     * 获取最大的序号
     *
     * @return 单据编号
     */
    BigDecimal getMaxUcnsSeqno(String seqNo);

    /**
     * 根据主键查找单耗数据
     *
     * @param uid
     * @return
     */
    EmsPutrecUcnsDt selectByUid(String uid);

    /**
     * 检查必填项
     *
     * @param emsPutrecUcnsDtList
     * @return
     */
    String checkListEmpty(List<EmsPutrecUcnsDt> emsPutrecUcnsDtList);
    /**
     * 批量插入时，判断数据是否合法
     * @param emsPutrecUcnsDtList
     * @return
     */
    String checkBeforeInsert(List<EmsPutrecUcnsDt> emsPutrecUcnsDtList);
}
