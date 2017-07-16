package com.powerbridge.bssp.cod_biz.service.impl;


import com.baomidou.mybatisplus.plugins.Page;
import com.powerbridge.bssp.base.service.impl.BaseServiceImpl;
import com.powerbridge.bssp.cod_biz.dao.CodBizRskMapper;
import com.powerbridge.bssp.cod_biz.entity.CodBizRsk;
import com.powerbridge.bssp.cod_biz.service.ICodBizRskService;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 项目名称：bssp Maven Webapp
 * 类名称：CodBizRskServiceImpl
 * 类描述：cod_biz_rsk_param 表业务逻辑层接口的实现类
 * 创建人：xuzuotao
 * 创建时间：2017年5月9日 下午10:51:17
 */
@Service("codBizRskService")
public class CodBizRskServiceImpl extends BaseServiceImpl<CodBizRskMapper, CodBizRsk> implements ICodBizRskService {
    @Autowired
    private CodBizRskMapper codBizRskMapper;

    /**
     * 查询业务参数/分页
     *
     * @param page 分页
     * @param codBizRsk
     * @return Page<CodBizRsk>
     */
    public Page<CodBizRsk> selectByCodBizRsk(Page<CodBizRsk> page, CodBizRsk codBizRsk) {
        page.setRecords(codBizRskMapper.selectByCodBizRsk(page, codBizRsk));
        return page;
    }

	@Override
	public List<CodBizRsk> selectRedisDropDown() {
		return codBizRskMapper.selectRedisDropDown();  
		    
	}
}
