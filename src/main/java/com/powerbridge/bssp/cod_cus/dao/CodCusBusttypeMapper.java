package com.powerbridge.bssp.cod_cus.dao;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.powerbridge.bssp.cod_cus.entity.CodCusBusttype;

import java.util.List;

public interface CodCusBusttypeMapper  extends BaseMapper<CodCusBusttype>{
    public List<CodCusBusttype> selectBusttypeByList();
    
	List<CodCusBusttype> selectRedisDropDown();

}