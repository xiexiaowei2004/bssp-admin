package com.powerbridge.bssp.cod_std.dao;


import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.powerbridge.bssp.cod_std.entity.CodStdBus;

import java.util.List;

/**
 * 项目名称：bssp Maven Webapp
 * 类名称：CodStdBusMapper
 * 类描述：cod_std_bus_type 表数据访问层接口
 * 创建人：xuzuotao
 * 创建时间：2017年5月9日 下午10:54:17
 */
public interface CodStdBusMapper extends BaseMapper<CodStdBus> {
    /**
     * 查询业务参数基础表/分页
     *
     * @param page 分页
     * @param codStdBus
     * @return List<CodStdBus>
     */
    List<CodStdBus> selectByCodStdBus(Page<CodStdBus> page, CodStdBus codStdBus);
    
    List<CodStdBus> selectRedisDropDown();
}