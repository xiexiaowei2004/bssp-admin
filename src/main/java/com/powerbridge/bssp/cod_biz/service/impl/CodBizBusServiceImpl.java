package com.powerbridge.bssp.cod_biz.service.impl;


import com.baomidou.mybatisplus.plugins.Page;
import com.powerbridge.bssp.base.service.impl.BaseServiceImpl;
import com.powerbridge.bssp.cod_biz.dao.CodBizBusMapper;
import com.powerbridge.bssp.cod_biz.entity.CodBizBus;
import com.powerbridge.bssp.cod_biz.service.ICodBizBusService;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 项目名称：bssp Maven Webapp
 * 类名称：CodBizBusServiceImpl
 * 类描述：cod_biz_bus_param 表业务逻辑层接口的实现类
 * 创建人：xuzuotao
 * 创建时间：2017年5月9日 下午10:51:17
 */
@Service("codBizBusService")
public class CodBizBusServiceImpl extends BaseServiceImpl<CodBizBusMapper, CodBizBus> implements ICodBizBusService {
    @Autowired
    private CodBizBusMapper codBizBusMapper;

    /**
     * 查询业务参数/分页
     *
     * @param page 分页
     * @param codBizBus
     * @return Page<CodBizBus>
     */
    public Page<CodBizBus> selectByCodBizBus(Page<CodBizBus> page, CodBizBus codBizBus) {
        page.setRecords(codBizBusMapper.selectByCodBizBus(page, codBizBus));
        return page;
    }

	@Override
	public List<CodBizBus> selectRedisDropDown() {
		return codBizBusMapper.selectRedisDropDown();  
		    
	}
}
