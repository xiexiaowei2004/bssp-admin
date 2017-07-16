package com.powerbridge.bssp.cod_cus.dao;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.powerbridge.bssp.cod_cus.entity.CodCusCotype;

import java.util.List;

public interface CodCusCotypeMapper extends BaseMapper<CodCusCotype> {
    public List<CodCusCotype> selectCotypeByList();
	List<CodCusCotype> selectRedisDropDown();

}