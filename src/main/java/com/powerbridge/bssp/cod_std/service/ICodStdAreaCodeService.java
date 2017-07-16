package com.powerbridge.bssp.cod_std.service;

import com.baomidou.mybatisplus.service.IService;
import com.powerbridge.bssp.cod_std.entity.CodStdAreaCode;

import java.util.List;

/**
 * 
* 项目名称：bssp Maven Webapp
* 类名称：ICodStdAreaCodeService  
* 类描述：CodStdAreaCode表业务逻辑层接口   
* 创建人：xwq
* 创建时间：2017年5月10日19:21:36
* @version
 */
public interface ICodStdAreaCodeService extends IService<CodStdAreaCode>{

	List<CodStdAreaCode> selectRedisDropDown();
	
	List<CodStdAreaCode> selectCarSortByList(String str);

}
  
    