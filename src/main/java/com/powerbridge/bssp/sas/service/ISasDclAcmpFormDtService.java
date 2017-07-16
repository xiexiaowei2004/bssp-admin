package com.powerbridge.bssp.sas.service;

import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.IService;
import com.powerbridge.bssp.sas.entity.SasDclAcmpFormDt;

/**
 * <p>
 * 业务申报随附单证表 服务类
 * </p>
 *
 * @author haihuihuang
 * @since 2017-06-01
 */
public interface ISasDclAcmpFormDtService extends IService<SasDclAcmpFormDt> {
    /**
     * 查询/分页
     *
     * @param page      分页
     * @param seqNo     参数数据
     * @return Page<SasDclAcmpFormDt>
     */
    Page<SasDclAcmpFormDt> selectByList(Page<SasDclAcmpFormDt> page, String seqNo);

    /**
     * @param
     * @throws
     * @Description: 用于删除后，将序号重新排序
     */
    void updateAcmpFormSeqno(String seqNo,int acmpFormSeqno);
}
