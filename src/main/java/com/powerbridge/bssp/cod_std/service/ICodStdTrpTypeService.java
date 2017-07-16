package com.powerbridge.bssp.cod_std.service;

import java.util.List;

import com.baomidou.mybatisplus.service.IService;
import com.powerbridge.bssp.cod_std.entity.CodStdTrpType;

/**
 * 项目名称：bssp-admin
 * 类名称：ICodStdTransportTypeService
 * 类描述：CodStdTransportType 表业务逻辑层接口
 * 创建人：zsl
 * 创建时间：2017/5/11 19:30
 * 修改人：zsl
 * 修改时间：2017/5/11 19:30
 */
public interface ICodStdTrpTypeService extends IService<CodStdTrpType> {
	List<CodStdTrpType> selectRedisDropDown();
}
