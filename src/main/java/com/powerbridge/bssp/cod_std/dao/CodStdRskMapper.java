package com.powerbridge.bssp.cod_std.dao;


import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.powerbridge.bssp.cod_std.entity.CodStdRsk;

import java.util.List;

/**
 * 项目名称：bssp Maven Webapp
 * 类名称：CodStdRskMapper
 * 类描述：cod_std_rsk_param 表数据访问层接口
 * 创建人：xuzuotao
 * 创建时间：2017年5月9日 下午10:54:17
 */
public interface CodStdRskMapper extends BaseMapper<CodStdRsk> {
    /**
     * 查询风险参数基础表/分页
     *
     * @param page 分页
     * @param codStdRsk
     * @return List<CodStdRsk>
     */
    List<CodStdRsk> selectByCodStdRsk(Page<CodStdRsk> page, CodStdRsk codStdRsk);
    
    List<CodStdRsk> selectRedisDropDown();
}