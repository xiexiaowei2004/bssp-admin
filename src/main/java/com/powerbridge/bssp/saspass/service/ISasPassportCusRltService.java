package com.powerbridge.bssp.saspass.service;

import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.IService;
import com.powerbridge.bssp.saspass.entity.SasPassportCusRlt;

import java.util.List;

public interface ISasPassportCusRltService extends IService<SasPassportCusRlt> {
    /**
     * 根据rltNo批量查询
     * @param rltNos
     * @return
     */
    public List<SasPassportCusRlt> selectByRltNos(List<String> rltNos);

    /**
     * 根据rltNo和seqNo查询
     * @param rltNos
     * @param seqNo
     * @return
     */
    public List<SasPassportCusRlt> selectByRltNosAndSeqNo(List<String> rltNos, String seqNo);

    /**
     * 带条件分页查询
     * @param page
     * @param sasPassportCusRlt
     * @return
     */
    public Page<SasPassportCusRlt> selectBySasPassportCusRlt(Page<SasPassportCusRlt> page, SasPassportCusRlt sasPassportCusRlt);
}
