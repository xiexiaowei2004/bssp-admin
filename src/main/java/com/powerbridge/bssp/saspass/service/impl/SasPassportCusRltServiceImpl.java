package com.powerbridge.bssp.saspass.service.impl;

import com.baomidou.mybatisplus.plugins.Page;
import com.powerbridge.bssp.base.service.impl.BaseServiceImpl;
import com.powerbridge.bssp.saspass.dao.SasPassportCusRltMapper;
import com.powerbridge.bssp.saspass.entity.SasPassportCusRlt;
import com.powerbridge.bssp.saspass.service.ISasPassportCusRltService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service("sasPassportCusRltService")
@Transactional(readOnly = true)
public class SasPassportCusRltServiceImpl extends BaseServiceImpl<SasPassportCusRltMapper, SasPassportCusRlt> implements ISasPassportCusRltService {

    @Autowired
    private SasPassportCusRltMapper sasPassportCusRltMapper;

    @Override
    public Page<SasPassportCusRlt> selectBySasPassportCusRlt(Page<SasPassportCusRlt> page, SasPassportCusRlt sasPassportCusRlt){
        page.setRecords(sasPassportCusRltMapper.selectBySasPassportCusRlt(page, sasPassportCusRlt));
        return page;
    }


    @Override
    public List<SasPassportCusRlt> selectByRltNos(List<String> rltNos){
        Map<String, Object> rltNosParam = new HashMap<String, Object>();
        rltNosParam.put("rltNos", rltNos);
        rltNosParam.put("seqNo",null);
        return sasPassportCusRltMapper.selectByRltNosAndSeqNo(rltNosParam);
    }

    @Override
    public List<SasPassportCusRlt> selectByRltNosAndSeqNo(List<String> rltNos,String seqNo){
        Map<String, Object> rltNosParam = new HashMap<String, Object>();
        rltNosParam.put("rltNos", rltNos);
        rltNosParam.put("seqNo",seqNo);
        return sasPassportCusRltMapper.selectByRltNosAndSeqNo(rltNosParam);
    }
}
