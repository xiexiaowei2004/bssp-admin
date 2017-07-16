package com.powerbridge.bssp.cop_et.service;

import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.IService;
import com.powerbridge.bssp.cod_cus.entity.CodCusComplex;
import com.powerbridge.bssp.cop_et.entity.EtArcrpExg;

import java.util.List;

/**
 * 联网企业档案库成品 服务类
 *
 * @author willChen
 * @since 2017-05-22
 */
public interface IEtArcrpExgService extends IService<EtArcrpExg> {

    /**
     * 根据条件查询表数据
     *
     * @param page
     * @param etArcrpExg
     * @return
     * @throws Exception
     */
    Page<EtArcrpExg> selectEtArcrpExgList(Page<EtArcrpExg> page, EtArcrpExg etArcrpExg) throws Exception;

    /**
     * 选取新增数据
     *
     * @param etArcrpExg
     * @param codCusComplexList
     * @return
     * @throws Exception
     */
    Integer chooseAddEtArcrpExg(EtArcrpExg etArcrpExg, List<CodCusComplex> codCusComplexList) throws Exception;

    /**
     * 获取最大的成品序号
     *
     * @param seqNo 统一编号
     * @param chgTmsCnt 变更次数
     * @return
     */
    Integer getMaxGdsSeqno(String seqNo, Integer chgTmsCnt);

    /**
     * 删除新增的成品并对其他新增商品重新排序
     *
     * @param uid
     * @param etArcrpExgList
     * @return
     */
    boolean deleteAdd(String uid, List<EtArcrpExg> etArcrpExgList);

}
