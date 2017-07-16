package com.powerbridge.bssp.dcr.service;

import com.powerbridge.bssp.dcr.entity.DcrAdjaccHisAcmpFormDt;
import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.IService;

/**
 * <p>
 * 账册核销随附单证明细历史表 服务类
 * </p>
 *
 * @author haihuihuang
 * @since 2017-07-03
 */
public interface IDcrAdjaccHisAcmpFormDtService extends IService<DcrAdjaccHisAcmpFormDt> {

    /**
     * 查询 分页
     *
     * @param page  分页
     * @param dcrAdjaccHisAcmpFormDt
     * @return Page<DcrAdjaccCusAcmpFormDt>
     */
    Page<DcrAdjaccHisAcmpFormDt> selectByList(Page<DcrAdjaccHisAcmpFormDt> page, DcrAdjaccHisAcmpFormDt dcrAdjaccHisAcmpFormDt);
}
