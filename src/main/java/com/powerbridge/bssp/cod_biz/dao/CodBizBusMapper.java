package com.powerbridge.bssp.cod_biz.dao;


import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.powerbridge.bssp.cod_biz.entity.CodBizBus;

import java.util.List;

/**
 * 项目名称：bssp Maven Webapp
 * 类名称：CodBizBusMapper
 * 类描述：cod_biz_bus_param 表数据访问层接口
 * 创建人：xuzuotao
 * 创建时间：2017年5月9日 下午10:54:17
 */
public interface CodBizBusMapper extends BaseMapper<CodBizBus> {
 /**
  * 查询业务参数/分页
  *
  * @param page 分页
  * @param codBizBus
  * @return List<CodBizBus>
  */
 List<CodBizBus> selectByCodBizBus(Page<CodBizBus> page, CodBizBus codBizBus);
 
 List<CodBizBus> selectRedisDropDown();
}