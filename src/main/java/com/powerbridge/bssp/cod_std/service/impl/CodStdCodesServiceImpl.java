package com.powerbridge.bssp.cod_std.service.impl;


import com.powerbridge.bssp.base.service.impl.BaseServiceImpl;
import com.powerbridge.bssp.cod_std.dao.CodStdCodesMapper;
import com.powerbridge.bssp.cod_std.entity.CodStdCodes;
import com.powerbridge.bssp.cod_std.service.ICodStdCodesService;
import com.powerbridge.bssp.common.Bean.PullDown;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("codStdCodesService")
public class CodStdCodesServiceImpl extends BaseServiceImpl<CodStdCodesMapper, CodStdCodes> implements ICodStdCodesService {

    @Autowired
    private CodStdCodesMapper codStdCodesMapper;

    /**
     * @Title: selectBriefByList
     * @Description: 获取数据字典信息
     * @return: List<PullDown>
     */
    public List<PullDown> selectCodStdCodesByList() {
        return codStdCodesMapper.selectCodStdCodesByList();
    }

	@Override
	public List<CodStdCodes> selectRedisDropDown() {
		return codStdCodesMapper.selectRedisDropDown();  
		    
	}

}
