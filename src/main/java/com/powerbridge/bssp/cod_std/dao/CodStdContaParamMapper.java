package com.powerbridge.bssp.cod_std.dao;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.powerbridge.bssp.cod_std.entity.CodStdContaParam;

import java.util.List;

public interface CodStdContaParamMapper extends BaseMapper<CodStdContaParam> {

    public List<CodStdContaParam> selectRedisDropDown();

}
