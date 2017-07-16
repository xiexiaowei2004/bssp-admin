package com.powerbridge.bssp.edi.service.impl;

import com.powerbridge.bssp.base.service.impl.BaseServiceImpl;
import com.powerbridge.bssp.edi.dao.EdiRecvDataMapper;
import com.powerbridge.bssp.edi.entity.EdiRecvData;
import com.powerbridge.bssp.edi.service.IEdiRecvDataService;
import org.springframework.stereotype.Service;

/**
 * Created by HQGK-004 on 2017/6/11.
 */
@Service("ediRecvDataService")
public class EdiRecvDataServiceImpl extends BaseServiceImpl<EdiRecvDataMapper, EdiRecvData> implements IEdiRecvDataService {
}
