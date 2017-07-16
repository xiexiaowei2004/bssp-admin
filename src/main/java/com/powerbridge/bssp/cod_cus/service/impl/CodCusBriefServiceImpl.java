package com.powerbridge.bssp.cod_cus.service.impl;

import com.powerbridge.bssp.common.Bean.PullDown;
import com.powerbridge.bssp.base.service.impl.BaseServiceImpl;
import com.powerbridge.bssp.cod_cus.dao.CodCusBriefMapper;
import com.powerbridge.bssp.cod_cus.entity.CodCusBrief;
import com.powerbridge.bssp.cod_cus.service.ICodCusBriefService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 *
 * 项目名称：bssp Maven Webapp
 * 类名称：CodCusBriefServiceImpl
 * 类描述：CodCusBrief 表业务逻辑层接口 实现类
 * 创建时间：2017年4月27日 下午10:12:17
 * @version
 */
@Service("codCusBriefService")
public class CodCusBriefServiceImpl extends BaseServiceImpl<CodCusBriefMapper,CodCusBrief> implements ICodCusBriefService {

    @Autowired
    private CodCusBriefMapper codCusBriefMapper;

    public List<PullDown> selectBriefByList(){
        return codCusBriefMapper.selectBriefByList();
    }


	/*public List<CodCusBrief> selectRedisDropDown() {
		return codCusBriefMapper.selectRedisDropDown();  
		    
	}*/
}
