package com.powerbridge.bssp.saspass.service;

import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.IService;
import com.powerbridge.bssp.saspass.entity.SasPassportBsc;
import com.powerbridge.bssp.saspass.entity.SasPassportRlt;

import java.util.List;

public interface ISasPassportRltService extends IService<SasPassportRlt> {

    public Boolean batchInsert(List<SasPassportRlt> entitys);

    /**
     * 多表级联的批量插入数据
     * 新增关联单可做批量插入，同时批量获取货物信息插入货物表，并同步更新核放单的关联单号
     * @param sasPass
     * @return
     */
    public Boolean batchAndCascadeInsert(SasPassportBsc sasPass);

    /**
     * 根据rltNo批量查询
     * @param rltNos
     * @return
     */
    public List<SasPassportRlt> selectByRltNos(List<String> rltNos);

    /**
     * 根据rltNo和seqNo查询
     * @param rltNos
     * @param seqNo
     * @return
     */
    public List<SasPassportRlt> selectByRltNosAndSeqNo(List<String> rltNos,String seqNo);

    /**
     * 带条件分页查询
     * @param page
     * @param sasPassportRlt
     * @return
     */
    public Page<SasPassportRlt> selectBySasPassportRlt(Page<SasPassportRlt> page, SasPassportRlt sasPassportRlt);
}
