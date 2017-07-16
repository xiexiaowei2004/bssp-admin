package com.powerbridge.bssp.edi.service.impl;

import com.powerbridge.bssp.base.service.impl.BaseServiceImpl;
import com.powerbridge.bssp.edi.dao.EdiBusinessCheckReceiptMapper;
import com.powerbridge.bssp.edi.entity.EdiBusinessCheckReceipt;
import com.powerbridge.bssp.edi.service.IEdiBusinessCheckReceiptService;
import org.springframework.stereotype.Service;

/**
 * Created by 宋轲 on 2017/6/2.
 */
@Service("ediBusinessCheckReceiptService")
public class EdiBusinessCheckReceiptServiceImpl extends BaseServiceImpl<EdiBusinessCheckReceiptMapper,EdiBusinessCheckReceipt> implements IEdiBusinessCheckReceiptService {
}
