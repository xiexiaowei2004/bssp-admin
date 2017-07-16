package com.powerbridge.bssp.cod_std.service;

import java.util.List;

import com.baomidou.mybatisplus.service.IService;
import com.powerbridge.bssp.cod_std.entity.CodStdClearanceType;

/**
 * 
* 项目名称：bssp Maven Webapp
* 类名称：ICodStdClearanceTypeService  
* 类描述：CodStdClearanceType表业务逻辑层接口   
* 创建人：xwq
* 创建时间：2017年5月10日19:48:35
* @version
 */
public interface ICodStdClearanceTypeService extends IService<CodStdClearanceType>{
	List<CodStdClearanceType> selectRedisDropDown();
}
  
    