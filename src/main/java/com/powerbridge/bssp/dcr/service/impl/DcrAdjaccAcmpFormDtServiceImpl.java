package com.powerbridge.bssp.dcr.service.impl;

import com.baomidou.mybatisplus.plugins.Page;
import com.powerbridge.bssp.base.service.impl.BaseServiceImpl;
import com.powerbridge.bssp.dcr.dao.DcrAdjaccAcmpFormDtMapper;
import com.powerbridge.bssp.dcr.entity.DcrAdjaccAcmpFormDt;
import com.powerbridge.bssp.dcr.service.IDcrAdjaccAcmpFormDtService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 账册核销随附单证明细 服务实现类
 * </p>
 *
 * @author haihuihuang
 * @since 2017-05-22
 */
@Service("dcrAdjaccAcmpFormDtService")
public class DcrAdjaccAcmpFormDtServiceImpl extends BaseServiceImpl<DcrAdjaccAcmpFormDtMapper, DcrAdjaccAcmpFormDt> implements IDcrAdjaccAcmpFormDtService {

    @Autowired
    private DcrAdjaccAcmpFormDtMapper dcrAdjaccAcmpFormDtMapper;

    /**
     * 查询 分页
     *
     * @param page  分页
     * @param seqNo 单据编号
     * @return Page<DcrAdjaccAcmpFormDt>
     */
    public Page<DcrAdjaccAcmpFormDt> selectByList(Page<DcrAdjaccAcmpFormDt> page, String seqNo) {
        page.setRecords(dcrAdjaccAcmpFormDtMapper.selectByList(page, seqNo));
        return page;
    }

    /**
     * @param
     * @throws
     * @Description: 用于删除后，将序号重新排序
     */
    public void updateAcmpFormSeqno(String seqNo,int acmpFormSeqno){
        dcrAdjaccAcmpFormDtMapper.updateAcmpFormSeqno(seqNo,acmpFormSeqno);
    }
}
