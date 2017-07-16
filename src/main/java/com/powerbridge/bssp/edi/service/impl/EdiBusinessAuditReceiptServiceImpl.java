package com.powerbridge.bssp.edi.service.impl;

import com.powerbridge.bssp.base.service.impl.BaseServiceImpl;
import com.powerbridge.bssp.edi.dao.EdiBusinessAuditReceiptMapper;
import com.powerbridge.bssp.edi.entity.EdiBusinessAuditReceipt;
import com.powerbridge.bssp.edi.service.IEdiBusinessAuditReceiptService;
import org.springframework.stereotype.Service;

/**
 * Created by 宋轲 on 2017/6/2.
 */
@Service("ediBusinessAuditReceiptService")
public class EdiBusinessAuditReceiptServiceImpl extends BaseServiceImpl<EdiBusinessAuditReceiptMapper,EdiBusinessAuditReceipt> implements IEdiBusinessAuditReceiptService {
}
