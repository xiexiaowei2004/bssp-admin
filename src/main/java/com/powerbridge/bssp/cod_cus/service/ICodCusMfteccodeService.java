package com.powerbridge.bssp.cod_cus.service;

import java.util.List;

import com.baomidou.mybatisplus.service.IService;
import com.powerbridge.bssp.cod_cus.entity.CodCusMfteccode;

/**
 * 项目名称：bssp-admin
 * 类名称：ICodCusMfteccodeService
 * 类描述：CodCusMfteccode 表业务逻辑层接口
 * 创建人：willChen
 * 创建时间：2017/5/4 15:39
 * 修改人：willChen
 * 修改时间：2017/5/4 15:39
 */
public interface ICodCusMfteccodeService extends IService<CodCusMfteccode> {
    List<CodCusMfteccode> selectRedisDropDown();

}
