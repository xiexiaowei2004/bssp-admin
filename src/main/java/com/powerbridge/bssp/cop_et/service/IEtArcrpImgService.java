package com.powerbridge.bssp.cop_et.service;

import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.IService;
import com.powerbridge.bssp.cod_cus.entity.CodCusComplex;
import com.powerbridge.bssp.cop_et.entity.EtArcrpImg;

import java.util.List;

/**
 * 联网企业档案库料件 服务类
 *
 * @author willChen
 * @since 2017-05-22
 */
public interface IEtArcrpImgService extends IService<EtArcrpImg> {

    /**
     * 根据条件查询表数据
     *
     * @param page
     * @param etArcrpImg
     * @return
     * @throws Exception
     */
    Page<EtArcrpImg> selectEtArcrpImgList(Page<EtArcrpImg> page, EtArcrpImg etArcrpImg) throws Exception;

    /**
     * 选取新增数据
     *
     * @param etArcrpImg
     * @param codCusComplexList
     * @return
     * @throws Exception
     */
    Integer chooseAddEtArcrpImg(EtArcrpImg etArcrpImg, List<CodCusComplex> codCusComplexList) throws Exception;

    /**
     * 获取最大的料件序号
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
     * @param etArcrpImgList
     * @return
     */
    boolean deleteAdd(String uid, List<EtArcrpImg> etArcrpImgList);

}
