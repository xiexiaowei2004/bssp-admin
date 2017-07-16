package com.powerbridge.bssp.dcr.service;

import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.IService;
import com.powerbridge.bssp.dcr.entity.DcrChgoffImg;
import com.powerbridge.bssp.ems.entity.EmsCusImg;

import java.util.List;

/**
 * <p>
 * 账册报核料件 服务类
 * </p>
 *
 * @author haihuihuang
 * @since 2017-05-22
 */
public interface IDcrChgoffImgService extends IService<DcrChgoffImg> {
    /**
     * 查询 分页
     *
     * @param page  分页
     * @param seqNo 单据编号
     * @return Page<DcrChgoffImg>
     */
    Page<DcrChgoffImg> selectByList(Page<DcrChgoffImg> page, String seqNo);

    /**
     * 核销
     *
     * @param seqNo 单据编号
     * @return boolean
     */
    boolean getDcrChgoffImgByList(List<EmsCusImg> cusImgList, String seqNo, String chgTmsCnt);

    /**
     * @param
     * @throws
     * @Description: 用于删除后，将序号重新排序
     */
    void updateGdsSeqno(String seqNo,int gdsSeqno);
}
