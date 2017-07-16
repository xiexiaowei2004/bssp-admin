package com.powerbridge.bssp.dcr.service;


import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.IService;
import com.powerbridge.bssp.dcr.entity.DcrAdjaccAcmpFormDt;

/**
 * <p>
 * 账册核销随附单证明细 服务类
 * </p>
 *
 * @author haihuihuang
 * @since 2017-05-22
 */
public interface IDcrAdjaccAcmpFormDtService extends IService<DcrAdjaccAcmpFormDt> {

    /**
     * 查询 分页
     *
     * @param page  分页
     * @param seqNo 单据编号
     * @return Page<DcrAdjaccAcmpFormDt>
     */
    Page<DcrAdjaccAcmpFormDt> selectByList(Page<DcrAdjaccAcmpFormDt> page, String seqNo);

    /**
     * @param
     * @throws
     * @Description: 用于删除后，将序号重新排序
     */
    void updateAcmpFormSeqno(String seqNo,int acmpFormSeqno);
}
