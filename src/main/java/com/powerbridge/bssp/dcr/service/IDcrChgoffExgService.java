package com.powerbridge.bssp.dcr.service;

import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.IService;
import com.powerbridge.bssp.dcr.entity.DcrChgoffExg;

/**
 * <p>
 * 账册报核成品 服务类
 * </p>
 *
 * @author haihuihuang
 * @since 2017-05-22
 */
public interface IDcrChgoffExgService extends IService<DcrChgoffExg> {

    /**
     * 查询 分页
     *
     * @param page  分页
     * @param seqNo 单据编号
     * @return Page<DcrChgoffImg>
     */
    Page<DcrChgoffExg> selectByList(Page<DcrChgoffExg> page, String seqNo);

    /**
     * @param
     * @throws
     * @Description: 用于删除后，将序号重新排序
     */
    void updateGdsSeqno(String seqNo,int gdsSeqno);
}
