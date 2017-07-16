package com.powerbridge.bssp.saspass.service;

import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.IService;
import com.powerbridge.bssp.saspass.entity.SasPassportBsc;
import com.powerbridge.bssp.saspass.entity.SasPassportHisRlt;
import com.powerbridge.bssp.saspass.entity.SasPassportRlt;

import java.util.List;

public interface ISasPassportHisRltService extends IService<SasPassportHisRlt> {

    /**
     * 根据rltNo批量查询
     * @param rltNos
     * @return
     */
    public List<SasPassportHisRlt> selectByRltNos(List<String> rltNos);

    /**
     * 根据rltNo和seqNo查询
     * @param rltNos
     * @param seqNo
     * @return
     */
    public List<SasPassportHisRlt> selectByRltNosAndSeqNo(List<String> rltNos, String seqNo);

    /**
     * 带条件分页查询
     * @param page
     * @param sasPassportHisRlt
     * @return
     */
    public Page<SasPassportHisRlt> selectBySasPassportHisRlt(Page<SasPassportHisRlt> page, SasPassportHisRlt sasPassportHisRlt);
}
