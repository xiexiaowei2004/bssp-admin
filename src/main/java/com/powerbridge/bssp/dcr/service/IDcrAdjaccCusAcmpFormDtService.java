package com.powerbridge.bssp.dcr.service;

import com.baomidou.mybatisplus.plugins.Page;
import com.powerbridge.bssp.dcr.entity.DcrAdjaccCusAcmpFormDt;
import com.baomidou.mybatisplus.service.IService;

/**
 * <p>
 * 账册核销随附单证明细正式表 服务类
 * </p>
 *
 * @author haihuihuang
 * @since 2017-07-03
 */
public interface IDcrAdjaccCusAcmpFormDtService extends IService<DcrAdjaccCusAcmpFormDt> {

    /**
     * 查询 分页
     *
     * @param page  分页
     * @param seqNo 单据编号
     * @return Page<DcrAdjaccCusAcmpFormDt>
     */
    Page<DcrAdjaccCusAcmpFormDt> selectByList(Page<DcrAdjaccCusAcmpFormDt> page, String seqNo);
}
