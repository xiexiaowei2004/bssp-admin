package com.powerbridge.bssp.edi.xml.prod;

import java.math.BigDecimal;

public interface IGroupProcesser {
	void execute(String docType,String bizType,String areaCode,String seqNo,BigDecimal chgTmsCnt);
}
