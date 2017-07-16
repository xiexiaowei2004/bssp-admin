package com.powerbridge.bssp.cod_cus.service;

import java.util.List;

import com.baomidou.mybatisplus.service.IService;
import com.powerbridge.bssp.cod_cus.entity.CodMerchElement;

/**
 * 
* 项目名称：bssp Maven Webapp
* 类名称：ICodStdAreaCodeService  
* 类描述：CodStdAreaCode表业务逻辑层接口   
* 创建人：xwq
* 创建时间：2017年5月10日19:21:36
* @version
 */
public interface ICodMerchElementService extends IService<CodMerchElement>{

	//按照主表的税则号查询字表对应的list
	List<CodMerchElement> selectBriefByList(String codeTs);
   /* List<CodMerchElement> selectRedisDropDown();*/

}
  
    