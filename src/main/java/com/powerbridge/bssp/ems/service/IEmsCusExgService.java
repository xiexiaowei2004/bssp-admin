package com.powerbridge.bssp.ems.service;

import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.IService;
import com.powerbridge.bssp.ems.entity.EmsCusExg;
import com.powerbridge.bssp.ems.entity.EmsPutrecExg;

import java.math.BigDecimal;
import java.util.List;

/**
 * 项目名称：bssp-admin
 * 类名称：IEmsCusExgService
 * 类描述：账册备案正式表成品服务
 * 创建人：willChen
 * 创建时间：2017/5/16 22:00
 * 修改人：jokylao
 * 修改时间：2017/6/4 11:23
 */
public interface IEmsCusExgService extends IService<EmsCusExg> {
    /**
     * 查询加工账册备案成品/分页
     *
     * @param page 分页
     * @param emsPutrecExg
     * @return List<EmsCusExg>
     */
    Page<EmsCusExg> selectEmsCusExgList(Page<EmsCusExg> page, EmsCusExg emsPutrecExg);
    /**
     * 获取最大的商品序号
     *
     * @return 账册编号
     */
    BigDecimal getMaxGdsSeqno(String emsNo);
    /**
     * 复制账册成品信息
     *
     * @param emsCusExgList
     * @return
     */
    List<EmsCusExg> copyGds(List<EmsCusExg> emsCusExgList);
    /**
     * 判断料件序号是否存在
     * @param emsNo
     * @param gdsSeqno
     * @return
     */
    Boolean isExistGdsSeqno(String emsNo,BigDecimal gdsSeqno);
    /**
     * 选择成品商品插入料件申报表中
     * @param seqNo
     * @param chgTmsCnt
     * @param idList
     * @return
     */
    List<EmsPutrecExg> getPreImgFromCus(String seqNo, BigDecimal chgTmsCnt, String idList);
}
