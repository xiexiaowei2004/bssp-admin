package com.powerbridge.bssp.cod_cus.service;

import java.util.List;

import com.baomidou.mybatisplus.service.IService;
import com.powerbridge.bssp.cod_cus.entity.CodCusUnit;

/**
 * 项目名称：bssp-admin
 * 类名称：ICodCusUnitService
 * 类描述：CodCusUnit 表业务逻辑层接口
 * 创建人：willChen
 * 创建时间：2017/5/5 09:39
 * 修改人：willChen
 * 修改时间：2017/5/5 09:39
 */
public interface ICodCusUnitService extends IService<CodCusUnit> {
    List<CodCusUnit> selectRedisDropDown();

}
