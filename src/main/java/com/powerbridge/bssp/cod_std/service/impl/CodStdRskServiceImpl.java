package com.powerbridge.bssp.cod_std.service.impl;



import com.baomidou.mybatisplus.plugins.Page;
import com.powerbridge.bssp.base.service.impl.BaseServiceImpl;
import com.powerbridge.bssp.cod_std.dao.CodStdRskMapper;
import com.powerbridge.bssp.cod_std.entity.CodStdRsk;
import com.powerbridge.bssp.cod_std.service.ICodStdRskService;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 项目名称：bssp Maven Webapp
 * 类名称：CodStdRskServiceImpl
 * 类描述：cod_std_rsk_param表业务逻辑层接口的实现类
 * 创建人：xuzuotao
 * 创建时间：2017年5月9日 下午10:51:17
 */
@Service("codStdRskService")
public class CodStdRskServiceImpl extends BaseServiceImpl<CodStdRskMapper, CodStdRsk> implements ICodStdRskService {
    @Autowired
    private CodStdRskMapper codStdRskMapper;

    /**
     * 查询业务参数/分页
     *
     * @param page 分页
     * @param codStdRsk
     * @return Page<CodStdRsk>
     */
    public Page<CodStdRsk> selectByCodStdRsk(Page<CodStdRsk> page, CodStdRsk codStdRsk) {
        page.setRecords(codStdRskMapper.selectByCodStdRsk(page, codStdRsk));
        return page;
    }

	@Override
	public List<CodStdRsk> selectRedisDropDown() {
		return codStdRskMapper.selectRedisDropDown();  
		    
	}
}
