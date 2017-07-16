package com.powerbridge.bssp.cod_std.service;

import java.util.List;

import com.baomidou.mybatisplus.service.IService;
import com.powerbridge.bssp.cod_std.entity.CodStdCarType;


/**
 * 
* 项目名称：bssp Maven Webapp
* 类名称：ICodStdCarTypeService  
* 类描述：CodStdCarType表业务逻辑层接口   
* 创建人：zsl
* 创建时间：2017年5月13日19:48:35
* @version
 */
public interface ICodStdCarTypeService extends IService<CodStdCarType>{
	List<CodStdCarType> selectRedisDropDown();
}
  
    