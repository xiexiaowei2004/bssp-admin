package com.powerbridge.bssp.dcr.service;

import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.IService;
import com.powerbridge.bssp.dcr.entity.DcrChgoffInvtLt;
import com.powerbridge.bssp.inv.entity.InvCusBsc;

import java.util.List;

/**
 * <p>
 * 账册报核清单 服务类
 * </p>
 *
 * @author haihuihuang
 * @since 2017-05-22
 */
public interface IDcrChgoffInvtLtService extends IService<DcrChgoffInvtLt> {

    /**
     * 查询 分页
     *
     * @param page  分页
     * @param seqNo 单据编号
     * @return Page<DcrChgoffInvtLt>
     */
    Page<DcrChgoffInvtLt> selectByList(Page<DcrChgoffInvtLt> page, String seqNo);

    /**
     * 选取
     *
     * @param seqNo 单据编号
     * @return boolean
     */
    boolean getDcrChgoffInvtLtByList(List<InvCusBsc> invCusBscList, String seqNo, String dclTypecd, String chgTmsCnt);

    /**
     * @param
     * @throws
     * @Description: 用于删除后，将序号重新排序
     */
    void updateLno(String seqNo,int lNo);
}
