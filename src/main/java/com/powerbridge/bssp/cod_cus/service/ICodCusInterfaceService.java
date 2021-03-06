package com.powerbridge.bssp.cod_cus.service;

import java.util.List;

import com.baomidou.mybatisplus.service.IService;
import com.powerbridge.bssp.cod_cus.entity.CodCusInterface;

/**
 * 项目名称：bssp-admin
 * 类名称：ICodCusInterfaceInterfaceService
 * 类描述：
 * 创建人：willChen
 * 创建时间：2017/5/6 12:01
 * 修改人：willChen
 * 修改时间：2017/5/6 12:01
 */
public interface ICodCusInterfaceService extends IService<CodCusInterface> {
	
    List<CodCusInterface> selectRedisDropDown();

}
