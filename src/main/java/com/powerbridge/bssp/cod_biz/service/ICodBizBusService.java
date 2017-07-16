package com.powerbridge.bssp.cod_biz.service;

import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.IService;
import com.powerbridge.bssp.cod_biz.entity.CodBizBus;

import java.util.List;

/**
 * 项目名称：bssp Maven Webapp
 * 类名称：ICodBizBusService
 * 类描述：cod_biz_bus_param 表业务逻辑层接口
 * 创建人：xuzuotao
 * 创建时间：2017年4月27日 下午10:12:17
 */
public interface ICodBizBusService extends IService<CodBizBus> {
    /**
     * 查询业务参数/分页
     *
     * @param page 分页
     * @param codBizBus
     * @return Page<CodBizBus>
     */
    Page<CodBizBus> selectByCodBizBus(Page<CodBizBus> page, CodBizBus codBizBus);
    
    List<CodBizBus> selectRedisDropDown();
}
