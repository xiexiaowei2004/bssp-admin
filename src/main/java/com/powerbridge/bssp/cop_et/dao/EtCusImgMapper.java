package com.powerbridge.bssp.cop_et.dao;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.baomidou.mybatisplus.plugins.pagination.Pagination;
import com.powerbridge.bssp.cop_et.entity.EtCusImg;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 联网企业档案库正式表料件 Mapper 接口
 *
 * @author willChen
 * @since 2017-06-01
 */
@Repository("etCusImgMapper")
public interface EtCusImgMapper extends BaseMapper<EtCusImg> {

    List<EtCusImg> selectEtCusImgList(Pagination page, EtCusImg etCusImg);

    /**
     * 获取最大的料件序号
     *
     * @param seqNo 统一编号
     * @return
     */
    Integer getMaxGdsSeqno(String seqNo);
}