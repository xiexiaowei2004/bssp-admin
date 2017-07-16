package com.powerbridge.bssp.dcr.dao;

import com.baomidou.mybatisplus.plugins.Page;
import com.powerbridge.bssp.dcr.entity.DcrAdjaccCusAcmpFormDt;
import com.baomidou.mybatisplus.mapper.BaseMapper;

import java.util.List;

/**
 * <p>
  * 账册核销随附单证明细正式表 Mapper 接口
 * </p>
 *
 * @author haihuihuang
 * @since 2017-07-04
 */
public interface DcrAdjaccCusAcmpFormDtMapper extends BaseMapper<DcrAdjaccCusAcmpFormDt> {

    /**
     * 查询 分页
     *
     * @param page  分页
     * @param seqNo 单据编号
     * @return List<DcrAdjaccCusAcmpFormDt>
     */
    List<DcrAdjaccCusAcmpFormDt> selectByList(Page<DcrAdjaccCusAcmpFormDt> page, String seqNo);
}