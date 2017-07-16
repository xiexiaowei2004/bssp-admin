package com.powerbridge.bssp.sas.service.impl;

import com.baomidou.mybatisplus.plugins.Page;
import com.powerbridge.bssp.sas.entity.SasDclCusAcmpFormDt;
import com.powerbridge.bssp.sas.dao.SasDclCusAcmpFormDtMapper;
import com.powerbridge.bssp.sas.service.ISasDclCusAcmpFormDtService;
import com.powerbridge.bssp.base.service.impl.BaseServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 业务申报随附单证正式表 服务实现类
 * </p>
 *
 * @author haihuihuang
 * @since 2017-07-01
 */
@Service("sasDclCusAcmpFormDtService")
public class SasDclCusAcmpFormDtServiceImpl extends BaseServiceImpl<SasDclCusAcmpFormDtMapper, SasDclCusAcmpFormDt> implements ISasDclCusAcmpFormDtService {

    @Autowired
    private SasDclCusAcmpFormDtMapper sasDclCusAcmpFormDtMapper;

    /**
     * 查询备案/分页
     *
     * @param page         分页
     * @param seqNo        参数数据
     * @return Page<SasDclAcmpFormDt>
     */
    public Page<SasDclCusAcmpFormDt> selectByList(Page<SasDclCusAcmpFormDt> page, String seqNo) {
        page.setRecords(sasDclCusAcmpFormDtMapper.selectByList(page, seqNo));
        return page;
    }
}
