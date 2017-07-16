package com.powerbridge.bssp.cod_std.service;

import com.baomidou.mybatisplus.service.IService;
import com.powerbridge.bssp.cod_std.entity.CodStdContaParam;

import java.util.List;

/**
 * 项目名称： bssp Maven Webapp
 * 类名称： ICodStdContaParamService
 * 类描述： 集装箱逻辑层接口
 * 创建人： LC
 * 创建时间： 2017年5月12日
 */
public interface ICodStdContaParamService extends IService<CodStdContaParam> {

    List<CodStdContaParam> selectRedisDropDown();

}
