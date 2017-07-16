package com.powerbridge.bssp.cod_std.dao;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.powerbridge.bssp.cod_std.entity.CodStdCodes;
import com.powerbridge.bssp.common.Bean.PullDown;

import java.util.List;

public interface CodStdCodesMapper extends BaseMapper<CodStdCodes> {

    /**
     * @Title: selectBriefByList
     * @Description: 获取数据字典信息
     * @return: List<PullDown>
     */
    List<PullDown> selectCodStdCodesByList();
    
    List<CodStdCodes> selectRedisDropDown();
}
