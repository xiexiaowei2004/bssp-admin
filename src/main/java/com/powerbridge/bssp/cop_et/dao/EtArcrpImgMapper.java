package com.powerbridge.bssp.cop_et.dao;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.baomidou.mybatisplus.plugins.pagination.Pagination;
import com.powerbridge.bssp.cop_et.entity.EtArcrpImg;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

/**
 * 联网企业档案库料件 Mapper 接口
 *
 * @author willChen
 * @since 2017-05-22
 */
@Repository("etArcrpImgMapper")
public interface EtArcrpImgMapper extends BaseMapper<EtArcrpImg> {

    List<EtArcrpImg> selectEtArcrpImgList(Pagination page, EtArcrpImg etArcrpImg);

    /**
     * 获取最大的成品序号
     *
     * @param map :seqNo 统一编号 chgTmsCnt 变更次数
     * @return
     */
    Integer getMaxGdsSeqno(Map map);
}