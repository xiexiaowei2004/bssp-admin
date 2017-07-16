package com.powerbridge.bssp.edi.service.impl;

import com.powerbridge.bssp.base.service.impl.BaseServiceImpl;
import com.powerbridge.bssp.edi.dao.EdiBusinessStorageReceiptMapper;
import com.powerbridge.bssp.edi.entity.EdiBusinessStorageReceipt;
import com.powerbridge.bssp.edi.service.IEdiBusinessStorageReceiptService;
import org.springframework.stereotype.Service;

/**
 * Created by 宋轲 on 2017/6/2.
 */
@Service("ediBusinessStorageReceiptService")
public class EdiBusinessStorageReceiptServiceImpl extends BaseServiceImpl<EdiBusinessStorageReceiptMapper,EdiBusinessStorageReceipt> implements IEdiBusinessStorageReceiptService {
}
