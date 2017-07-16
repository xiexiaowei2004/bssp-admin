package com.powerbridge.bssp.cod_std.service.impl;


import com.baomidou.mybatisplus.plugins.Page;
import com.powerbridge.bssp.base.service.impl.BaseServiceImpl;
import com.powerbridge.bssp.cod_std.dao.CodStdBusMapper;
import com.powerbridge.bssp.cod_std.entity.CodStdBus;
import com.powerbridge.bssp.cod_std.service.ICodStdBusService;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 项目名称：bssp Maven Webapp
 * 类名称：CodStdBusServiceImpl
 * 类描述：cod_std_bus_param 表业务逻辑层接口的实现类
 * 创建人：xuzuotao
 * 创建时间：2017年5月9日 下午10:51:17
 */
@Service("codStdBusService")
public class CodStdBusServiceImpl extends BaseServiceImpl<CodStdBusMapper, CodStdBus> implements ICodStdBusService {
    @Autowired
    private CodStdBusMapper codStdBusMapper;

    /**
     * 查询业务参数/分页
     *
     * @param page 分页
     * @param codStdBus
     * @return Page<CodStdBus>
     */
    public Page<CodStdBus> selectByCodStdBus(Page<CodStdBus> page, CodStdBus codStdBus) {
        page.setRecords(codStdBusMapper.selectByCodStdBus(page, codStdBus));
        return page;
    }

	@Override
	public List<CodStdBus> selectRedisDropDown() {
		return codStdBusMapper.selectRedisDropDown();  
		    
	}
}
