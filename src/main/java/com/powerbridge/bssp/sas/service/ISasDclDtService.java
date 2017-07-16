package com.powerbridge.bssp.sas.service;

import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.IService;
import com.powerbridge.bssp.sas.entity.SasDclDt;

/**
 * <p>
 * 业务申报商品表 服务类
 * </p>
 *
 * @author haihuihuang
 * @since 2017-06-01
 */
public interface ISasDclDtService extends IService<SasDclDt> {
    /**
     * 查询/分页
     *
     * @param page      分页
     * @param sasDclDt     参数数据
     * @return Page<SasDclDt>
     */
    Page<SasDclDt> selectByList(Page<SasDclDt> page, SasDclDt sasDclDt);

    /**
     * @param
     * @throws
     * @Description: 用于删除后，将序号重新排序
     */
    void updateBySasDclSeqno(String seqNo,int sasDclSeqno);
}
