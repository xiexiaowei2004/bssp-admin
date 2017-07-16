package com.powerbridge.bssp.cod_cus.service;


import java.util.List;

import com.baomidou.mybatisplus.service.IService;
import com.powerbridge.bssp.cod_cus.entity.CodCusBusitype;

/**
 * 
* 项目名称：bssp Maven Webapp
* 类名称：ICodCusBusitypeService  
* 类描述：CodCusBusitype表业务逻辑层接口   
* 创建人：LC
* 创建时间：2017年4月29日 下午17:57:17
* @version
 */
public interface ICodCusBusitypeService  extends IService<CodCusBusitype> {
    List<CodCusBusitype> selectRedisDropDown();

}
