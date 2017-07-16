package com.powerbridge.bssp.saspass.service.impl;

import com.baomidou.mybatisplus.plugins.Page;
import com.powerbridge.bssp.base.service.impl.BaseServiceImpl;
import com.powerbridge.bssp.saspass.dao.SasPassportHisRltMapper;
import com.powerbridge.bssp.saspass.entity.SasPassportHisRlt;
import com.powerbridge.bssp.saspass.service.ISasPassportHisRltService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service("sasPassportHisRltService")
@Transactional(readOnly = true)
public class SasPassportHisRltServiceImpl extends BaseServiceImpl<SasPassportHisRltMapper, SasPassportHisRlt> implements ISasPassportHisRltService {

    @Autowired
    private SasPassportHisRltMapper sasPassportHisRltMapper;

    @Override
    public Page<SasPassportHisRlt> selectBySasPassportHisRlt(Page<SasPassportHisRlt> page, SasPassportHisRlt sasPassportHisRlt){
        page.setRecords(sasPassportHisRltMapper.selectBySasPassportHisRlt(page, sasPassportHisRlt));
        return page;
    }


    @Override
    public List<SasPassportHisRlt> selectByRltNos(List<String> rltNos){
        Map<String, Object> rltNosParam = new HashMap<String, Object>();
        rltNosParam.put("rltNos", rltNos);
        rltNosParam.put("seqNo",null);
        return sasPassportHisRltMapper.selectByRltNosAndSeqNo(rltNosParam);
    }

    @Override
    public List<SasPassportHisRlt> selectByRltNosAndSeqNo(List<String> rltNos,String seqNo){
        Map<String, Object> rltNosParam = new HashMap<String, Object>();
        rltNosParam.put("rltNos", rltNos);
        rltNosParam.put("seqNo",seqNo);
        return sasPassportHisRltMapper.selectByRltNosAndSeqNo(rltNosParam);
    }
}
