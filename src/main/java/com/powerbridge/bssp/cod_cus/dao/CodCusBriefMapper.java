package com.powerbridge.bssp.cod_cus.dao;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.powerbridge.bssp.cod_cus.entity.CodCusBrief;
import com.powerbridge.bssp.common.Bean.PullDown;

import java.util.List;

public interface CodCusBriefMapper extends BaseMapper<CodCusBrief> {
    List<PullDown> selectBriefByList();
    
	/*List<CodCusBrief> selectRedisDropDown();*/

}