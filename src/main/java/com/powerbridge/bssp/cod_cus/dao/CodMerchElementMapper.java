package com.powerbridge.bssp.cod_cus.dao;

import java.util.List;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.powerbridge.bssp.cod_cus.entity.CodMerchElement;

public interface CodMerchElementMapper extends BaseMapper<CodMerchElement>{

	List<CodMerchElement> selectBriefByList(String codeTs);
	/*List<CodMerchElement> selectRedisDropDown();*/

}
  
    